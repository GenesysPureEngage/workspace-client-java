# API Reference
This document contains class documentation for the main API classes.

## WorkspaceApi

###### `public WorkspaceApi( String apiKey, String baseUrl, boolean debugEnabled )`

Constructor

 * **Parameters:**
   * `apiKey` — The API key to be used.
   * `baseUrl` — base URL for the workpace API
   * `debugEnabled` — enable debug (or not)

###### `public CompletableFuture<User> initialize(String authCode, String redirectUri) throws WorkspaceApiException`

Initializes the API using the provided authCode and redirectUri. This is the preferred means of init.

 * **Parameters:**
   * `authCode` — the auth code to be used for initialization
   * `redirectUri` — the redirectUri to be used for initialization. Since this is not being sent by the UI, this just

     needs to match the redirectUri that was sent when obtaining the authCode.

###### `public CompletableFuture<User> initialize(String token) throws WorkspaceApiException`

Initializes the API using the provided auth token.

 * **Parameters:** `token` — The auth token to use for initialization.

###### `public void destroy() throws WorkspaceApiException`

Logout the agent and cleanup resources.

###### `public void activateChannels( String agentId, String placeName ) throws WorkspaceApiException`

Initializes the voice channel using the specified resources.

 * **Parameters:**
   * `agentId` — agentId to be used for login
   * `placeName` — name of the place to use for login

###### `public void activateChannels( String agentId, String dn, String placeName, String queueName ) throws WorkspaceApiException`

Initializes the voice channel using the specified resources.

 * **Parameters:**
   * `agentId` — agentId to be used for login
   * `dn` — DN to be used for login. Provide only one of dn or placeName
   * `placeName` — name of the place to use for login. Provide only one of placeName or dn
   * `queueName` — name of the queue to be used for login. (optional)

###### `public User getUser()`

Returns the current user.

 * **Returns:** the current user.

###### `public boolean debugEnabled()`

Returns the debug flag

 * **Returns:** debug flag

###### `public void setDebugEnabled(boolean debugEnabled)`

Sets the debug flag

 * **Parameters:** `debugEnabled` — debug flag

## VoiceApi

###### `public void addEventUserEventListener(EventUserEventListener listener)`

Adds a listener for EventUserEvent events.

 * **Parameters:** `listener` — the listener to be added.  
 
###### `public void removeEventUserEventListener(EventUserEventListener listener)`

Removes a listener for EventUserEvent events.

 * **Parameters:** `listener` — the listener to be removed.   

###### `public void addCallEventListener(CallEventListener listener)`

Adds a listener for CallStateChanged events.

 * **Parameters:** `listener` — the listener to be added.

###### `public void removeCallEventListener(CallEventListener listener)`

Removes a previously added CallStateChanged listener.

 * **Parameters:** `listener` — the listener to be removed

###### `public void addDnEventListener(DnEventListener listener)`

Add a listener for DnStateChanged events.

 * **Parameters:** `listener` — the listener to be added.

###### `public void removeDnEventListener(DnEventListener listener)`

Remove a previously added DnStateChanged listener.

 * **Parameters:** `listener` — the listener to be removed.

###### `public void addErrorEventListener(ErrorEventListener listener)`

Add a listener for EventError

 * **Parameters:** `listener` — the listener to be added.

###### `public void removeErrorEventListener(ErrorEventListener listener)`

Remove a previously added EventError listener.

 * **Parameters:** `listener` — the listener to be removed.

###### `public Collection<Call> getCalls()`

Gets the list of active calls.

 * **Returns:** list of active calls

###### `public void setAgentReady() throws WorkspaceApiException`

Set the agent state to ready.

###### `public void setAgentReady( KeyValueCollection reasons, KeyValueCollection extensions ) throws WorkspaceApiException`

Set the agent state to ready.

 * **Parameters:**
   * `reasons` — reasons
   * `extensions` — extensions

###### `public void setAgentNotReady() throws WorkspaceApiException`

Set the agent state to not ready.

###### `public void setAgentNotReady(String workMode, String reasonCode) throws WorkspaceApiException`

Set the agent state to not ready.

 * **Parameters:**
   * `workMode` — optional workMode to use in the request.
   * `reasonCode` — optional reasonCode to use in the request.

###### `public void setAgentNotReady( String workMode, String reasonCode, KeyValueCollection reasons, KeyValueCollection extensions ) throws WorkspaceApiException`

Set the agent state to not ready.

 * **Parameters:**
   * `workMode` — optional workMode to use in the request.
   * `reasonCode` — optional reasonCode to use in the request.
   * `reasons` — reasons
   * `extensions` — extensions

###### `public void dndOn() throws WorkspaceApiException`

Set do-not-disturb on for voice.

###### `public void dndOff() throws WorkspaceApiException`

Set do-not-disturb off for voice.

###### `public void login() throws WorkspaceApiException`

Login the voice channel.

###### `public void logout() throws WorkspaceApiException`

Logout the voice channel.

###### `public void setForward(String destination) throws WorkspaceApiException`

Set call forwarding to the specififed destination.

 * **Parameters:** `destination` — - destination to forward calls to.

###### `public void cancelForward() throws WorkspaceApiException`

Cancel call forwarding.

###### `public void makeCall(String destination) throws WorkspaceApiException`

Make a new call to the specified destination.

 * **Parameters:** `destination` — The destination to call

###### `public void makeCall( String destination, KeyValueCollection userData ) throws WorkspaceApiException`

Make a new call to the specified destination.

 * **Parameters:**
   * `destination` — The destination to call
   * `userData` — userData to be included with the new call

###### `public void makeCall( String destination, KeyValueCollection userData, KeyValueCollection reasons, KeyValueCollection extensions ) throws WorkspaceApiException`

Make a new call to the specified destination.

 * **Parameters:**
   * `destination` — The destination to call
   * `userData` — userData to be included with the new call
   * `reasons` — reasons
   * `extensions` — extensions

###### `public void answerCall(String connId) throws WorkspaceApiException`

Answer call.

 * **Parameters:** `connId` — The connId of the call to answer.

###### `public void answerCall( String connId, KeyValueCollection reasons, KeyValueCollection extensions ) throws WorkspaceApiException`

Answer call.

 * **Parameters:**
   * `connId` — The connId of the call to answer.
   * `reasons` — reasons
   * `extensions` — extensions

###### `public void holdCall(String connId) throws WorkspaceApiException`

Place call on hold.

 * **Parameters:** `connId` — The connId of the call to place on hold.

###### `public void holdCall( String connId, KeyValueCollection reasons, KeyValueCollection extensions ) throws WorkspaceApiException`

Place call on hold.

 * **Parameters:**
   * `connId` — The connId of the call to place on hold.
   * `reasons` — reasons
   * `extensions` — extensions

###### `public void retrieveCall(String connId) throws WorkspaceApiException`

Retrieve call from hold.

 * **Parameters:** `connId` — The connId of the call to retrieve.

###### `public void retrieveCall( String connId, KeyValueCollection reasons, KeyValueCollection extensions ) throws WorkspaceApiException`

Retrieve call from hold.

 * **Parameters:**
   * `connId` — The connId of the call to retrieve.
   * `reasons` — reasons
   * `extensions` — extensions

###### `public void releaseCall(String connId) throws WorkspaceApiException`

Release call.

 * **Parameters:** `connId` — The connId of the call to release

###### `public void releaseCall( String connId, KeyValueCollection reasons, KeyValueCollection extensions ) throws WorkspaceApiException`

Release call.

 * **Parameters:**
   * `connId` — The connId of the call to release
   * `reasons` — reasons
   * `extensions` — extensions

###### `public void initiateConference(String connId, String destination) throws WorkspaceApiException`

Initiate a conference to the specified destination.

 * **Parameters:**
   * `connId` — The connId of the call to start the conference from.
   * `destination` — The destination

###### `public void initiateConference( String connId, String destination, KeyValueCollection userData ) throws WorkspaceApiException`

Initiate a conference to the specified destination.

 * **Parameters:**
   * `connId` — The connId of the call to start the conference from.
   * `destination` — The destination
   * `userData` — userdata to be used for the new consult call.

###### `public void initiateConference( String connId, String destination, String location, String outboundCallerId, KeyValueCollection userData, KeyValueCollection reasons, KeyValueCollection extensions ) throws WorkspaceApiException`

Initiate a conference to the specified destination.

 * **Parameters:**
   * `connId` — The connId of the call to start the conference from.
   * `destination` — The destination
   * `location` — 
   * `outboundCallerId` — 
   * `userData` — userdata to be used for the new consult call.
   * `reasons` — reasons
   * `extensions` — extensions

###### `public void completeConference(String connId, String parentConnId) throws WorkspaceApiException`

Complete a previously initiated conference identified by the provided ids.

 * **Parameters:**
   * `connId` — The id of the consule call (established)
   * `parentConnId` — The id of the parent call (held).

###### `public void completeConference( String connId, String parentConnId, KeyValueCollection reasons, KeyValueCollection extensions ) throws WorkspaceApiException`

Complete a previously initiated conference identified by the provided ids.

 * **Parameters:**
   * `connId` — The id of the consule call (established)
   * `parentConnId` — The id of the parent call (held).
   * `reasons` — reasons
   * `extensions` — extensions

###### `public void initiateTransfer(String connId, String destination) throws WorkspaceApiException`

Initiate a transfer to the specified destination.

 * **Parameters:**
   * `connId` — The connId of the call to be transferred.
   * `destination` — The destination of the transfer.

###### `public void initiateTransfer( String connId, String destination, KeyValueCollection userData ) throws WorkspaceApiException`

Initiate a transfer to the specified destination.

 * **Parameters:**
   * `connId` — The connId of the call to be transferred.
   * `destination` — The destination of the transfer.
   * `userData` — userdata to be included with the new consult call

###### `public void initiateTransfer( String connId, String destination, String location, String outboundCallerId, KeyValueCollection userData, KeyValueCollection reasons, KeyValueCollection extensions ) throws WorkspaceApiException`

Initiate a transfer to the specified destination.

 * **Parameters:**
   * `connId` — The connId of the call to be transferred.
   * `destination` — The destination
   * `location` — 
   * `outboundCallerId` — 
   * `userData` — userdata to be used for the new consult call.
   * `reasons` — reasons
   * `extensions` — extensions

###### `public void completeTransfer(String connId, String parentConnId) throws WorkspaceApiException`

Complete a previously initiated transfer using the provided ids.

 * **Parameters:**
   * `connId` — The id of the consult call (established)
   * `parentConnId` — The id of the parent call (held)

###### `public void completeTransfer( String connId, String parentConnId, KeyValueCollection reasons, KeyValueCollection extensions ) throws WorkspaceApiException`

Complete a previously initiated transfer using the provided ids.

 * **Parameters:**
   * `connId` — The id of the consult call (established)
   * `parentConnId` — The id of the parent call (held)
   * `reasons` — reasons
   * `extensions` — extensions

###### `public void alternateCalls(String connId, String heldConnId) throws WorkspaceApiException`

Alternate two calls retrieving the held call and placing the established call on hold. This is a shortcut for doing hold and retrieve separately.

 * **Parameters:**
   * `connId` — The id of the established call.
   * `heldConnId` — The id of the held call.

###### `public void alternateCalls( String connId, String heldConnId, KeyValueCollection reasons, KeyValueCollection extensions ) throws WorkspaceApiException`

Alternate two calls retrieving the held call and placing the established call on hold. This is a shortcut for doing hold and retrieve separately.

 * **Parameters:**
   * `connId` — The id of the established call.
   * `heldConnId` — The id of the held call.
   * `reasons` — reasons
   * `extensions` — extensions

###### `public void deleteFromConference(String connId, String dnToDrop) throws WorkspaceApiException`

Delete a dn from a conference call

 * **Parameters:**
   * `connId` — The connId of the conference
   * `dnToDrop` — The dn number to drop from the conference.

###### `public void deleteFromConference( String connId, String dnToDrop, KeyValueCollection reasons, KeyValueCollection extensions ) throws WorkspaceApiException`

Delete a dn from a conference call

 * **Parameters:**
   * `connId` — The connId of the conference
   * `dnToDrop` — The dn number to drop from the conference.
   * `reasons` — reasons
   * `extensions` — extensions

###### `public void singleStepTransfer(String connId, String destination) throws WorkspaceApiException`

Perform a single-step transfer to the specified destination.

 * **Parameters:**
   * `connId` — The id of the call to transfer.
   * `destination` — The destination to transfer the call to.

###### `public void singleStepTransfer( String connId, String destination, KeyValueCollection userData ) throws WorkspaceApiException`

Perform a single-step transfer to the specified destination.

 * **Parameters:**
   * `connId` — The id of the call to transfer.
   * `destination` — The destination to transfer the call to.
   * `userData` — userdata to be included on the transfer

###### `public void singleStepTransfer( String connId, String destination, String location, KeyValueCollection userData, KeyValueCollection reasons, KeyValueCollection extensions ) throws WorkspaceApiException`

Perform a single-step transfer to the specified destination.

 * **Parameters:**
   * `connId` — The id of the call to transfer.
   * `destination` — The destination to transfer the call to.
   * `location` — 
   * `userData` — userdata to be used for the new consult call.
   * `reasons` — reasons
   * `extensions` — extensions

###### `public void singleStepConference(String connId, String destination) throws WorkspaceApiException`

Perform a single-step conference to the specififed destination. This will effectively add the destination to the existing call, creating a conference if necessary.

 * **Parameters:**
   * `connId` — The id of the call to conference.
   * `destination` — The destination to be added to the call.

###### `public void singleStepConference( String connId, String destination, KeyValueCollection userData ) throws WorkspaceApiException`

Perform a single-step conference to the specififed destination. This will effectively add the destination to the existing call, creating a conference if necessary.

 * **Parameters:**
   * `connId` — The id of the call to conference.
   * `destination` — The destination to be added to the call.
   * `userData` — userdata to be included with the request

     <p>

###### `public void singleStepConference( String connId, String destination, String location, KeyValueCollection userData, KeyValueCollection reasons, KeyValueCollection extensions ) throws WorkspaceApiException`

Perform a single-step conference to the specififed destination. This will effectively add the destination to the existing call, creating a conference if necessary.

 * **Parameters:**
   * `connId` — The id of the call to conference.
   * `destination` — The destination to be added to the call.
   * `location` — 
   * `userData` — userdata to be included with the request
   * `reasons` — reasons
   * `extensions` — extensions

###### `public void attachUserData(String connId,KeyValueCollection userData) throws WorkspaceApiException`

Attach the provided data to the call. This adds the data to the call even if data already exists with the provided keys.

 * **Parameters:**
   * `connId` — The id of the call to attach data to.
   * `userData` — The data to attach to the call. This is an array of objects with the properties key, type, and value.

###### `public void updateUserData(String connId, KeyValueCollection userData) throws WorkspaceApiException`

Update call data with the provided key/value pairs. This will replace any existing kvpairs with the same keys.

 * **Parameters:**
   * `connId` — The id of the call to update data for.
   * `userData` — The data to update. This is an array of objecvts with the properties key, type, and value.

###### `public void deleteUserDataPair(String connId, String key) throws WorkspaceApiException`

Delete data with the specified key from the call.

 * **Parameters:**
   * `connId` — The call to remove data from.
   * `key` — The key to remove.

###### `public void sendDTMF(String connId, String digits) throws WorkspaceApiException`

Send DTMF digits to the specififed call.

 * **Parameters:**
   * `connId` — The call to send DTMF digits to.
   * `digits` — The DTMF digits to send.

###### `public void sendDTMF( String connId, String digits, KeyValueCollection reasons, KeyValueCollection extensions ) throws WorkspaceApiException`

Send DTMF digits to the specififed call.

 * **Parameters:**
   * `connId` — The call to send DTMF digits to.
   * `digits` — The DTMF digits to send.
   * `reasons` — reasons
   * `extensions` — extensions

###### `public void sendUserEvent(KeyValueCollection userData) throws WorkspaceApiException`

Send EventUserEvent with the provided data.

 * **Parameters:** `userData` — The data to be sent. This is an array of objects with the properties key, type, and value.

###### `public void sendUserEvent(KeyValueCollection userData, String callUuid) throws WorkspaceApiException`

Send EventUserEvent with the provided data.

 * **Parameters:**
   * `userData` — The data to be sent. This is an array of objects with the properties key, type, and value.
   * `callUuid` — The callUuid that the event will be associated with.

###### `public void redirectCall(String connId, String destination) throws WorkspaceApiException`

Redirect call to the specified destination

 * **Parameters:**
   * `connId` — The connId of the call to redirect.
   * `destination` — The destination to redirect the call to.

###### `public void redirectCall( String connId, String destination, KeyValueCollection reasons, KeyValueCollection extensions ) throws WorkspaceApiException`

Redirect call to the specified destination

 * **Parameters:**
   * `connId` — The connId of the call to redirect.
   * `destination` — The destination to redirect the call to.
   * `reasons` — reasons
   * `extensions` — extensions

###### `public void mergeCalls(String connId, String otherConnId) throws WorkspaceApiException`

Merge the two specified calls.

 * **Parameters:**
   * `connId` — The id of the first call to be merged.
   * `otherConnId` — The id of the second call to be merged.

###### `public void mergeCalls( String connId, String otherConnId, KeyValueCollection reasons, KeyValueCollection extensions ) throws WorkspaceApiException`

Merge the two specified calls.

 * **Parameters:**
   * `connId` — The id of the first call to be merged.
   * `otherConnId` — The id of the second call to be merged.
   * `reasons` — reasons
   * `extensions` — extensions

###### `public void reconnectCall(String connId, String heldConnId) throws WorkspaceApiException`

Reconnect the specified call. Reconnect releases the established call and retrieves the held call in one step.

 * **Parameters:**
   * `connId` — The id of the established call (will be released)
   * `heldConnId` — The id of the held call (will be retrieved)

###### `public void reconnectCall( String connId, String heldConnId, KeyValueCollection reasons, KeyValueCollection extensions ) throws WorkspaceApiException`

Reconnect the specified call. Reconnect releases the established call and retrieves the held call in one step.

 * **Parameters:**
   * `connId` — The id of the established call (will be released)
   * `heldConnId` — The id of the held call (will be retrieved)
   * `reasons` — reasons
   * `extensions` — extensions

###### `public void clearCall(String connId) throws WorkspaceApiException`

Clear call.

 * **Parameters:** `connId` — The connId of the call to clear

###### `public void clearCall( String connId, KeyValueCollection reasons, KeyValueCollection extensions ) throws WorkspaceApiException`

Clear call.

 * **Parameters:**
   * `connId` — The connId of the call to clear
   * `reasons` — reasons
   * `extensions` — extensions

###### `public void startRecording(String connId) throws WorkspaceApiException`

Start call recording

 * **Parameters:** `connId` — The id of the call to start recording.

###### `public void pauseRecording(String connId) throws WorkspaceApiException`

Pause call recording.

 * **Parameters:** `connId` — The id of the call to pause recording on.

###### `public void resumeRecording(String connId) throws WorkspaceApiException`

Resume call recording.

 * **Parameters:** `connId` — The id of the call to resume recording.

###### `public void stopRecording(String connId) throws WorkspaceApiException`

Stop call recording

 * **Parameters:** `connId` — The id of the call to stop recording.
