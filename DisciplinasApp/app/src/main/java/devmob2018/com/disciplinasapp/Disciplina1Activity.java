package devmob2018.com.disciplinasapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import devmob2018.com.disciplinasapp.entities.Disciplina;

public class Disciplina1Activity extends Activity {

    private Disciplina d1;

    private EditText nomeDisciplina;

    private NumberPicker np1, np2, np3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplina1);

        this.d1 = new Disciplina();

        this.nomeDisciplina = findViewById(R.id.etD1);

        this.np1 = findViewById(R.id.npNota1);
        this.np2 = findViewById(R.id.npNota2);
        this.np3 = findViewById(R.id.npNota3);


    }

    public void toNext(View v) {



        Intent it = new Intent(this, Disciplina2Activity.class);
        it.putExtra("d1", d1);

    }


}
