package starbucks.cmpe202.com.starbucksapp;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import io.gloxey.gnm.interfaces.VolleyResponse;
import io.gloxey.gnm.managers.ConnectionManager;

public class AddCardActivity extends AppCompatActivity {

    //TextView inputCardNumber;
    //TextView inputCardCode;
    String userid = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard);

        //inputCardNumber = findViewById(R.id.inputCardNumber);
        //inputCardCode = findViewById(R.id.inputCardCode);

        //userid = "TT";
        userid = UserManager.getInstance().getUser().getId();
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("on Resume");
    }

//Post
    public void btAddCardHandler(View v) {
        System.out.println("btAddCardHandler");
        String baseuri = getString(R.string.api_uri);
        final TextView tvCardId = (TextView) findViewById(R.id.inputCardNumber);
        final TextView tvCardCode = (TextView) findViewById(R.id.inputCardCode);
        final TextView tvAmount = (TextView) findViewById(R.id.inputAmount);
        String CardIdval = tvCardId.getText().toString();
        String CardCodeval = tvCardCode.getText().toString();
        String CardAmountVal = tvAmount.getText().toString();



        //Reference doc https://github.com/adnanbinmustafa/Gloxey-Network-Manager#1-volley-stringrequest
        String url = baseuri+"/addcard";

        HashMap<String, String> params = new HashMap<>();
        params.put("cardid", CardIdval);
        params.put("cardcode", CardCodeval);
        params.put("cardvalue", CardAmountVal);
        params.put("userid", userid);


        ConnectionManager.volleyStringRequest(this, true, null, url, Request.Method.POST, params, new VolleyResponse() {
            @Override
            public void onResponse(String response) {
                Log.e("Response is: ", response);
                Toast.makeText(getApplicationContext(), "Response is: "+ response,
                        Toast.LENGTH_SHORT).show();
                /**
                 * Handle Response
                 */
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response is: ", error.toString());
                Toast.makeText(getApplicationContext(), "Response Error: "+ error.toString(),
                        Toast.LENGTH_SHORT).show();
                /**
                 * handle Volley Error
                 */
            }

            @Override
            public void isNetwork(boolean connected) {
               // Log.e("Network is: ", connected);
                /**
                 * True if internet is connected otherwise false
                 */
            }
        });

    }


}
