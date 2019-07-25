package com.example.gglogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class Main4Activity extends AppCompatActivity {

    private TextView Description;
    private EditText EmailEntry;
    private Button ResetClick;
    private FirebaseAuth emailAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        emailAuth = FirebaseAuth.getInstance();

        Description = (TextView) findViewById(R.id.stextView33);
        EmailEntry = (EditText) findViewById(R.id.editText33);
        ResetClick = (Button) findViewById(R.id.button33);

        ResetClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string1 = EmailEntry.getText().toString();

                if(TextUtils.isEmpty(string1))
                {
                    Toast.makeText(Main4Activity.this, "Please enter the registered email address",Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        emailAuth.sendPasswordResetEmail(string1).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(Main4Activity.this, "Please check your Email for reset link", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(Main4Activity.this, MainActivity.class));
                                }
                                else
                                {
                                    String message = task.getException().getMessage();
                                    Toast.makeText(Main4Activity.this, "Error Occurred" + message, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

            }
        });




    }
}
