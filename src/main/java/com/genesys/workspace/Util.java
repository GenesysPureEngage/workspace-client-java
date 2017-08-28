package com.genesys.workspace;

import com.genesys.workspace.events.NotificationType;
import com.genesys.workspace.models.*;
import com.genesys.workspace.models.cfg.ActionCodeType;
import com.genesys.workspace.models.targets.availability.AgentActivity;

import java.util.Map;

public class Util {
    public static AgentState parseAgentState(String input) {
        AgentState state = AgentState.UNKNOWN;
        if (input != null) {
            switch (input) {
                case "LoggedOut":
                    state = AgentState.LOGGED_OUT;
                    break;

                case "LoggedIn":
                    state = AgentState.LOGGED_IN;
                    break;

                case "Ready":
                    state = AgentState.READY;
                    break;

                case "NotReady":
                    state = AgentState.NOT_READY;
                    break;

                case "OutOfService":
                    state = AgentState.OUT_OF_SERVICE;
                    break;
            }
        }

        return state;
    }

    public static AgentWorkMode parseAgentWorkMode(String input) {
        AgentWorkMode workMode = AgentWorkMode.UNKNOWN;
        if (input != null) {
            switch (input) {
                case "AuxWork":
                    workMode = AgentWorkMode.AUX_WORK;
                    break;

                case "AfterCallWork":
                    workMode = AgentWorkMode.AFTER_CALL_WORK;
                    break;

                case "AutoIn":
                    workMode = AgentWorkMode.AUTO_IN;
                    break;

                case "ManualIn":
                    workMode = AgentWorkMode.MANUAL_IN;
                    break;
            }
        }

        return workMode;
    }

    public static ActionCodeType parseActionCodeType(String input) {

        ActionCodeType type = ActionCodeType.UNKNOWN;
        if (input == null) {
            return type;
        }

        switch (input) {
            case "Login":
                type = ActionCodeType.LOGIN;
                break;

            case "Logout":
                type = ActionCodeType.LOGOUT;
                break;

            case "Ready":
                type = ActionCodeType.READY;
                break;

            case "NotReady":
                type = ActionCodeType.NOT_READY;
                break;

            case "BusyOn":
                type = ActionCodeType.BUSY_ON;
                break;

            case "BusyOff":
                type = ActionCodeType.BUSY_OFF;
                break;

            case "ForwardOn":
                type = ActionCodeType.FORWARD_ON;
                break;

            case "Forwardoff":
                type = ActionCodeType.FORWARD_OFF;
                break;

            case "InternalCall":
                type = ActionCodeType.INTERNAL_CALL;
                break;

            case "InboundCall":
                type = ActionCodeType.INBOUND_CALL;
                break;

            case "OutboundCall":
                type = ActionCodeType.OUTBOUND_CALL;
                break;

            case "Conference":
                type = ActionCodeType.CONFERENCE;
                break;

            case "Transfer":
                type = ActionCodeType.TRANSFER;
                break;
        }

        return type;
    }


    public static void extractKeyValueData(KeyValueCollection userData, Object[] data) {
        if (data == null) {
            return;
        }

        for(int i=0; i < data.length; i++) {
            Map<String, Object> pair = (Map<String, Object>)data[i];
            String key = (String)pair.get("key");
            String type = (String)pair.get("type");

            switch (type) {
                case "str":
                    userData.addString(key, (String)pair.get("value"));
                    break;

                case "int":
                    userData.addInt(key, (Integer)pair.get("value"));

                case "kvlist":
                    KeyValueCollection list = new KeyValueCollection();
                    Util.extractKeyValueData(list, (Object[])pair.get("value"));
                    userData.addList(key, list);
                    break;
            }
        }
    }

    public static String[] extractParticipants(Object[] data) {
        String[] participants = new String[data.length];
        for(int i = 0; i < data.length; i++) {
            Map<String, Object> p = (Map<String, Object>)data[i];
            String number = (String)p.get("number");
            participants[i] = number;
        }

        return participants;
    }

    public static CallState parseCallState(String input) {
        CallState state = CallState.UNKNOWN;
        if (input != null) {
            switch (input) {
                case "Ringing":
                    state = CallState.RINGING;
                    break;

                case "Dialing":
                    state = CallState.DIALING;
                    break;

                case "Established":
                    state = CallState.ESTABLISHED;
                    break;

                case "Held":
                    state = CallState.HELD;
                    break;

                case "Released":
                    state = CallState.RELEASED;
                    break;

                case "Completed":
                    state = CallState.COMPLETED;
                    break;
            }
        }

        return state;
    }

    public static NotificationType parseNotificationType(String input) {
        NotificationType type = NotificationType.UNKNOWN;
        if (input != null) {
            switch (input) {
                case "StateChange":
                    type = NotificationType.STATE_CHANGE;
                    break;

                case "ParticipantsUpdated":
                    type = NotificationType.PARTICIPANTS_UPDATED;
                    break;

                case "AttachedDataChanged":
                    type = NotificationType.ATTACHED_DATA_CHANGED;
                    break;

                case "CallRecovered":
                    type = NotificationType.CALL_RECOVERED;
                    break;
            }
        }

        return type;
    }

    public static AgentActivity parseAgentActivity(String input) {
        AgentActivity activity = AgentActivity.UNKNOWN;
        if (input != null) {
            switch (input) {
                case "Idle":
                    activity = AgentActivity.IDLE;
                    break;

                case "HandlingInboundCall":
                    activity = AgentActivity.HANDLING_INBOUND_CALL;
                    break;

                case "HandlingInternalCall":
                    activity = AgentActivity.HANDLING_INTERNAL_CALL;
                    break;

                case "HandlingOutboundCall":
                    activity = AgentActivity.HANDLING_OUTBOUND_CALL;
                    break;

                case "HandlingConsultCall":
                    activity = AgentActivity.HANDLING_CONSULT_CALL;
                    break;

                case "InitiatingCall":
                    activity = AgentActivity.INITIATING_CALL;
                    break;

                case "ReceivingCall":
                    activity = AgentActivity.RECEIVING_CALL;
                    break;

                case "CallOnHold":
                    activity = AgentActivity.CALL_ON_HOLD;
                    break;

                case "HandlingInboundInteraction":
                    activity = AgentActivity.HANDLING_INBOUND_INTERACTION;
                    break;

                case "HandlingInternalInteraction":
                    activity = AgentActivity.HANDLING_INTERNAL_INTERACTION;
                    break;

                case "HandlingOutboundInteraction":
                    activity = AgentActivity.HANDLING_OUTBOUND_INTERACTION;
                    break;

                case "DeliveringInteraction":
                    activity = AgentActivity.DELIVERING_INTERACTION;
                    break;


            }
        }

        return activity;
    }

}
