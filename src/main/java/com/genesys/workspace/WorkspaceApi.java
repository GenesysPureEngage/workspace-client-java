package com.genesys.workspace;

import java.net.URI;
import java.net.HttpCookie;
import java.net.CookieManager;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.genesys._internal.workspace.model.*;
import com.genesys._internal.workspace.ApiClient;
import com.genesys._internal.workspace.ApiException;
import com.genesys._internal.workspace.ApiResponse;
import com.genesys.workspace.common.WorkspaceApiException;
import com.genesys.workspace.models.*;
import com.genesys.workspace.models.cfg.*;
import com.genesys._internal.workspace.api.SessionApi;

import com.genesys.workspace.models.cfg.BusinessAttribute;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;
import org.cometd.client.BayeuxClient;
import org.cometd.client.transport.ClientTransport;
import org.cometd.client.transport.LongPollingTransport;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkspaceApi {
    private static final Logger logger = LoggerFactory.getLogger(WorkspaceApi.class);
    
    private String apiKey;
    private String baseUrl;
    private boolean debugEnabled;
    private String workspaceUrl;
    private ApiClient workspaceClient;
    private HttpClient cometdHttpClient;
    private BayeuxClient cometdClient;
    private SessionApi sessionApi;
    private TargetsApi targetsApi;
    private VoiceApi voiceApi;
    private String sessionCookie;
    private String workspaceSessionId;
    private CompletableFuture<User> initFuture;
    private boolean workspaceInitialized = false;
    private User user;
    private KeyValueCollection settings;
    private List<AgentGroup> agentGroups;
    private List<BusinessAttribute> businessAttributes;
    private List<ActionCode> actionCodes;
    private List<Transaction> transactions;

    /**
     * Constructor 
     * @param apiKey The API key to be used.
     * @param baseUrl base URL for the workpace API
     * @param debugEnabled enable debug (or not) 
    */
    public WorkspaceApi(
            String apiKey,
            String baseUrl,
            boolean debugEnabled
    ) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.workspaceUrl = this.baseUrl + "/workspace/v3";
        this.debugEnabled = debugEnabled;
        this.voiceApi = new VoiceApi(debugEnabled);
        this.targetsApi = new TargetsApi();
    }

    private void extractSessionCookie(ApiResponse<ApiSuccessResponse> response) throws WorkspaceApiException {
        logger.debug("Extracting session cookie...");
        Optional<String> cookie = response.getHeaders().get("set-cookie")
                .stream().filter(v -> v.startsWith("WORKSPACE_SESSIONID")).findFirst();

        if(!cookie.isPresent()) {
            throw new WorkspaceApiException("Failed to extract workspace session cookie.");
        }

        this.sessionCookie = cookie.get();
        this.workspaceSessionId = this.sessionCookie.split(";")[0].split("=")[1];
        logger.debug("WORKSPACE_SESSIONID is " + this.workspaceSessionId);

        this.workspaceClient.addDefaultHeader("Cookie", this.sessionCookie);
    }

    private void onInitMessage(Message message) {
        logger.debug("Message received for /workspace/v3/initialization:\n" + message.toString());

        Map<String, Object> data = message.getDataAsMap();
        String messageType = (String)data.get("messageType");

        if ("WorkspaceInitializationComplete".equals(messageType)) {
            String state = (String)data.get("state");
            if ("Complete".equals(state)) {
                this.workspaceInitialized = true;

                Map<String, Object> initData = (Map<String, Object>)data.get("data");
                Map<String, Object> userData = (Map<String, Object>)initData.get("user");
                String employeeId = (String)userData.get("employeeId");
                String defaultPlace = (String)userData.get("defaultPlace");
                String agentId = (String)userData.get("agentLogin");
                Object[] annexData = (Object[])userData.get("userProperties");
                KeyValueCollection userProperties = new KeyValueCollection();
                Util.extractKeyValueData(userProperties, annexData);

                if (this.user == null) {
                    this.user = new User();
                }

                this.user.setEmployeeId(employeeId);
                this.user.setAgentId(agentId);
                this.user.setDefaultPlace(defaultPlace);
                this.user.setUserProperties(userProperties);

                this.extractConfiguration((Map<String, Object>)initData.get("configuration"));

                this.initFuture.complete(this.user);
                this.workspaceInitialized = true;

            } else if ("Failed".equals(state)) {
                logger.debug("Workspace initialization failed!");
                this.initFuture.completeExceptionally(
                        new WorkspaceApiException("initialize workspace failed"));
            }
        }
    }

    private void extractConfiguration(Map<String, Object> configData) {
        Object[] actionCodesData = (Object[])configData.get("actionCodes");
        this.actionCodes = new ArrayList<>();
        if (actionCodesData != null) {
            for (int i = 0; i < actionCodesData.length; i++) {
                Map<String, Object> actionCodeData = (Map<String, Object>)actionCodesData[i];
                String name = (String)actionCodeData.get("name");
                String code = (String)actionCodeData.get("code");
                ActionCodeType type = Util.parseActionCodeType((String)actionCodeData.get("type"));
                Object[] userPropertyData = (Object[])actionCodeData.get("userProperties");
                KeyValueCollection userProperties = new KeyValueCollection();
                Util.extractKeyValueData(userProperties, userPropertyData);

                Object[] subCodesData = (Object[])actionCodeData.get("subCodes");
                List<SubCode> subCodes = new ArrayList<>();
                if (subCodesData != null) {
                    for (int j = 0; j < subCodesData.length; j++) {
                        Map<String, Object> subCodeData = (Map<String, Object>)subCodesData[j];
                        String subCodeName = (String)subCodeData.get("name");
                        String subCodeCode = (String)subCodeData.get("code");

                        subCodes.add(new SubCode(subCodeName, subCodeCode));
                    }
                }

                this.actionCodes.add(new ActionCode(name, code, type, subCodes, userProperties));
            }
        }

        Object[] settingsData = (Object[])configData.get("settings");
        this.settings = new KeyValueCollection();
        Util.extractKeyValueData(this.settings, settingsData);

        Object[] businessAttributesData = (Object[])configData.get("businessAttributes");
        if (businessAttributesData != null) {
            this.businessAttributes = new ArrayList<>();
            for (int i = 0; i < businessAttributesData.length; i ++) {
                Map<String, Object> businessAttributeData = (Map<String, Object>)businessAttributesData[i];
                Long dbid = (Long)businessAttributeData.get("id");
                String name = (String)businessAttributeData.get("name");
                String displayName = (String)businessAttributeData.get("displayName");
                String description = (String)businessAttributeData.get("description");
                Object[] valuesData = (Object[])businessAttributeData.get("values");

                List<BusinessAttributeValue> values = new ArrayList<>();
                if (valuesData != null) {
                    for (int j = 0; j < valuesData.length; j++) {
                        Map<String, Object> valueData = (Map<String, Object>)valuesData[j];
                        Long valueDBID = (Long)valueData.get("id");
                        String valueName = (String)valueData.get("name");
                        String valueDisplayName = (String)valueData.get("displayName");
                        String valueDescription = (String)valueData.get("description");
                        Object valueDefault = valueData.get("default");

                        values.add(new BusinessAttributeValue(
                                valueDBID, valueName, valueDisplayName,
                                valueDescription, valueDefault));
                    }
                }

                this.businessAttributes.add(new BusinessAttribute(dbid, name, displayName, description, values));
            }
        }

        Object[] transactionsData = (Object[])configData.get("transactions");
        if (transactionsData != null) {
            this.transactions = new ArrayList<>();
            for (int i = 0; i < transactionsData.length; i ++) {
                Map<String, Object> transactionData = (Map<String, Object>)transactionsData[i];
                String name = (String)transactionData.get("name");
                String alias = (String)transactionData.get("alias");
                Object[] userPropertyData = (Object[])transactionData.get("userProperties");
                KeyValueCollection userProperties = new KeyValueCollection();
                Util.extractKeyValueData(userProperties, userPropertyData);

                this.transactions.add(new Transaction(name, alias, userProperties));
            }
        }

        Object[] agentGroupsData = (Object[])configData.get("agentGroups");
        if (agentGroupsData != null) {
            this.agentGroups = new ArrayList<>();
            for (int i = 0; i < agentGroupsData.length; i++) {
                Map<String, Object> agentGroupData = (Map<String, Object>)agentGroupsData[i];
                Long dbid = (Long)agentGroupData.get("DBID");
                String name = (String)agentGroupData.get("name");

                KeyValueCollection userProperties = new KeyValueCollection();
                Map<String, Object> agentGroupSettingsData = (Map<String, Object>)agentGroupData.get("settings");
                if (agentGroupSettingsData != null && !agentGroupSettingsData.isEmpty()) {
                    // Top level will be sections
                    for (Map.Entry<String, Object> entry : agentGroupSettingsData.entrySet()) {

                        String sectionName = entry.getKey();
                        Map<String, Object> sectionData = (Map<String, Object>)entry.getValue();
                        KeyValueCollection section = new KeyValueCollection();
                        if (sectionData != null && !sectionData.isEmpty()) {
                            for (Map.Entry<String, Object> option : sectionData.entrySet()) {
                                section.addString(option.getKey(), (String)option.getValue());
                            }
                        }

                        userProperties.addList(entry.getKey(), section);
                    }
                }

                this.agentGroups.add(new AgentGroup(name, dbid, userProperties));
            }
        }
    }

    private void onHandshake(Message handshakeMessage) {
        if(!handshakeMessage.isSuccessful()) {
            logger.debug("Cometd handshake failed:\n" + handshakeMessage.toString());
            return;
        }

        logger.debug("Cometd handshake successful.");
        logger.debug("Subscribing to channels...");
        this.cometdClient.getChannel("/workspace/v3/initialization").subscribe(
                (ClientSessionChannel channel, Message msg) -> this.onInitMessage(msg));

        this.cometdClient.getChannel("/workspace/v3/voice").subscribe((channel, msg) -> {
            try {
                this.voiceApi.onVoiceMessage(msg);
            }
            catch(Exception ex) {
                logger.error("", ex);
            }
        });
    }

    private void initializeCometd() throws WorkspaceApiException {
        try {
            logger.debug("Initializing cometd...");
            SslContextFactory sslContextFactory = new SslContextFactory();
            this.cometdHttpClient = new HttpClient(sslContextFactory);
            cometdHttpClient.start();

            CookieManager manager = new CookieManager();
            cometdHttpClient.setCookieStore(manager.getCookieStore());
            cometdHttpClient.getCookieStore().add(new URI(workspaceUrl),
                    new HttpCookie("WORKSPACE_SESSIONID", this.workspaceSessionId));

            ClientTransport transport = new LongPollingTransport(new HashMap(), cometdHttpClient) {
                 final TraceInterceptor interceptor = new TraceInterceptor();
                
                @Override
                protected void customize(Request request) {
                    request.header("x-api-key", apiKey);
                    request.header(TraceInterceptor.TRACEID_HEADER, interceptor.makeUniqueId());
                    request.header(TraceInterceptor.SPANID_HEADER, interceptor.makeUniqueId());
                    
                    logger.debug(request.toString());
                    logger.debug(request.getHeaders().toString());
                }
            };

            this.cometdClient = new BayeuxClient(this.workspaceUrl + "/notifications", transport);
            logger.debug("Starting cometd handshake...");
            this.cometdClient.handshake((ClientSessionChannel channel, Message msg) -> this.onHandshake(msg));

        } catch(Exception e) {
            throw new WorkspaceApiException("Cometd initialization failed.", e);
        }
    }

    /**
     * Initializes the API using the provided authCode and redirectUri. This is the preferred means of init.
     * @param authCode the auth code to be used for initialization
     * @param redirectUri the redirectUri to be used for initialization. Since this is not being sent by the UI, this just
     * needs to match the redirectUri that was sent when obtaining the authCode.
     */
    public CompletableFuture<User> initialize(String authCode, String redirectUri) throws WorkspaceApiException {
        return this.initialize(authCode, redirectUri, null);
    }

    /**
     * Initializes the API using the provided auth token.
     * @param token The auth token to use for initialization.
     */
    public CompletableFuture<User> initialize(String token) throws WorkspaceApiException {
        return this.initialize(null, null, token);
    }

    private CompletableFuture<User> initialize(String authCode, String redirectUri, String token) throws WorkspaceApiException {
        try {
            this.initFuture = new CompletableFuture<>();

            ApiClient client = new ApiClient();
            
            List<Interceptor> interceptors = client.getHttpClient().interceptors();
            interceptors.add(new TraceInterceptor());
            
            final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(logger::debug);
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            interceptors.add(loggingInterceptor);
            
            client.setBasePath(this.workspaceUrl);
            client.addDefaultHeader("x-api-key", this.apiKey);
            
            this.workspaceClient = client;
            this.sessionApi = new SessionApi(client);
            this.voiceApi.initialize(client);
            this.targetsApi.initialize(client);

            String authorization = token != null ? "Bearer " + token : null;
            final ApiResponse<ApiSuccessResponse> response =
                    this.sessionApi.initializeWorkspaceWithHttpInfo(authCode, redirectUri, authorization);
            this.extractSessionCookie(response);

            this.initializeCometd();
            return initFuture;

        } catch (ApiException e) {
            throw new WorkspaceApiException("initialize failed.", e);
        }
    }

    /**
     * Logout the agent and cleanup resources.
     */
    public void destroy() throws WorkspaceApiException {
        try {
            if (this.workspaceInitialized) {
                this.cometdClient.disconnect();
                this.cometdHttpClient.stop();
                this.sessionApi.logout();
            }
        } catch (Exception e) {
            throw new WorkspaceApiException("destroy failed.", e);
        } finally {
            this.workspaceInitialized = false;
        }
    }

    /**
     * Initializes the voice channel using the specified resources.
     * @param agentId agentId to be used for login
     * @param placeName name of the place to use for login
     */
    public void activateChannels(
            String agentId,
            String placeName
    ) throws WorkspaceApiException {
        this.activateChannels(agentId, null, placeName, null);
    }

    /**
     * Initializes the voice channel using the specified resources.
     * @param agentId agentId to be used for login
     * @param dn DN to be used for login. Provide only one of dn or placeName
     * @param placeName name of the place to use for login. Provide only one of placeName or dn
     * @param queueName name of the queue to be used for login. (optional)
     */
    public void activateChannels(
            String agentId,
            String dn,
            String placeName,
            String queueName
    ) throws WorkspaceApiException {
        try {
            String msg = "Activating channels with agentId [" + agentId + "] ";
            ActivatechannelsData data = new ActivatechannelsData();
            data.setAgentId(agentId);

            if (placeName != null) {
                data.setPlaceName(placeName);
                msg += "place [" + placeName + "]";
            } else {
                data.setDn(dn);
                msg += "dn [" + dn + "]";
            }

            if (queueName != null) {
                data.setQueueName(queueName);
                msg += " queueName [" + queueName + "]";
            }

            ChannelsData channelsData = new ChannelsData();
            channelsData.data(data);

            logger.debug(msg + "...");
            ApiSuccessResponse response = this.sessionApi.activateChannels(channelsData);
            if(response.getStatus().getCode() != 0) {
                throw new WorkspaceApiException(
                        "activateChannels failed with code: " +
                                response.getStatus().getCode());
            }
        } catch (ApiException e) {
            throw new WorkspaceApiException("activateChannels failed.", e);
        }
    }

    /**
     * Returns the targets API
     * @return Targets API
     */
    public VoiceApi voice() {
        return this.voiceApi;
    }

    /**
     * Returns the voice API.
     * @return Voice API
     */
    public TargetsApi targets() {
        return this.targetsApi;
    }
    /**
      * Returns the current user.
      * @return the current user.
      */
    public User getUser() {
        return this.user;
    }

    public KeyValueCollection getSettings() {
        return this.settings;
    }

    public Collection<ActionCode> getActionCodes() {
        return this.actionCodes;
    }

    public Collection<AgentGroup> getAgentGroups() {
        return this.agentGroups;
    }

    public Collection<BusinessAttribute> getBusinessAttributes() {
        return this.businessAttributes;
    }

    public Collection<Transaction> getTransactions() {
        return this.transactions;
    }

    /**
     * Returns the debug flag
      * @return debug flag
     */
    public boolean debugEnabled() {
        return this.debugEnabled;
    }

    /**
     * Sets the debug flag
     * @param debugEnabled debug flag
     */
    public void setDebugEnabled(boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
        this.voiceApi.setDebugEnabled(debugEnabled);
    }
}
