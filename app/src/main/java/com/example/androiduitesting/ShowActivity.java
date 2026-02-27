package com.example.androiduitesting;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        // Gets references
        TextView cityText = findViewById(R.id.text_city_name);
        Button backButton = findViewById(R.id.button_back);

        // Gets city name from intent
        String city = getIntent().getStringExtra("city_name");

        // Displays city immediately
        if (city != null) {
            cityText.setText(city);
            cityText.setAlpha(1f); 
        }

        // Goes back when back button clicked
        backButton.setOnClickListener(v -> finish());

        overridePendingTransition(0, 0);
    }
}
