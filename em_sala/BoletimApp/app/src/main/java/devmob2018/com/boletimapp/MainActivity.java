package devmob2018.com.boletimapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void calcular(View v){
        EditText editNota1 = (EditText) findViewById(R.id.editNota1);
        EditText editNota2 = (EditText) findViewById(R.id.editNota2);

//        editNota1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Clicou", Toast.LENGTH_SHORT).show();
//            }
//        });

        double nota1 = Double.parseDouble(editNota1.getText().toString());
        double nota2 = Double.parseDouble(editNota2.getText().toString());

        double resultado = (nota1+nota2) / 2;

        Toast.makeText(this,"Média: "+resultado,Toast.LENGTH_LONG).show();

    }

    public void limpar(){

    }

    public void enviar(){

    }

}
