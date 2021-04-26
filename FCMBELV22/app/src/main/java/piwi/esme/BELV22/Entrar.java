package piwi.esme.BELV22;

import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import androidx.appcompat.app.AppCompatActivity;
        import com.google.firebase.auth.FirebaseAuth;

public class Entrar extends AppCompatActivity {

    private Button btn_cerrar;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);

        btn_cerrar = (Button) findViewById(R.id.btn_cerrar_sesion);
        mAuth = FirebaseAuth.getInstance();

        btn_cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Entrar.this, MainActivity.class));
            }
        });
    }
}
