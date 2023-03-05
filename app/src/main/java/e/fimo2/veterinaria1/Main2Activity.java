package e.fimo2.veterinaria1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import e.fimo2.veterinaria1.utilidades.utilidades;

public class Main2Activity extends AppCompatActivity {

    EditText id,nom,tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        id = findViewById(R.id.id);
        nom = findViewById(R.id.nom);
        tel = findViewById(R.id.tel);
    }

    public void regi(View view) {
        registrarUsuarios();
    }

    private void registrarUsuarios() {
        conexion conn= new conexion(this,"bd_usuarios",null,1);

        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(utilidades.CAMPO_ID, id.getText().toString());
        values.put(utilidades.CAMPO_NOMBRE, nom.getText().toString());
        values.put(utilidades.CAMPO_TELEFONO, tel.getText().toString());

        Long idres = db.insert(utilidades.TABLA_USUARIO,utilidades.CAMPO_ID,values);
        Toast.makeText(getApplicationContext(),"Id Registro: " + idres, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(i);


    }
}
