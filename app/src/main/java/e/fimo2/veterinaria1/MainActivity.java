package e.fimo2.veterinaria1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conexion conn= new conexion(this,"bd_usuarios",null,1);
    }

    public void alta(View view) {
        Intent i = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(i);
    }

    public void con(View view) {
        Intent i = new Intent(MainActivity.this, consulta.class);
        startActivity(i);
    }
}
