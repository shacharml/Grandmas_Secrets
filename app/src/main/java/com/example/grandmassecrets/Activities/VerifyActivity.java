package com.example.grandmassecrets.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grandmassecrets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerifyActivity extends AppCompatActivity {

    private EditText verify_EDT_code1,verify_EDT_code2,verify_EDT_code3,verify_EDT_code4,verify_EDT_code5,verify_EDT_code6;
    private TextView verify_TXT_text_mobile;
    private Button verify_BTN_send;

    // string for storing our verification ID
    private String verificationId;
    private FirebaseAuth myAuth;
//    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        findViews();
        setupInputs();
        verify_TXT_text_mobile.setText(String.format(
                "+972%s",getIntent().getStringExtra(getString(R.string.LBL_phone))
        ));


        verificationId = getIntent().getStringExtra(getString(R.string.LBL_verification_Id));

        verify_BTN_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                progressBarVerify.setVisibility(View.VISIBLE);
                if (verify_EDT_code1.getText().toString().trim().isEmpty() ||
                        verify_EDT_code2.getText().toString().trim().isEmpty() ||
                        verify_EDT_code3.getText().toString().trim().isEmpty() ||
                        verify_EDT_code4.getText().toString().trim().isEmpty() ||
                        verify_EDT_code5.getText().toString().trim().isEmpty() ||
                        verify_EDT_code6.getText().toString().trim().isEmpty()) {
                    Toast.makeText(VerifyActivity.this, "OTP is not Valid!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (verificationId != null) {
                        String code = getCode();
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
                        signInWithCredential(credential);
                    }
                }
            }
        });
    }

    private String getCode() {
       return verify_EDT_code1.getText().toString().trim() +
                verify_EDT_code2.getText().toString().trim() +
                verify_EDT_code3.getText().toString().trim() +
                verify_EDT_code4.getText().toString().trim() +
                verify_EDT_code5.getText().toString().trim() +
                verify_EDT_code6.getText().toString().trim();
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        // inside this method we are checking if the code entered is correct or not.
        myAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(VerifyActivity.this, "Authentication Success", Toast.LENGTH_LONG).show();

                            // Now need to check if the user is already in the database or need to sign up





//                            FirebaseUser user = task.getResult().getUser();
//                            // Update UI

                            // TODO: 23/06/2022 this is when it secsesful - we need to forward the uid that came back and check if already exist enter to application else send to sign up activity
//                binding.progressBarVerify.setVisibility(View.VISIBLE);
//                binding.btnVerify.setVisibility(View.INVISIBLE);
                            // if the code is correct and the task is successful
                            // we are sending our user to new activity.
                            Intent i = new Intent(VerifyActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            // if the code is not correct then we are displaying an error message to the user.
                            Toast.makeText(VerifyActivity.this, "Authentication Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void findViews() {
        verify_EDT_code1 = findViewById(R.id.verify_EDT_code1);
        verify_EDT_code2 = findViewById(R.id.verify_EDT_code2);
        verify_EDT_code3 = findViewById(R.id.verify_EDT_code3);
        verify_EDT_code4 = findViewById(R.id.verify_EDT_code4);
        verify_EDT_code5 = findViewById(R.id.verify_EDT_code5);
        verify_EDT_code6 = findViewById(R.id.verify_EDT_code6);
        verify_TXT_text_mobile = findViewById(R.id.verify_TXT_text_mobile);
        verify_BTN_send = findViewById(R.id.sign_up_BTN_sign);
        myAuth =  FirebaseAuth.getInstance();
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


    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onStop() {
        super.onStop();
//        code = getCode();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}