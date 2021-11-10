package com.example.book.Screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.book.MainActivity;
import com.example.book.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Login extends AppCompatActivity {
    Button btnDangKyTaiKhoanLogin, btnDangNhap;
    EditText edtUsername, edtPassword;
    FirebaseAuth auth;
    FirebaseUser user;
    TextView btnQuenMK;
    ImageView loginWithFacebook;
    ImageView loginWithGoogle;

    ViewFlipper view_fillper_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        auth = FirebaseAuth.getInstance();
        remember();
        setAction();


        // slides:
        // Hiển thị slide:
        int background[] = {R.drawable.user1, R.drawable.user2, R.drawable.user3, R.drawable.user4};

        for (int i = 0; i < background.length; i++) {
            setViewFlipper(background[i]);
        }


    }

    public void setViewFlipper(int background) {
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setBackgroundResource(background);
        view_fillper_login.addView(imageView);
        view_fillper_login.setFlipInterval(3000);
        view_fillper_login.setAutoStart(true);
        view_fillper_login.setInAnimation(getApplicationContext(), android.R.anim.slide_in_left);
        view_fillper_login.setOutAnimation(getApplicationContext(), android.R.anim.slide_out_right);
    }

    private void remember() {
        user = auth.getCurrentUser();
        if (user != null) {
            MainActivity.usernameApp = auth.getUid();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    private void setAction() {
        // bấm đăng ký
        btnDangKyTaiKhoanLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
        // bấm đăng nhập
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });


        // bấm quên mk:
        btnQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmailResetPassword();
            }
        });


        // bấm đăng nhập với facebook:
        loginWithFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        // Bấm đăng nhập với google:
        loginWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void sendEmailResetPassword() {
        String email=edtUsername.getText().toString();
        if(email.isEmpty()){
            edtUsername.setError("Trường email đang trống!");
        }else {
            auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Vui lòng kiểm tra email!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Gửi email đổi mật khẩu không thành công!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void login() {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        if (username.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập tài khoản!", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT).show();
        } else {
            auth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        MainActivity.usernameApp = auth.getUid();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setControl() {
        btnDangKyTaiKhoanLogin = findViewById(R.id.btnDangKyTaiKhoanLogin);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnQuenMK = findViewById(R.id.btnQuenMK);
        view_fillper_login = findViewById(R.id.view_fillper_login);
        loginWithFacebook = findViewById(R.id.loginWithFacebook);
        loginWithGoogle = findViewById(R.id.loginWithGoogle);
    }

}
