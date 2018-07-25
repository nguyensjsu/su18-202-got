package starbucks.cmpe202.com.starbucksapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import io.gloxey.gnm.interfaces.VolleyResponse;
import io.gloxey.gnm.managers.ConnectionManager;

public class MyCardsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycards);

        // Get card Activities
        String baseuri = getString(R.string.api_uri);
        String url = baseuri+"/getcardshistory?userid=TT";
        ConnectionManager.volleyStringRequest(this, true, null, url, new VolleyResponse() {
            @Override
            public void onResponse(String response) {
                Log.e("Response is: ", response.substring(0,500));
                Toast.makeText(getApplicationContext(), "Response is: "+ response.substring(0,500),
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

}
