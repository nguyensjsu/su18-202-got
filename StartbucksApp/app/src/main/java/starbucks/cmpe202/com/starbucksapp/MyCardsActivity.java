package starbucks.cmpe202.com.starbucksapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MyCardsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycards);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("on Resume");
    }


    public void btHandler(View v) {
        System.out.println("btHandler");
    }

}
