package com.animo.jRest.util;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * An invocation of a APIHelper method that sends a request to a webserver and returns a response.
 * Each call yields its own HTTP request and response pair
 * 
 * <p>Calls may be executed synchronously with {@link #callMeNow}, or asynchronously with {@link
 * #callMeLater}. 
 * 
 * @author animo
 *
 * @param <Request>
 * @param <Response>
 */

public class APICall<Request,Response> {
    private Response responseBody;
    private int responseCode;
    private RequestBean<Request>  requestBean;
    private Map<String, List<String>> responseHeaders;
    private Type type;

    public Map<String, List<String>> getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(Map<String, List<String>> responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

	public RequestBean<Request> getRequestBean() {
        return requestBean;
    }

    public void setRequestBean(RequestBean<Request> requestBean) {
        this.requestBean = requestBean;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Response getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Response responseBody) {
        this.responseBody = responseBody;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
    
    /**
     * Synchronous implementation of {@link com.animo.jRest.util.APICall APICall} , which invokes a blocking call to webserver
     * . And waits for the APICall to complete
     * @return {@code APICall}
     * @throws Exception
     */
    public APICall<Request,Response> callMeNow() throws Exception{

        APIAsyncTask<Request,Response> asyncTask = new APIAsyncTask<>(requestBean,type);
        return asyncTask.executeNow(requestBean);
    }
    
    /**
     * Asynchronous implementation of {@link com.animo.jRest.util.APICall APICall} , which invokes a non-blocking call to webserver
     * . It accepts {@link com.animo.jRest.util.APICallBack APICallBack} as a parameter
     * @param callBack
     * @throws Exception
     */
    public void callMeLater(APICallBack<Request, Response> callBack) throws Exception {
    	APIAsyncTask<Request,Response> asyncTask = new APIAsyncTask<>(requestBean,type,callBack);
        asyncTask.executeLater(requestBean);
    }

}
