package starbucks.cmpe202.com.starbucksapp.server;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.util.Log;
import br.com.codezone.meuesporte.app.ContextHolder;
import br.com.codezone.meuesporte.utils.Helper;

public class StarbucksClient {

    private static AsyncHttpClient client = new AsyncHttpClient();
    private static String TAG = "API";
    private static int DEFAULT_TIMEOUT = 50000;

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        attachCredentials( params );
        Log.d(TAG, "Requesting " + getAbsoluteUrl(url) + ", params = " + params);
        client.setTimeout(DEFAULT_TIMEOUT);
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
    	if (params == null) {
    		params = new RequestParams();
    	}
        client.setTimeout(DEFAULT_TIMEOUT);
        client.post(getAbsoluteUrl(url), attachCredentials(params), responseHandler);
        Log.d(TAG, "Requesting " + getAbsoluteUrl(url) + ", params = " + params);
    }

    public static String getAbsoluteUrl(String relativeUrl) {
        return relativeUrl;//Constants.serverUrl +
    }


    private static RequestParams attachCredentials( RequestParams params ){
        if ( params == null ){
            params = new RequestParams();
        }
        if (!Helper.getToken(ContextHolder.getInstance().getContext()).equals("")) {
        	params.put("token", Helper.getToken(ContextHolder.getInstance().getContext()));
        }
        if (!Helper.getUserId(ContextHolder.getInstance().getContext()).equals("")) {
        	params.put("user_id", Helper.getUserId(ContextHolder.getInstance().getContext()));
        }
        return params;
    }
}
