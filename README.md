# workspace-client-java
A Java library to interface with the Workspace API 

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

// Set the agent state to ready, which will result in
// a DnStateChanged message.
api.setAgentReady();

// Set the agent state to not-ready. This will also result
// in a DnStateChanged message.
api.setAgentNotReady();

```

## Basic Call Control

```java
  
// Make a new call to DN 5001. This will result in a CallStateChange message
// being published.
api.makeCall("5001");

```


