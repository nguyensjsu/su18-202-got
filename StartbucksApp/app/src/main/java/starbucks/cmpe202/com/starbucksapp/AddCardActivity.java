package starbucks.cmpe202.com.starbucksapp;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

        //TODO: Check for valid inputs
        Utility util = new Utility();
        HashMap<String, String> postDataParams = new HashMap<String, String>();
        postDataParams.put("cardid","123456789");//cardid=2222222222&cardcode=222&cardvalue=20.5&userid=mm
        postDataParams.put("cardcode","123");
        postDataParams.put("cardvalue","20");
        postDataParams.put("userid","TT");
        String ret = util.performPostCall("http://127.0.0.1:8080/addcard", postDataParams);
        Toast.makeText(AddCardActivity.this, "Output= "+ ret, Toast.LENGTH_SHORT).show();
    }

}
