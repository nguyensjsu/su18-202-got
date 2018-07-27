package starbucks.cmpe202.com.starbucksapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import java.util.HashMap;

import io.gloxey.gnm.interfaces.VolleyResponse;
import io.gloxey.gnm.managers.ConnectionManager;

public class MyCardsActivity extends AppCompatActivity {

    String userid = new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycards);

        userid = UserManager.getInstance().getUser().getId();

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("on Resume");
    }


    public void btAddCardHandler(View v) {
        System.out.println("btAddCardHandler");

        Intent intent = new Intent(this, AddCardActivity.class);
        startActivity(intent);
    }

//Post
    public void btnPayHandler(View v) {
        System.out.println("btnPayHandler");
        String baseuri = getString(R.string.api_uri);
        //final TextView mTextView = (TextView) findViewById(R.id.tvmessage);

        final TextView tvPayAmount = (TextView) findViewById(R.id.inputPayAmount);
        String payAmountVal = tvPayAmount.getText().toString();
        //Reference doc https://github.com/adnanbinmustafa/Gloxey-Network-Manager#1-volley-stringrequest
        String url = baseuri+"/pay";

        HashMap<String, String> params = new HashMap<>();
        params.put("amount", payAmountVal);
        params.put("storecode", "WestfieldMall");
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
    public void btnBalanceHandler(View v) {
        System.out.println("btnBalanceHandler");
        // Get pay Activities
        String baseuri = getString(R.string.api_uri);
        String url = baseuri+"/getbalance?userid="+userid;
        ConnectionManager.volleyStringRequest(this, true, null, url, new VolleyResponse() {
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
    public void btnPayHistoryHandler(View v) {
        System.out.println("btnPayHistoryHandler");
        // Get pay Activities
        String baseuri = getString(R.string.api_uri);
        String url = baseuri+"/getpayhistory?userid="+userid;
        ConnectionManager.volleyStringRequest(this, true, null, url, new VolleyResponse() {
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
    public void btnCardHistoryHandler(View v) {
        System.out.println("btnCardHistoryHandler");
        // Get card Activities
        String baseuri = getString(R.string.api_uri);
        String url = baseuri+"/getcardshistory?userid="+userid;
        ConnectionManager.volleyStringRequest(this, true, null, url, new VolleyResponse() {
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
