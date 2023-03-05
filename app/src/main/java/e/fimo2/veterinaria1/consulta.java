package e.fimo2.veterinaria1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import e.fimo2.veterinaria1.utilidades.utilidades;

public class consulta extends AppCompatActivity {

    EditText idd,nomm,tell;
    TextView todo,todo1,todo2;

    conexion conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        conn=new conexion(getApplicationContext(),"bd_usuarios",null,1);
        todo = findViewById(R.id.tod);
        todo1 = findViewById(R.id.tod1);
        todo2 = findViewById(R.id.tod2);
        idd = findViewById(R.id.idi);
        nomm = findViewById(R.id.nomb);
        tell = findViewById(R.id.tele);
    }

    public void consu(View view) {
        consultar();
    }

    private void consultar() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros ={idd.getText().toString()};
        String[] campos = {utilidades.CAMPO_NOMBRE,utilidades.CAMPO_TELEFONO};

        try {
            Cursor cursor = db.query(utilidades.TABLA_USUARIO,campos,utilidades.CAMPO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            nomm.setText(cursor.getString(0));
            tell.setText(cursor.getString(1));
            cursor.close();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe", Toast.LENGTH_SHORT).show();
            limpiar();
        }


    }

    private void limpiar() {
        nomm.setText("");
        tell.setText("");
    }


    public void act(View view) {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros ={idd.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(utilidades.CAMPO_NOMBRE, nomm.getText().toString());
        values.put(utilidades.CAMPO_TELEFONO, tell.getText().toString());

        db.update(utilidades.TABLA_USUARIO,values,utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Datos Actualizados", Toast.LENGTH_SHORT).show();
        db.close();
        limpiar();

    }

    public void borr(View view) {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros ={idd.getText().toString()};
        db.delete(utilidades.TABLA_USUARIO,utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Elemento Eliminado", Toast.LENGTH_SHORT).show();
        db.close();
        limpiar();
    }

    private void consultarT() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros ={idd.getText().toString()};
        String[] campos = {utilidades.CAMPO_ID,utilidades.CAMPO_NOMBRE,utilidades.CAMPO_TELEFONO};
        String col1 = "";
        String col2 = "";
        String col3 = "";

        try {
            Cursor cursor = db.query(utilidades.TABLA_USUARIO,campos,null,null,null,null,null);
            if (cursor.moveToFirst()){
                while (cursor.moveToNext()){
                    col1 ="\t" + col1 + " " + cursor.getString(0);
                    col2 ="\t" + col2 + " " + cursor.getString(1);
                    col3 ="\t" + col3 + " " + cursor.getString(2);
                    todo.setText("\t" + "IP's: " + col1 + "\t");
                    todo1.setText("\t" + "Nombres: " + col2 + "\t");
                    todo2.setText("\t"+ "Numeros: " +col3 + "\t");
                }
            }
    }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe", Toast.LENGTH_SHORT).show();
            limpiar();
        }
            db.close();
        }

    public void conT(View view) {
        consultarT();
    }
}
