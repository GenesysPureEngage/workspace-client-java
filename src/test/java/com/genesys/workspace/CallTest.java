package com.genesys.workspace;

import com.genesys.workspace.models.Capability;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;

public class CallTest {
    @Test
    public void capabilityFromString() {
        String[] values = {
            "answer",
            "release",
            "hold",
            "retrieve",
            "send-dtmf",
            "attach-user-data",
            "delete-user-data-pair",
            "update-user-data",
            "initiate-conference",
            "initiate-transfer",
            "complete-conference",
            "complete-transfer",
            "single-step-conference",
            "single-step-transfer",
            "delete-from-conference",
            "start-recording",
            "stop-recording",
            "pause-recording",
            "resume-recording",
            "switch-to-listen-in",
            "switch-to-coaching",
            "switch-to-barge-in",
            "alternate",
            "clear",
            "reconnect",
            "redirect",
            "complete",
            "merge",
        };
        
        for(String v: values) {
            Capability capability = Capability.fromString(v);
            Assert.assertNotNull(capability);
        }
    }
}
