package com.example.book.Screen;import android.content.Intent;import android.os.Bundle;import android.os.Handler;import androidx.appcompat.app.AppCompatActivity;import com.example.book.MainActivity;import com.example.book.R;import com.google.firebase.auth.FirebaseAuth;import com.google.firebase.auth.FirebaseUser;public class SplashActivity extends AppCompatActivity {    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_splash);        Handler handler = new Handler();        handler.postDelayed(new Runnable() {            @Override            public void run() {                checkLogin();            }        },3000);    }    private void checkLogin() {        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();        if (mUser == null) {            //redirect to sign in screen            startActivity(new Intent(SplashActivity.this, SignInActivity.class));        } else {            //redirect to main screen            startActivity(new Intent(SplashActivity.this, MainActivity.class));        }        //close splash screen        finish();    }}