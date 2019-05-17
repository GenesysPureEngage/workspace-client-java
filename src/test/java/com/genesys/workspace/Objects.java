package com.genesys.workspace;

import com.genesys.internal.common.ApiResponse;
import com.genesys.internal.workspace.model.*;
import com.genesys.workspace.events.NotificationType;
import com.genesys.workspace.models.AgentState;
import com.genesys.workspace.models.AgentWorkMode;
import com.genesys.workspace.models.CallState;
import com.genesys.workspace.models.KeyValueCollection;
import com.genesys.workspace.models.cfg.ActionCodeType;
import com.genesys.workspace.models.targets.TargetType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.cometd.bayeux.Message;
import org.cometd.common.HashMapMessage;

public class Objects {
    final static Random random = new Random();
    
    public static final String SEARCH_TERM = "test";
    public static final String SWITCH_NAME = "switch";
    public static final String API_KEY = "apiKey";
    public static final String BASE_URL = "https://baseUrl";
    public static final String CALL_ID = "callid";
    public static final String OTHER_CALL_ID = "callid";
    public static final String HELD_CALL_ID = "callid";
    public static final String PARENT_CALL_ID = "parentCallId";
    public static final String DESTINATION = "destination";
    public static final String OUTBOUND_CALLER_ID = "outboundCallerId";
    public static final String LOCATION = "location";
    public static final String DIGITS = "123456";
    public static final String WORKMODE = "workMode";
    public static final String REASON_CODE = "reasonCode";
    public static final Boolean DND = true;
    public static final String AGENT_ID = "agentId";
    public static final String AGENT_DN = "agentDn";
    public static final String PLACE_NAME = "placeName";
    public static final String QUEUE_NAME = "queueName";
    public static String TOKEN = "token";
    public static String AUTH_CODE = "authcode";
    public static String REDIRECT_URI = "redirecturi";
    public static final String SUBSCRIPTION_ID = "subscriptionId";
    public static final String CONNECTION_ID = "connectionId";

    public static KeyValueCollection makeUserData() {
        return new KeyValueCollection();
    }
    
    public static KeyValueCollection makeReasons() {
        return new KeyValueCollection();
    }
    
    public static KeyValueCollection makeExtensions() {
        return new KeyValueCollection();
    }
    
    public static Target makeTarget(TargetType type) {
        int dbid = random.nextInt();
        String number = String.valueOf(random.nextInt());
        
        Target target = new Target();
        target.setType(Target.TypeEnum.fromValue(type.getValue()));
        target.DBID(dbid);
        target.setName(String.format("target %s %d", type, dbid));
        target.setSwitchName(SWITCH_NAME);
        target.setNumber(number);
        
        return target;
    }

    public static List<Target> makeTargets(int n) {
        TargetType[] types = TargetType.values();
        List<Target> list = new ArrayList<>();
        for(int i = 0; i < n; ++i) {
            Target target = makeTarget(types[random.nextInt(types.length)]);
            list.add(target);
        }
        
        return list;
    }
    
    public static TargetsResponse makeTargetsResponse(int n) {
        TargetsResponse response = new TargetsResponse();
        response.setStatus(new TargetsResponseStatus());
        TargetsResponseData data = new TargetsResponseData();
        data.setTotalMatches(n);
        data.setTargets(Objects.makeTargets(data.getTotalMatches()));
        response.setData(data);
        
        return response;
    }

    public static ApiSuccessResponse makeResponse(int code) {
        ApiSuccessResponse response = new ApiSuccessResponse();
        TargetsResponseStatus status = new TargetsResponseStatus();
        status.setCode(code);
        response.setStatus(status);
        
        return response;                
    }
    
    public static ApiResponse<ApiSuccessResponse> makeHttpResponse() {
        Map<String,List<String>> headers = new HashMap<>();
        headers.put("set-cookie", Arrays.asList("WORKSPACE_SESSIONID=sessionid"));
        
        return makeHttpResponse(200, headers);
    }

    public static ApiResponse<ApiSuccessResponse> makeHttpResponse(int code, Map<String,List<String>> headers) {
        ApiResponse<ApiSuccessResponse> response = new ApiResponse<>(code, headers);
        
        return response;        
    }

    private static Object[] makeActionCodes() {
        return new Object[]{};
    }

    private static Object[] makeSettings() {
        return new Object[]{};
    }

    private static Object[] makeBusinessAttributes() {
        return new Object[]{};
    }

    private static Object[] makeTransactions() {
        return new Object[]{};
    }

    private static Object[] makeAgentGroups() {
        return new Object[]{};
    }

    public static Map<String, Object> makeActionCode(String name, ActionCodeType type) {
        Map<String,Object> data = new HashMap<>();
        data.put("name", name);
        data.put("type", type.toString());
        
        return data;
    }

    public static enum MessageType {
        DnStateChanged,
        EventError,
        CallStateChanged,
        WorkspaceInitializationComplete
    }
    
    public static Message makeMessage(MessageType type) {
        Map<String,Object> data = new HashMap<>();
        data.put("messageType", type.toString());
        
        switch (type) {
            case DnStateChanged:
                data.put("dn", makeDnData());
                break;
            case EventError:
                data.put("error", makeErrorData());
                break;
            case CallStateChanged:
                NotificationType[] types = NotificationType.values();
                data.put("notificationType", types[random.nextInt(types.length)].toString());
                data.put("call", makeCallData());
                break;
            case WorkspaceInitializationComplete:
                data.put("data", makeInitData());
                data.put("state", "Complete");
                break;
        }
        
        
        Message message = new HashMapMessage();
        message.put(Message.DATA_FIELD, data);
        
        return message;
    }
    
    public static Map<String, Object> makeStateChangeData() {
        Map<String, Object> map = new HashMap<>();
        map.put("dn", makeDnData());
        
        return map;
    }

    private static Map<String, Object> makeDnData() {
        AgentState[] states = AgentState.values();
        AgentWorkMode[] modes = AgentWorkMode.values();
        
        return makeDnData(String.valueOf(random.nextInt()), String.valueOf(random.nextInt()), states[random.nextInt(states.length)], modes[random.nextInt(modes.length)], String.valueOf(random.nextInt()), DND);
    }
    
    private static Map<String, Object> makeDnData(String agentId, String agentNumber, AgentState state, AgentWorkMode mode, String forwardTo, Boolean dnd) {
        Map<String, Object> data = new HashMap<>();
        data.put("number", agentNumber);
        data.put("agentId", agentId);
        data.put("agentState", state.toString());
        data.put("agentWorkMode", mode.toString());
        data.put("forwardTo", forwardTo);
        data.put("dnd", dnd);
        
        return data;
    }
    
    public static Map<String, Object> makeCallData() {
        CallState[] states = CallState.values();
        return makeCallData(String.valueOf(random.nextInt()), 
                String.valueOf(random.nextInt()), 
                states[random.nextInt(states.length)], 
                String.valueOf(random.nextInt()), 
                String.valueOf(random.nextInt()), 
                String.valueOf(random.nextInt()), 
                String.valueOf(random.nextInt()), 
                makeCapabilities(), makeParticipants(), makeUserDataArray());
    }

    public static Map<String, Object> makeCallData(
            String id, 
            String callUuid,
            CallState state, 
            String parentConnId, 
            String previousConnId, 
            String ani, 
            String dnis, 
            Object[] capabilities,
            Object[] participantData,
            Object[] userData) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("callUuid", callUuid);
        data.put("state", state.toString());
        data.put("parentConnId", parentConnId);
        data.put("previousConnId", previousConnId);
        data.put("ani", ani);
        data.put("dnis", dnis);
        data.put("capabilities", capabilities);
        data.put("participants", participantData);
        data.put("userData", userData);
        
        return data;
    }

    public static Object[] makeUserDataArray() {
        
        return new Object[] {};
    }

    public static Object[] makeParticipants() {
        
        return new Object[] {};
    }

    public static Object[] makeCapabilities() {
        
        return new Object[] {};
    }
    
    public static Map<String, Object> makeUser() {
        Map<String, Object> data = new HashMap<>();
        data.put("employeeId", AGENT_ID);
        data.put("defaultPlace", PLACE_NAME);
        data.put("agentLogin", AGENT_DN);
        data.put("userProperties", new Object[]{});
        
        return data;
    }
    
    public static Map<String, Object> makeErrorData() {
        return makeErrorData("test", 1);
    }

    public static Map<String, Object> makeErrorData(String msg, int code) {
        Map<String,Object> data = new HashMap<>();
        data.put("errorMessage", msg);
        data.put("errorCode", code);
        
        return data;
    }

    public static Map<String, Object> makeInitData() {
        Map<String, Object> user = makeUser();
        
        Map<String,Object> conf = new HashMap<>();
        conf.put("actionCodes", makeActionCodes());
        conf.put("settings", makeSettings());
        conf.put("businessAttributes", makeBusinessAttributes());
        conf.put("transactions", makeTransactions());
        conf.put("agentGroups", makeAgentGroups());
        
        Map<String,Object> data = new HashMap<>();
        data.put("user", user);
        data.put("configuration", conf);
        
        return data;
    }
    
}
