package com.example.grandmassecrets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class VerifyActivity extends AppCompatActivity {

    private EditText verify_EDT_code1,verify_EDT_code2,verify_EDT_code3,verify_EDT_code4,verify_EDT_code5,verify_EDT_code6;
    private TextView verify_TXT_text_mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        findViews();

        verify_TXT_text_mobile.setText(String.format(
                "+972%s",getIntent().getStringExtra("mobile")
        ));

        setupInputs();
    }

    private void findViews() {
        verify_EDT_code1 = findViewById(R.id.verify_EDT_code1);
        verify_EDT_code2 = findViewById(R.id.verify_EDT_code2);
        verify_EDT_code3 = findViewById(R.id.verify_EDT_code3);
        verify_EDT_code4 = findViewById(R.id.verify_EDT_code4);
        verify_EDT_code5 = findViewById(R.id.verify_EDT_code5);
        verify_EDT_code6 = findViewById(R.id.verify_EDT_code6);
        verify_TXT_text_mobile = findViewById(R.id.verify_TXT_text_mobile);
    }

    private void setupInputs(){
        verify_EDT_code1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    verify_EDT_code2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        verify_EDT_code2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    verify_EDT_code3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        verify_EDT_code3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    verify_EDT_code4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        verify_EDT_code4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    verify_EDT_code5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        verify_EDT_code5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    verify_EDT_code6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}