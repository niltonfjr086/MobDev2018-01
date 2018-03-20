package devmob2018.com.disciplinasapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import devmob2018.com.disciplinasapp.entities.Disciplina;

public class Disciplina2Activity extends Activity {

    private Disciplina d1;
    private Disciplina d2;

    private EditText nomeDisciplina;

    private NumberPicker np1, np2, np3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplina1);

        Bundle view1 = getIntent().getExtras();

        this.d1 = (Disciplina) view1.getSerializable("d1");
        this.d2 = new Disciplina();

        this.nomeDisciplina = findViewById(R.id.etD2);

        this.np1 = findViewById(R.id.npNota1);
        this.np1.setMaxValue(10);
        this.np1.setMinValue(0);

        this.np2 = findViewById(R.id.npNota2);
        this.np2.setMaxValue(10);
        this.np2.setMinValue(0);

        this.np3 = findViewById(R.id.npNota3);
        this.np3.setMaxValue(10);
        this.np3.setMinValue(0);

    }

    public void toNext(View v) {

        Toast.makeText(this, "VEIO", Toast.LENGTH_LONG).show();


        d2.setNota1(Double.valueOf(String.valueOf(np1.getValue())));

//        Intent it = new Intent(this, ResultadoActivity.class);
//        it.putExtra("d1", d1);
//        it.putExtra("d2", d2);

//        startActivity(it);



    }
}
