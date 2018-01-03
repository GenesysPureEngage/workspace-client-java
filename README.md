# Workspace Client Library

The Workspace Client Library is a Java wrapper for the [Workspace API](https://developer.genhtcc.com/api/reference/workspace/index.html) that makes it easier to code against the API. The library provides much of the supporting code needed to make HTTP requests, process HTTP responses, and enable [CometD](https://cometd.org/) messaging.

The library is hosted on [GitHub](https://github.com/GenesysPureEngage/workspace-client-java) and Genesys welcomes pull requests for corrections.

## Install

Genesys recommends that you install the Workspace Client Library JAR file with [Gradle](https://gradle.org/). You should use latest version available in the Maven [repository](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.genesys%22%20AND%20a%3A%22workspace%22).

Add the following line to the **dependencies** block in your **build.gradle** file:

```gradle
compile 'com.genesys:workspace:<latest_version>'
```

## Related links

* The following Workspace API tutorials demonstrate some ways you can use this library:
  * [Changing agent state](https://developer.genhtcc.com/tutorials/voice-ready-workspace-java/)
  * [Basic call control](https://developer.genhtcc.com/tutorials/basic-call-control-workspace-java/)
  * [Conferences](https://developer.genhtcc.com/tutorials/conference-call-workspace-java/)
  * [Transfers](https://developer.genhtcc.com/tutorials/transfer-call-workspace-java/)
  * [Alternating calls](https://developer.genhtcc.com/tutorials/alternate-calls-workspace-java/)
  * [Initialize Workspace](hhttps://developer.genhtcc.com/tutorials/initialize-workspace-java/)
  * [Search for targets](https://developer.genhtcc.com/tutorials/targets-workspace-java/)
  * [Changing user data](https://developer.genhtcc.com/tutorials/user-data-workspace-java/)
* Test and play with the API using the [Console Sample](https://github.com/GenesysPureEngage/console-agent-app-java).
* Learn more about the [Workspace API](https://developer.genhtcc.com/api/reference/workspace/).
* Learn more about the [Workspace Client Library](https://developer.genhtcc.com/api/client-libraries/workspace/).

## Classes

The Workspace Client Library includes two main classes: [VoiceApi](https://developer.genhtcc.com/api/client-libraries/workspace/java/VoiceApi/index.html) and [WorkspaceApi](https://developer.genhtcc.com/api/client-libraries/workspace/java/WorkspaceApi/index.html). These classes represent the resources and events that are part of the Workspace API, along with all the methods you need to access the API functionality.

## Authentication

To use with the Workspace Client Library, the first thing you need to do is authenticate with the [Authentication Client Library](https://developer.genhtcc.com/api/client-libraries/authentication/index.html) (or the [Authentication API](https://developer.genhtcc.com/api/reference/authentication/)).

If you're creating a web-based agent application, Genesys recommends following the [Authorization Code Grant](https://tools.ietf.org/html/rfc6749#section-4.1) flow. This means you need to provide the authorization code and redirect URI when you call the Workspace Client Library's `initialize()` method.

If you follow the [Resource Owner Password Credentials Grant](https://tools.ietf.org/html/rfc6749#section-4.3) flow, you need to provide the access token when you call the Workspace Client Library's `initialize()` method.

## Examples

The examples below give an overview of what the Workspace Client Library looks like. For more information, see the documentation for the library's [VoiceApi](https://developer.genhtcc.com/api/client-libraries/workspace/java/VoiceApi/index.html) and [WorkspaceApi](https://developer.genhtcc.com/api/client-libraries/workspace/java/WorkspaceApi/index.html) classes.

### Initialization

```java
// Create a new instance providing the URL and API key.
WorkspaceApi api = new WorkspaceApi(apiKey, baseUrl, debugEnabled);

// Register handlers to run on DN and call-related events
api.voice().addCallEventListener(msg -> {
    System.out.println("CallStateChanged! [" + msg.getCall().getState() + "]");
});
api.voice().addDnEventListener(msg -> {
    System.out.println("DnStateChanged! [" + msg.getDn().getAgentState() + "]");
});
api.voice().addErrorEventListener(msg -> {
    System.out.println("EventError: " + msg.getMessage() + " - code [" + msg.getCode() + "]");
});

// Initialize the API providing the authCode. See the Authentication section for more details.
CompletableFuture<User> future = api.initialize(authCode, redirectUri);

// After the API is initialized, user details are available.
User user = future.get();

String agentLogin = user.getAgentId();
String defaultPlace = user.getDefaultPlace();
```

### Activating the Voice Channel

After initializing the API, next activate the voice channel.

```java
// Activate the voice channel providing the agentId
// and dn to be used for login.
api.activateChannels(agentId, dn);

// After the process completes, the API publishes a DnStateChanged message.
```

### Agent State

Set the agent's state on the voice channel. The API includes the standard Genesys states: Ready, NotReady, NotReady with workmode and do-not-disturb.

```java
// Ready
api.voice().ready();

// NotReady with optional workmode and reason
api.voice().notReady();
api.voice().notReady(workMode, reasonCode);
api.voice().notReady(workMode, reasonCode, reasons, extensions);

// DND
api.voice().dndOn();
api.voice().dndOff();
```

### Basic Call Control

The Workspace API offers the typical Genesys call control capabilities.

```java
// Make a new call
api.voice().makeCall(destination);
api.voice().makeCall(destination, userData);

// Answer
api.voice().answerCall(connId);

// Hold and retrieve
api.voice().holdCall(connId);
api.voice().retrieveCall(connId);

// Release
api.voice().releaseCall(connId);

// Send DTMF tones
api.voice().sendDTMF(connId, digits);
```

### Conference and Transfers

The API includes both single-step and two-step conferences and transfers.

```java
// Two-step transfer
api.voice().initiateTransfer(connId, destination);
api.voice().completerTransfer(connId, parentConnId);

// Two-step conference
api.voice().initiateConference(connId, destination);
api.voice().completeConference(connId, parentConnId);

// Delete a participant
api.voice().deleteFromConference(connId, dnToDrop);

// Single-step versions
api.voice().singleStepTransfer(connId, destination);
api.voice().singleStepConference(connId, destination);
```

### UserData

You can use the API to manipulate user data for the call.

```java
// Creating
KeyValueCollection userData = new KeyValueCollection();
userData.addString("firstName", "value");
userData.addInt("points", 100);

KeyValueCollection address = new KeyValueCollection();
address.addString("city", "San Francisco");
address.addString("state", "CA");

userData.addList("address", address);

// Attach
api.voice().attachUserData(connId, userData);

// Update
api.voice().updateUserData(connId, userData);

// Delete a key
api.voice().deleteUserDataPair(connId, key);
```

### Call Recording

You can use the API to record voice calls.

```java
// Start and stop
api.voice().startRecording(connId);
api.voice().stopRecording(connId);

// Pause and resume
api.voice().pauseRecording(connId);
api.voice().resumeRecording(connId);
```
