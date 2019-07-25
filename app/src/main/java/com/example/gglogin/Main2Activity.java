package com.example.gglogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gglogin.MainActivity;
import com.example.gglogin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity {


    private ImageView sKey;
    private TextView Hello;
    private TextView Details;
    private TextView sNameview;
    private TextView sPasswordview;
    private TextView sRePasswordview;
    private EditText sName;
    private EditText sPassword;
    private EditText sRePassword;
    private Button Register;
    private TextView Already;
    private TextView LoginHere;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sKey = (ImageView) findViewById(R.id.simageView);
        Hello = (TextView) findViewById(R.id.stextView1);
        Details = (TextView) findViewById(R.id.stextView2);
        sNameview = (TextView) findViewById(R.id.stextView3);
        sPasswordview = (TextView) findViewById(R.id.stextView4);
        sRePasswordview = (TextView) findViewById(R.id.stextView5);
        sName = (EditText) findViewById(R.id.seditText);
        sPassword = (EditText) findViewById(R.id.seditText1);
        sRePassword = (EditText) findViewById(R.id.seditText2);
        Register = (Button) findViewById(R.id.sbutton);
        Already = (TextView) findViewById(R.id.stextView6);
        LoginHere = (TextView) findViewById(R.id.stextView7);

        firebaseAuth = FirebaseAuth.getInstance();

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String SName = sName.getText().toString().trim();
                    String SPassword = sPassword.getText().toString().trim();
                    String SRePassword = sRePassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(SName, SPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Main2Activity.this, "Your Account is Registered Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Main2Activity.this, MainActivity.class));
                            } else {
                                Toast.makeText(Main2Activity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                Log.d("Register", task.getException().toString());
                            }
                        }
                    });

                }

            }
        });
        LoginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, MainActivity.class));
            }
        });
    }

    private boolean validate() {
        boolean result = false;

        String Name = sName.getText().toString();
        String Password = sPassword.getText().toString();
        String RePassword = sRePassword.getText().toString();

        if (Name.isEmpty() || Password.isEmpty() || RePassword.isEmpty())
        {
            Toast.makeText(Main2Activity.this, "Please Fill All the Entries", Toast.LENGTH_SHORT).show();
        }
        else if(!Password.equals(RePassword))
        {
            Toast.makeText(Main2Activity.this,"Confirmation password is not matching with original password", Toast.LENGTH_LONG).show();
        }
        else
        {
            result = true;
        }
        return result;

    }

}










