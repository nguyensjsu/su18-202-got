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

public class MainActivity extends AppCompatActivity {

    TextView inputEmail;
    TextView inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("on Resume");
    }


    public void btLoginHandler(View v) {
        System.out.println("btLoginHandler");


        if (inputEmail.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter a e-mail", Toast.LENGTH_LONG).show();
            return ;
        }
        if (inputPassword.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter a password", Toast.LENGTH_LONG).show();
            return ;
        }

//        String url = baseuri+"/addcard";
//
//        HashMap<String, String> params = new HashMap<>();
//        params.put("cardid", CardIdval);
//        params.put("cardcode", CardCodeval);
//        params.put("cardvalue", CardAmountVal);
//        params.put("userid", userid);
//
//
//        ConnectionManager.volleyStringRequest(this, true, null, url, Request.Method.POST, params, new VolleyResponse() {
//            @Override
//            public void onResponse(String response) {
//                Log.e("Response is: ", response);
//                Toast.makeText(getApplicationContext(), "Response is: "+ response,
//                        Toast.LENGTH_SHORT).show();
//                /**
//                 * Handle Response
//                 */
//            }
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("Response is: ", error.toString());
//                Toast.makeText(getApplicationContext(), "Response Error: "+ error.toString(),
//                        Toast.LENGTH_SHORT).show();
//                /**
//                 * handle Volley Error
//                 */
//            }
//
//            @Override
//            public void isNetwork(boolean connected) {
//                // Log.e("Network is: ", connected);
//                /**
//                 * True if internet is connected otherwise false
//                 */
//            }
//        });


        Intent intent = new Intent(this, MyCardsActivity.class);
        startActivity(intent);
    }

    public void btSignupHandler(View v) {
        System.out.println("btSignup");
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

}
