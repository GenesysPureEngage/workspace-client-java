/*
 * Workspace API
 * Agent API
 *
 * OpenAPI spec version: v9.0.000.20.2204
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.genesys.internal.workspace.api;

import com.genesys.internal.common.ApiCallback;
import com.genesys.internal.common.ApiClient;
import com.genesys.internal.common.ApiException;
import com.genesys.internal.common.ApiResponse;
import com.genesys.internal.common.Configuration;
import com.genesys.internal.common.Pair;
import com.genesys.internal.common.ProgressRequestBody;
import com.genesys.internal.common.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import com.genesys.internal.workspace.model.ApiErrorResponse;
import com.genesys.internal.workspace.model.ApiSuccessResponse;
import java.math.BigDecimal;
import com.genesys.internal.workspace.model.Data;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersApi {
    private ApiClient apiClient;

    public UsersApi() {
        this(Configuration.getDefaultApiClient());
    }

    public UsersApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for getUsers
     * @param searchTerm The text to search. (optional)
     * @param groupId The ID of the group where the user belongs. (optional)
     * @param sort The sort order, either &#x60;asc&#x60; (ascending) or &#x60;desc&#x60; (descending). The default is &#x60;asc&#x60;. (optional)
     * @param limit Number of results to return. The default value is 100. (optional)
     * @param offset The offset to start from in the results. The default value is 0. (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getUsersCall(String searchTerm, BigDecimal groupId, String sort, BigDecimal limit, BigDecimal offset, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/users";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (searchTerm != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("searchTerm", searchTerm));
        if (groupId != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("groupId", groupId));
        if (sort != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("sort", sort));
        if (limit != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("limit", limit));
        if (offset != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("offset", offset));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getUsersValidateBeforeCall(String searchTerm, BigDecimal groupId, String sort, BigDecimal limit, BigDecimal offset, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        

        com.squareup.okhttp.Call call = getUsersCall(searchTerm, groupId, sort, limit, offset, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Search for users.
     * Search for users with the specified filters.
     * @param searchTerm The text to search. (optional)
     * @param groupId The ID of the group where the user belongs. (optional)
     * @param sort The sort order, either &#x60;asc&#x60; (ascending) or &#x60;desc&#x60; (descending). The default is &#x60;asc&#x60;. (optional)
     * @param limit Number of results to return. The default value is 100. (optional)
     * @param offset The offset to start from in the results. The default value is 0. (optional)
     * @return ApiSuccessResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiSuccessResponse getUsers(String searchTerm, BigDecimal groupId, String sort, BigDecimal limit, BigDecimal offset) throws ApiException {
        ApiResponse<ApiSuccessResponse> resp = getUsersWithHttpInfo(searchTerm, groupId, sort, limit, offset);
        return resp.getData();
    }

    /**
     * Search for users.
     * Search for users with the specified filters.
     * @param searchTerm The text to search. (optional)
     * @param groupId The ID of the group where the user belongs. (optional)
     * @param sort The sort order, either &#x60;asc&#x60; (ascending) or &#x60;desc&#x60; (descending). The default is &#x60;asc&#x60;. (optional)
     * @param limit Number of results to return. The default value is 100. (optional)
     * @param offset The offset to start from in the results. The default value is 0. (optional)
     * @return ApiResponse&lt;ApiSuccessResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<ApiSuccessResponse> getUsersWithHttpInfo(String searchTerm, BigDecimal groupId, String sort, BigDecimal limit, BigDecimal offset) throws ApiException {
        com.squareup.okhttp.Call call = getUsersValidateBeforeCall(searchTerm, groupId, sort, limit, offset, null, null);
        Type localVarReturnType = new TypeToken<ApiSuccessResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Search for users. (asynchronously)
     * Search for users with the specified filters.
     * @param searchTerm The text to search. (optional)
     * @param groupId The ID of the group where the user belongs. (optional)
     * @param sort The sort order, either &#x60;asc&#x60; (ascending) or &#x60;desc&#x60; (descending). The default is &#x60;asc&#x60;. (optional)
     * @param limit Number of results to return. The default value is 100. (optional)
     * @param offset The offset to start from in the results. The default value is 0. (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getUsersAsync(String searchTerm, BigDecimal groupId, String sort, BigDecimal limit, BigDecimal offset, final ApiCallback<ApiSuccessResponse> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getUsersValidateBeforeCall(searchTerm, groupId, sort, limit, offset, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<ApiSuccessResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for supervisorRemoteOperation
     * @param operationName Name of state to change to (required)
     * @param data  (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call supervisorRemoteOperationCall(String operationName, Data data, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = data;

        // create path and map variables
        String localVarPath = "/supervisor/voice/{operationName}"
            .replaceAll("\\{" + "operationName" + "\\}", apiClient.escapeString(operationName.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call supervisorRemoteOperationValidateBeforeCall(String operationName, Data data, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'operationName' is set
        if (operationName == null) {
            throw new ApiException("Missing the required parameter 'operationName' when calling supervisorRemoteOperation(Async)");
        }
        

        com.squareup.okhttp.Call call = supervisorRemoteOperationCall(operationName, data, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Logout user remotely for supervisors
     * 
     * @param operationName Name of state to change to (required)
     * @param data  (optional)
     * @return ApiSuccessResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiSuccessResponse supervisorRemoteOperation(String operationName, Data data) throws ApiException {
        ApiResponse<ApiSuccessResponse> resp = supervisorRemoteOperationWithHttpInfo(operationName, data);
        return resp.getData();
    }

    /**
     * Logout user remotely for supervisors
     * 
     * @param operationName Name of state to change to (required)
     * @param data  (optional)
     * @return ApiResponse&lt;ApiSuccessResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<ApiSuccessResponse> supervisorRemoteOperationWithHttpInfo(String operationName, Data data) throws ApiException {
        com.squareup.okhttp.Call call = supervisorRemoteOperationValidateBeforeCall(operationName, data, null, null);
        Type localVarReturnType = new TypeToken<ApiSuccessResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Logout user remotely for supervisors (asynchronously)
     * 
     * @param operationName Name of state to change to (required)
     * @param data  (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call supervisorRemoteOperationAsync(String operationName, Data data, final ApiCallback<ApiSuccessResponse> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = supervisorRemoteOperationValidateBeforeCall(operationName, data, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<ApiSuccessResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
