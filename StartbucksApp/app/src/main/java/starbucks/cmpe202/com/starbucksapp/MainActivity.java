package starbucks.cmpe202.com.starbucksapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

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

        Intent intent = new Intent(this, MyCardsActivity.class);
        startActivity(intent);
    }

    public void btSignupHandler(View v) {
        System.out.println("btSignup");
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

}