package starbucks.cmpe202.com.starbucksapp.server;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;

public abstract class AbstractCustomJsonHttpResponseHandler extends JsonHttpResponseHandler {
	@Override
	public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
		// TODO Auto-generated method stub
		super.onSuccess(statusCode, headers, response);
		try {
			if (!response.getBoolean("success")) {
				if (response.has("error_code") && response.getInt("error_code")==-1 ) {
					onInvalidArgs(statusCode, headers, response);
				} else {
					onCheckedFailure(statusCode, headers, response);
				}
			} else {
				onCheckedSuccess(statusCode, headers, response);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public abstract void onInvalidArgs(int statusCode, Header[] headers, JSONObject response);

	public abstract void onCheckedSuccess(int statusCode, Header[] headers, JSONObject response);

	public abstract void onCheckedFailure(int statusCode, Header[] headers, JSONObject response);
}
