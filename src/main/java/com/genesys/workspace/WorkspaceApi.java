package com.genesys.workspace;


import com.genesys.internal.common.ApiClient;
import com.genesys.internal.common.ApiException;
import com.genesys.internal.common.ApiResponse;
import com.genesys.internal.workspace.api.SessionApi;
import com.genesys.internal.workspace.model.*;
import com.genesys.workspace.common.WorkspaceApiException;
import com.genesys.workspace.models.*;
import com.genesys.workspace.models.cfg.*;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkspaceApi {
    private static final Logger logger = LoggerFactory.getLogger(WorkspaceApi.class);
    
    public static final String SESSION_COOKIE = "WORKSPACE_SESSIONID";
    
    private String apiKey;
    private String baseUrl;
    private String workspaceUrl;

    private Notifications notifications;
    private SessionApi sessionApi;
    private TargetsApi targetsApi;
    private ReportingApi reportingApi;
    private VoiceApi voiceApi;
    
    private String workspaceSessionId;
    private boolean workspaceInitialized = false;
    
    private User user;
    private KeyValueCollection settings;
    private List<AgentGroup> agentGroups;
    private List<BusinessAttribute> businessAttributes;
    private List<ActionCode> actionCodes;
    private List<Transaction> transactions;
    
    private WorkspaceApiException initError;
	private Object initSignal;
	
    /**
     * Constructor 
     * @param apiKey The API key to be included in HTTP requests.
     * @param baseUrl The base URL of the PureEngage API.
    */
    public WorkspaceApi(String apiKey, String baseUrl) {
        this(apiKey, baseUrl, new VoiceApi(), new TargetsApi(), new SessionApi(), new Notifications());
    }

    WorkspaceApi(String apiKey, String baseUrl, VoiceApi voiceApi, TargetsApi targetsApi, SessionApi sessionApi, Notifications notifications) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.workspaceUrl = this.baseUrl + "/workspace/v3";
        this.voiceApi = voiceApi;
        this.targetsApi = targetsApi;
        this.sessionApi = sessionApi;
        this.notifications = notifications;
        this.reportingApi = new ReportingApi();
    }

    private static String extractSessionCookie(ApiResponse<ApiSuccessResponse> response) throws WorkspaceApiException {
        logger.debug("Extracting session cookie...");
        String workspaceSessionCookie = null;
        List<String> cookies = response.getHeaders().get("set-cookie");
        for (String cookie : cookies) {
            if(cookie.startsWith("WORKSPACE_SESSIONID")){
                workspaceSessionCookie = cookie;
                break;
            }
        }

        if(workspaceSessionCookie == null) {
            throw new WorkspaceApiException("Failed to extract workspace session cookie.");
        }

        String value = workspaceSessionCookie;
        String sessionId = value.split(";")[0].split("=")[1];
        logger.debug("WORKSPACE_SESSIONID is " + sessionId);
        
        return sessionId;
    }
    
    void onInitMessage(Map<String, Object> data) {
        String messageType = (String)data.get("messageType");
        if ("WorkspaceInitializationComplete".equals(messageType)) {
                String state = (String)data.get("state");
                if ("Complete".equals(state)) {
                    Map<String, Object> initData = (Map<String, Object>)data.get("data");
                    Map<String, Object> userData = (Map<String, Object>)initData.get("user");
                    String employeeId = (String)userData.get("employeeId");
                    String defaultPlace = (String)userData.get("defaultPlace");
                    String agentId = (String)userData.get("agentLogin");
                    Object[] annexData = (Object[])userData.get("userProperties");
                    KeyValueCollection userProperties = Util.extractKeyValueData(annexData);

                    if (user == null) {
                        user = new User();
                    }

                    user.setEmployeeId(employeeId);
                    user.setAgentId(agentId);
                    user.setDefaultPlace(defaultPlace);
                    user.setUserProperties(userProperties);

                    extractConfiguration((Map<String, Object>)initData.get("configuration"));

                    this.workspaceInitialized = true;
                    logger.debug("Initialization complete");

                    synchronized (this.initSignal) {
                        this.initSignal.notifyAll();
                    }    
                } else if ("Failed".equals(state)) {
                    logger.debug("Workspace initialization failed!");
                    this.initError = new WorkspaceApiException("initialize workspace failed");
                    synchronized (this.initSignal) {
                    this.initSignal.notifyAll();
                    }
                }
        }
        else {
            logger.debug("{}", data);
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
                KeyValueCollection userProperties = Util.extractKeyValueData(userPropertyData);

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
        this.settings = Util.extractKeyValueData(settingsData);

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
                KeyValueCollection userProperties = Util.extractKeyValueData(userPropertyData);

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

                        userProperties.addList(sectionName, section);
                    }
                }

                this.agentGroups.add(new AgentGroup(name, dbid, userProperties));
            }
        }
    }

    

    /**
     * Initializes the API using the provided authorization code and redirect URI. The authorization code comes from using the 
     * Authorization Code Grant flow to authenticate with the Authentication API. Genesys recommends using this code grant type.
     * @param authCode The authorization code you received during authentication.
     * @param redirectUri The redirect URI you used during authentication. Since this is not sent by the UI, it needs to match the redirectUri that you sent 
     * when using the Authentication API to get the authCode.
     * @return CompletableFuture<User>
     */
    public User initialize(String authCode, String redirectUri) throws WorkspaceApiException {
        return initialize(authCode, redirectUri, null);
    }

    /**
     * Initializes the API using the provided auth token.
     * @param token The auth token to use for initialization.
     */
    public User initialize(String token) throws WorkspaceApiException {
        return initialize(null, null, token);
    }

    private User initialize(String authCode, String redirectUri, String token) throws WorkspaceApiException {
        try {
        	this.initSignal = new Object();
            ApiClient client = new ApiClient();
            List<Interceptor> interceptors = client.getHttpClient().interceptors();
            interceptors.add(new TraceInterceptor());
            
            final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            interceptors.add(loggingInterceptor);
            
            client.setBasePath(workspaceUrl);
            CookieStoreImpl cookieStore = new CookieStoreImpl();
            client.getHttpClient().setCookieHandler(new CookieManager(cookieStore, CookiePolicy.ACCEPT_ALL));
            client.addDefaultHeader("x-api-key", apiKey);
            
            sessionApi.setApiClient(client);
            voiceApi.initialize(client);
            targetsApi.initialize(client);
            reportingApi.initialize(client);

            String authorization = token != null ? "Bearer " + token : null;
            final ApiResponse<ApiSuccessResponse> response = sessionApi.initializeWorkspaceWithHttpInfo(authCode, redirectUri, authorization);
            workspaceSessionId = extractSessionCookie(response);
            client.addDefaultHeader("Cookie", String.format("%s=%s", SESSION_COOKIE, workspaceSessionId));

//            final CompletableFuture<User> future = new CompletableFuture<>();

            notifications.setCookieStore(cookieStore);
            notifications.subscribe("/workspace/v3/initialization", new Notifications.NotificationListener() {
                @Override
                public void onNotification(String channel, Map<String, Object> data) {
                    onInitMessage(data);
                }
            });
            //notifications.subscribe("/workspace/v3/initialization", (channel, msg) -> onInitMessage(msg, future));
            notifications.subscribe("/workspace/v3/voice", new Notifications.NotificationListener() {
                @Override
                public void onNotification(String channel, Map<String, Object> data) {
                    voiceApi.onVoiceMessage(data);
                }
            });
            //notifications.subscribe("/workspace/v3/voice", (channel, msg) -> voiceApi.onVoiceMessage(msg));
            notifications.initialize(workspaceUrl + "/notifications", apiKey);
            
        	synchronized(this.initSignal) {
        		this.initSignal.wait(30000);
        	}
        	
            if (this.initError != null) {
                WorkspaceApiException e = this.initError;
                initError = null;
                throw e;
            } 
            else if (user!=null)
            	return this.user;
            
            throw new WorkspaceApiException("timeout");

        } catch (InterruptedException | ApiException e) {
            throw new WorkspaceApiException("initialize failed.", e);
        }
    }

    /**
     * Logout the agent and cleanup resources.
     */
    public void destroy() throws WorkspaceApiException {
        try {
            if (this.workspaceInitialized) {
                notifications.disconnect();
                sessionApi.logout();
            }
        } catch (Exception e) {
            throw new WorkspaceApiException("destroy failed.", e);
        } finally {
            this.workspaceInitialized = false;
        }
    }

    /**
     * Initializes the voice channel for the specified agent and DN.
     * @param agentId The ID of the agent to be used for login.
     * @param placeName The DN to be used for login.
     */
    public void activateChannels(
            String agentId,
            String placeName
    ) throws WorkspaceApiException {
        this.activateChannels(agentId, null, placeName, null, null);
    }

    /**
     * Initializes the voice channel using the specified resources.
     * @param agentId The ID of the agent to be used for login.
     * @param dn The DN to be used for login. Only provide this if you aren't specifying the place name.
     * @param placeName The name of the place to use for login. Only provide this if you aren't specifying the DN.
     * @param queueName The name of the queue to be used for login. (optional)
     * @param workMode The workMode to be used for login. The possible values are AUTO_IN or MANUAL_IN. (optional)
     */
    public void activateChannels(
            String agentId,
            String dn,
            String placeName,
            String queueName,
            AgentWorkMode workMode
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

            if (workMode != null) {
                if (AgentWorkMode.MANUAL_IN.equals(workMode)) {
                    data.setAgentWorkMode(ActivatechannelsData.AgentWorkModeEnum.MANUALIN);
                } else if (AgentWorkMode.AUTO_IN.equals(workMode)) {
                    data.setAgentWorkMode(ActivatechannelsData.AgentWorkModeEnum.AUTOIN);
                } else {
                    throw new WorkspaceApiException(
                            "only workmode MANUAL_IN or AUTO_IN can be used");
                }
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
     * Returns the Voice API.
     * @return VoiceApi
     */
    public VoiceApi voice() {
        return this.voiceApi;
    }

    /**
     * Returns the Targets API.
     * @return TargetsApi
     */
    public TargetsApi targets() {
        return this.targetsApi;
    }
    
    /**
     * Returns the Reporting API.
     * 
     * @return ReportingApi
     */
    public ReportingApi getReportingApi() {
        return reportingApi;
    }
    
    /**
      * Returns the current user.
      * @return User
      */
    public User getUser() {
        return this.user;
    }

    /**
      * Returns application options from Configuration Server.
      * @return KeyValueCollection
      */
    public KeyValueCollection getSettings() {
        return this.settings;
    }

    /**
      * Returns action codes from Configuration Server. 
      * @return Collection<ActionCode>
      */
    public Collection<ActionCode> getActionCodes() {
        return this.actionCodes;
    }

    /**
      * Returns agent groups from Configuration Server.
      * @return Collection<AgentGroup>
      */
    public Collection<AgentGroup> getAgentGroups() {
        return this.agentGroups;
    }

    /**
      * Returns business attributes from Configuration Server.
      * @return Collection<BusinessAttribute>
      */
    public Collection<BusinessAttribute> getBusinessAttributes() {
        return this.businessAttributes;
    }

    /**
      * Returns transactions from Configuration Server.
      * @return Collection<Transaction>
      */
    public Collection<Transaction> getTransactions() {
        return this.transactions;
    }
}
