package com.genesys.workspace;

import java.util.HashMap;
import org.cometd.client.transport.LongPollingTransport;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientTransport extends LongPollingTransport {
    private static final Logger logger = LoggerFactory.getLogger(ClientTransport.class);
    
    private final TraceInterceptor interceptor = new TraceInterceptor();
    private final String apiKey;

    public ClientTransport(String apiKey, HttpClient httpClient) {
        super(new HashMap<String, Object>(), httpClient);
        
        this.apiKey = apiKey;
    }

    @Override
    protected void customize(Request request) {
        request.header("x-api-key", apiKey);
        request.header(TraceInterceptor.TRACEID_HEADER, interceptor.makeUniqueId());
        request.header(TraceInterceptor.SPANID_HEADER, interceptor.makeUniqueId());

        logger.debug(request.toString());
        logger.debug(request.getHeaders().toString());
    }
}
