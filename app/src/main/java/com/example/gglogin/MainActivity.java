package com.example.gglogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.PriorityQueue;

public class MainActivity extends AppCompatActivity {

    //Create Variables for the widgets

    private ImageView Key;
    private TextView Welcome;
    private TextView Continue;
    private TextView Nameview;
    private TextView Passwordview;
    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView Forgot;
    private TextView Reset;
    private TextView Noaccount;
    private TextView Register;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Key = (ImageView) findViewById(R.id.imageView);
        Welcome = (TextView) findViewById(R.id.textView1);
        Continue = (TextView) findViewById(R.id.textView2);
        Name = (EditText) findViewById(R.id.editText);
        Password = (EditText) findViewById(R.id.editText1);
        Nameview = (TextView) findViewById(R.id.textView3);
        Passwordview = (TextView) findViewById(R.id.textView4);
        Login = (Button) findViewById(R.id.button);
        Forgot = (TextView) findViewById(R.id.textView5);
        Noaccount = (TextView) findViewById(R.id.textView6);
        Reset = (TextView) findViewById(R.id.textView7);
        Register = (TextView) findViewById(R.id.textView8);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        Login .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(),Password.getText().toString());
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
               startActivity(new Intent(MainActivity.this,Main4Activity.class));
            }
        });

    }

    private void validate(String Name, String Password)
    {
        progressDialog.setMessage("Please wait, while we are logging into your account");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(Name,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this,"Successfully Logged In", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Main3Activity.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
