package starbucks.cmpe202.com.starbucksapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("on Resume");
    }


    public void btSignUpHandler(View v) {
        System.out.println("btSignUpHandler");

        // TODO: Make API request to register user


        // TODO: Redirect user to MyCards if registration is successful
        Intent intent = new Intent(this, MyCardsActivity.class);
        startActivity(intent);
    }

}
