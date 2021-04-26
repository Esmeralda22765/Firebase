package piwi.esme.BELV22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =" ";
    TextView mRegister;
    EditText mCorreo, mClave;
    Button miinicioSesion;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRegister = (TextView) findViewById(R.id.txt_register);
        mCorreo = (EditText) findViewById(R.id.txt_correo);
        mClave = (EditText) findViewById(R.id.txt_password);
        miinicioSesion = (Button) findViewById(R.id.btn_iniciarsesion);

        mAuth = FirebaseAuth.getInstance();

        miinicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = mCorreo.getText().toString();
                password = mClave.getText().toString();
                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Log.d(TAG, "signInWithEmail:succes");
                                    Intent intent = new Intent(MainActivity.this, Entrar.class);
                                    startActivity(intent);
                                    Toast.makeText(MainActivity.this, "Bienvenido",
                                            Toast.LENGTH_LONG).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {

                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Error al iniciar sesion",
                                            Toast.LENGTH_LONG).show();
                                    updateUI(null);
                                }
                            }
                        });
            }
        });
       
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Agregar.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if(user != null){
            Intent intent = new Intent(MainActivity.this, Entrar.class);
            startActivity(intent);
            finish();
        }
    }
}