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

public class SignUpActivity extends AppCompatActivity {

    TextView inputName;
    TextView inputEmail;
    TextView inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("on Resume");
    }



    public void registerUser(final String token){

        String name = inputName.getText().toString();
        String email = inputEmail.getText().toString();

        String url = Server.ENDPOINT_USER + "/updateUserAccount.php";

        HashMap<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("token", token);
        params.put("apiKey", "cmpe202kungfubelikewater");


        ConnectionManager.volleyStringRequest(this, true, null, url, Request.Method.POST, params,new VolleyResponse() {

            @Override
            public void onResponse (String response){
                Log.d("response", response);

                final UserModel user = new Gson().fromJson(response, UserModel.class);

                if (user.getId() == null) {
                    final ApiErrorResponseModel error = new Gson().fromJson(response, ApiErrorResponseModel.class);
                    Toast.makeText(getApplicationContext(), "Response Error: "+ error.errorMessage, Toast.LENGTH_SHORT).show();
                    return ;
                }

                Log.d("USER", user.getId());
                Log.d("USER", user.getEmail());
                UserManager.getInstance().getUser().setUser(user);
                UserManager.getInstance().getUser().setToken(token);

                Log.d("UserManager", UserManager.getInstance().getUser().getId());
                Log.d("UserManager", UserManager.getInstance().getUser().getEmail());

                Intent intent = new Intent(getApplicationContext(), MyCardsActivity.class);
                startActivity(intent);

            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response is: ", error.toString());
                Toast.makeText(getApplicationContext(), "Response Error: "+ error.toString(), Toast.LENGTH_SHORT).show();
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




    public void btSignUpHandler(View v) {
        System.out.println("btSignUpHandler");

        //Check for valid inputs
        String name = inputName.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (name.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter a name", Toast.LENGTH_LONG).show();
            return ;
        }
        if (email.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter a e-mail", Toast.LENGTH_LONG).show();
            return ;
        }
        if (password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter a password", Toast.LENGTH_LONG).show();
            return ;
        }


        // Make API request to register user

        String url = Server.ENDPOINT_AUTH + "signup";


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

                final AuthResponseModel authResponse = new Gson().fromJson(response, AuthResponseModel.class);
                Log.e("Token is: ", authResponse.token);

                registerUser(authResponse.token);

                //UserManager.getInstance().getUser().setId("5b593fcc7df93600145bce42");


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

}
