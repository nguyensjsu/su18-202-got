package starbucks.cmpe202.com.starbucksapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class RewardsActivity extends AppCompatActivity {

    private TextView tvRewardPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        tvRewardPoints = findViewById(R.id.tvRewardPoints);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("on Resume");
    }


}
