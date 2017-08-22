# workspace-client-java
A Java library to interface with the Workspace API 

# Examples
There are several examples available for Java:

* [Changing agent state](https://github.com/GenesysPureEngage/tutorials/tree/master/voice-ready-workspace-java)
* [Basic call control](https://github.com/GenesysPureEngage/tutorials/tree/master/basic-call-control-workspace-java)
* [Conferences](https://github.com/GenesysPureEngage/tutorials/tree/master/conference-call-workspace-java)
* [Transfers](https://github.com/GenesysPureEngage/tutorials/tree/master/transfer-call-workspace-java)
* [Alternating calls](https://github.com/GenesysPureEngage/tutorials/tree/master/alternate-calls-workspace-java)

There is also a [Console Sample](https://github.com/GenesysPureEngage/console-agent-app-java) that provides a command line to test and play with the API.

# Related Links
* [Provisioning API for Java](https://github.com/GenesysPureEngage/provisioning-client-java)
* [Authorization API for Java](https://github.com/GenesysPureEngage/authorization-client-java)


# Quick Start

## Initialization

```java
// Create a new instance providing the url and api key.
WorkspaceApi api = new WorkspaceApi(apiKey, baseUrl, debugEnabled);

// Register handlers to run on DN and call related events
this.api.addCallEventListener(msg -> {
    System.out.println("CallStateChanged! [" + msg.getCall().getState() + "]");
});
this.api.addDnEventListener(msg -> {
    System.out.println("DnStateChanged! [" + msg.getDn().getAgentState() + "]");
});
this.api.addErrorEventListener(msg -> {
    System.out.println("EventError: " + msg.getMessage() + " - code [" + msg.getCode() + "]");
});

// Initialize the API providing authCode. How to obtain an auth code
// is explained separately.
CompletableFuture<User> future = api.initialize(authCode, redirectUri);

// After the API is initialized, user details are available.
User user = future.get();

String agentLogin = user.getAgentId();
String defaultPlace = user.getDefaultPlace();

```

## Activating the Voice Channel

After the API has been initialized, the next step is to activate the voice channel. 

```java

// Activate the voice channel providing the agentId
// and dn to be used for login.
api.activateChannels(agentId, dn);

// After the process completes a DnStateChanged message will be published.

```

## Agent State

```java
  // Ready
  api.ready();
  
  // NotReady with optional workmode and reason
  api.notReady();
  api.notReady(workMode, reasonCode);
  
  // DND
  api.dndOn();
  api.dndOff();
```


## Basic Call Control

```java
  // Make a new call
  api.makeCall(destination);
  
  // Answer
  api.answerCall(connId);
  
  // Hold and retrieve
  api.holdCall(connId);
  api.retrieveCall(connId);
  
  // Release
  api.releaseCall(connId);
  
  // Send DTMF tones
  api.sendDTMF(connId, digits);
```

## Conference and Transfers

```java
  // Two-step transfer
  api.initiateTransfer(connId, destination);
  api.completerTransfer(connId, parentConnId);
  
  // Two-step conference
  api.initiateConference(connId, destination);
  api.completeConference(connId, parentConnId);
  
  // Delete a participant
  api.deleteFromConference(connId, dnToDrop);
  
  // Single-step versions
  api.singleStepTransfer(connId, destination);
  api.singleStepConference(connId, destination);
```

## UserData

```java
  // Attach
  api.attachUserData(connId, userData);
  
  // Update
  api.updateUserData(connId, userData);
  
  // Delete a key
  api.deleteUserDataPair(connId, key);
```

## Call Recording

```java
  // Start and stop
  api.startRecording(connId);
  api.stopRecording(connId);
  
  // Pause and resume
  api.pauseRecording(connId);
  api.resumeRecording(connId);

```


