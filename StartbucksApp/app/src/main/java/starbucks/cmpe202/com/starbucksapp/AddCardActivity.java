package starbucks.cmpe202.com.starbucksapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

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


        // TODO: Make API request to register user


        // TODO: Redirect user to MyCards if registration is successful
        Intent intent = new Intent(this, MyCardsActivity.class);
        startActivity(intent);

        // TODO: If error, show Toast
    }

}
