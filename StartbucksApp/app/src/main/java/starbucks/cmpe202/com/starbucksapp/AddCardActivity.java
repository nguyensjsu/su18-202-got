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

public class AddCardActivity extends AppCompatActivity {

    TextView inputCardNumber;
    TextView inputCardCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard);

        inputCardNumber = findViewById(R.id.inputCardNumber);
        inputCardCode = findViewById(R.id.inputCardCode);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("on Resume");
    }


    public void btAddCardHandler(View v) {
        System.out.println("btAddCardHandler");
        final TextView mTextView = (TextView) findViewById(R.id.tvmessage);
        //TODO: Check for valid inputs

        //new RestPostAsync().execute("ddg");

        //Refrence: https://developer.android.com/training/volley/request
        String baseuri = getString(R.string.api_uri);
        String url = baseuri+"/getall";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.e("Response is: ", response.substring(0,500));
                        Toast.makeText(getApplicationContext(), "Response is: "+ response.substring(0,500),
                                Toast.LENGTH_SHORT).show();
                        //mTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response is: ", error.toString());
                Toast.makeText(getApplicationContext(), "Response Error: "+ error.toString(),
                        Toast.LENGTH_SHORT).show();
                //mTextView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        requestQueue.add(stringRequest);
/*
        Utility util = new Utility();
        HashMap<String, String> postDataParams = new HashMap<String, String>();
        postDataParams.put("cardid","123456789");//cardid=2222222222&cardcode=222&cardvalue=20.5&userid=mm
        postDataParams.put("cardcode","123");
        postDataParams.put("cardvalue","20");
        postDataParams.put("userid","TT");
        String ret = util.performPostCall("http://54.160.241.16:8080/addcard", postDataParams);
        Toast.makeText(AddCardActivity.this, "Output= "+ ret, Toast.LENGTH_SHORT).show();
*/
    }

}
