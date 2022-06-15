package com.example.grandmassecrets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class LoginActivity extends AppCompatActivity {

    private Button login_BTN_send;
    private EditText login_EDT_phone_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
        login_BTN_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(login_EDT_phone_number.getText().toString().trim().isEmpty()){
                    Toast.makeText(LoginActivity.this,"Enter Mobile number"  ,Toast.LENGTH_SHORT).show();
                    return;
                }
                if (login_EDT_phone_number.getText().toString().length() < 9){
                    Toast.makeText(LoginActivity.this,"The Phone number is too short"  ,Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(),VerifyActivity.class);
                intent.putExtra("mobile",login_EDT_phone_number.getText().toString());
                startActivity(intent);
//                finish();
            }
        });

    }

    private void findViews() {
        login_EDT_phone_number = findViewById(R.id.login_EDT_phone_number);
        login_BTN_send = findViewById(R.id.login_BTN_send);
    }
}