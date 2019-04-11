package com.genesys.workspace;

import com.genesys.workspace.common.WorkspaceApiException;
import java.net.CookieManager;
import java.net.CookieStore;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;
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
    
    final Map<String, Collection<NotificationListener>> listeners = new ConcurrentHashMap<>();

    private CookieStore cookieStore = new CookieManager().getCookieStore();

    public CookieStore getCookieStore() {
        return cookieStore;
    }

    public void setCookieStore(CookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }

    private void onHandshake(Message msg) {
        if(msg.isSuccessful()) {
            logger.debug("Handshake successful.");
            
            logger.debug("Subscribing to channels...");
            
            for (final String name : this.listeners.keySet()) {
                final Collection<NotificationListener> list = this.listeners.get(name);
                client.getChannel(name).subscribe(new ClientSessionChannel.MessageListener() {
                    @Override
                    public void onMessage(ClientSessionChannel clientSessionChannel, Message message) {
                        Map<String, Object> data = message.getDataAsMap();
                        for (NotificationListener notificationListener : list) {
                            notificationListener.onNotification(name, data);
                        }
                    }
                }, new ClientSessionChannel.MessageListener() {
                    @Override
                    public void onMessage(ClientSessionChannel clientSessionChannel, Message message) {
                        String subscription = (String) message.get("subscription");
                        if (message.isSuccessful()) {
                            logger.debug("Successfuly subscribed to channel: {}", subscription);
                        } else {
                            logger.error("Cannot subscribe to channel: {}", subscription);
                        }
                    }
                });
            }
        }
        else {
            logger.debug("{}", msg);
            logger.error("Handshake failed");
        }
    }
    
    public void initialize(String endpoint, String apiKey) throws WorkspaceApiException {
        try {
            httpClient = new HttpClient(new SslContextFactory());
            httpClient.start();
            client = new BayeuxClient(endpoint, new ClientTransport(apiKey, httpClient) {
                @Override
                protected CookieStore getCookieStore() {
                    return cookieStore;
                }
            });
            initialize(client);
        }
        catch(Exception ex) {
            throw new WorkspaceApiException("Cometd initialization failed.", ex);
        }
    }
    
    void initialize(BayeuxClient client) {
        this.client = client;

        logger.debug("Starting cometd handshake...");
        client.handshake(new ClientSessionChannel.MessageListener() {
            @Override
            public void onMessage(ClientSessionChannel clientSessionChannel, Message message) {
                onHandshake(message);
            }
        });
    }
    
    public void disconnect() throws WorkspaceApiException  {
        disconnect(10000); // 10 second timeout by default
    }

    public void disconnect(long disconnectRequestTimeout) throws WorkspaceApiException  {
        try {
            if(client != null) {
                client.disconnect(disconnectRequestTimeout);
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
        synchronized(listeners) {
            if(!listeners.containsKey(channelName)){
                listeners.put(channelName, new ConcurrentLinkedQueue<NotificationListener>());
            }
        }
        
        listeners.get(channelName).add(listener);
    }
}
