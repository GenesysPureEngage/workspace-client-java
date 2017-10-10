package com.genesys.workspace;

import com.genesys.workspace.common.WorkspaceApiException;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cometd.bayeux.Message;
import org.cometd.client.BayeuxClient;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Notifications {
    private static final Logger logger = LoggerFactory.getLogger(Notifications.class);

    public static interface NotificationListener {
        void onNotification(String channel, Map<String,Object> data);
    }

    private BayeuxClient client;
    private HttpClient httpClient;
    
    final Map<String, List<NotificationListener>> listeners = new HashMap<>();

    private void onHandshake(Message msg) {
        if(msg.isSuccessful()) {
            logger.debug("Cometd handshake successful.");
            logger.debug("Subscribing to channels...");
            
            listeners.entrySet().forEach((entry) -> {
                final String name = entry.getKey();
                final List<NotificationListener> list = entry.getValue();

                client.getChannel(name).subscribe((channel, message) ->  {
                    Map<String, Object> data = message.getDataAsMap();
                    list.forEach(listener -> listener.onNotification(name, data));
                }, (channel, message) -> {
                    String subscription = (String) message.get("subscription");
                    if(message.isSuccessful()) {
                        logger.debug("Successfuly subscribed to channel: {}", subscription);
                    }
                    else {
                        logger.error("Cannot subscribe to channel: {}", subscription);
                    }
                });
            });
        }
        else {
            logger.error("Cometd handshake failed");
            logger.error(msg.toString());
        }
    }
    
    public void initialize(String endpoint, String apiKey, final String sessionId) throws WorkspaceApiException {
        try {
            httpClient = new HttpClient(new SslContextFactory());
            httpClient.start();
            client = new BayeuxClient(endpoint, new ClientTransport(apiKey, httpClient));            
            client.getCookieStore().add(new URI(endpoint), new HttpCookie(WorkspaceApi.SESSION_COOKIE, sessionId));
            
            initialize(endpoint, apiKey, sessionId, client);
        }
        catch(Exception ex) {
            throw new WorkspaceApiException("Cometd initialization failed.", ex);
        }
    }
    
    void initialize(String endpoint, String apiKey, final String sessionId, BayeuxClient client) {
        this.client = client;
        
        logger.debug("Starting cometd handshake...");
        client.handshake((channel, msg) -> onHandshake(msg));
    }
    
    public void disconnect() throws WorkspaceApiException  {
        try {
            if(client != null) {
                client.disconnect();
            }
            if(httpClient != null) {
                httpClient.stop();
            }
        }
        catch (Exception ex) {
            throw new WorkspaceApiException("Cannot disconnection", ex);
        }
    }
    
    public void subscribe(String channelName, NotificationListener listener) {
        if(!listeners.containsKey(channelName)) {
            listeners.put(channelName, new ArrayList<>());
        }
        
        List<NotificationListener> list = listeners.get(channelName);
        list.add(listener);
    }
}
