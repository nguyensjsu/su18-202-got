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
import com.google.gson.Gson;

import org.json.JSONObject;

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

        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (email.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter a e-mail", Toast.LENGTH_LONG).show();
            return ;
        }
        if (password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter a password", Toast.LENGTH_LONG).show();
            return ;
        }

        //Server.login(getApplicationContext(), inputEmail.getText().toString(), inputPassword.getText().toString());

        String url = Server.ENDPOINT_AUTH + "signin";


        final JSONObject object = new JSONObject ();
        try {
            object.put("email", email);
            object.put("password", password);
        } catch (Exception ex) {

        }



        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        ConnectionManager.volleyJSONRequest(this, true, null, url, Request.Method.POST, object, headers, new VolleyResponse() {

            @Override
            public void onResponse(String response) {
                Log.e("Response is: ", response);
                //Toast.makeText(getApplicationContext(), "Response is: "+ response, Toast.LENGTH_SHORT).show();

                AuthResponseModel authResponse = new Gson().fromJson(response, AuthResponseModel.class);
                Log.e("Token is: ", authResponse.token);

                UserManager.getInstance().getUser().setId("5b593fcc7df93600145bce42");

                Intent intent = new Intent(getApplicationContext(), MyCardsActivity.class);
                startActivity(intent);

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
                //Log.e("Network is: ", connected);
                /**
                 * True if internet is connected otherwise false
                 */
            }
        });




    }

    public void btSignupHandler(View v) {
        System.out.println("btSignup");
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

}
