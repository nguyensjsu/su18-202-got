package starbucks.cmpe202.com.starbucksapp.server;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.codezone.meuesporte.app.ContextHolder;
import br.com.codezone.meuesporte.utils.Utils;

public class CustomJsonHttpResponseHandler extends AbstractCustomJsonHttpResponseHandler {


	@Override
	public void onInvalidArgs(int statusCode, Header[] headers, JSONObject response) {
		try {
			Utils.show_Toast(ContextHolder.getInstance().getContext(), response.getString("message"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onCheckedSuccess(int statusCode, Header[] headers, JSONObject response) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCheckedFailure(int statusCode, Header[] headers, JSONObject response) {
		try {
			Utils.show_Toast(ContextHolder.getInstance().getContext(), response.getString("message"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
