package devmob2018.com.eleitorapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import devmob2018.com.eleitorapp.entities.Pessoa;

public class PrincipalActivity extends Activity {

    Pessoa p;

    EditText editNome;
    NumberPicker npIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        this.p = new Pessoa();

        this.editNome = findViewById(R.id.editNome);

        this.npIdade = findViewById(R.id.npIdade);
        this.npIdade.setMinValue(0);
        this.npIdade.setMaxValue(150);
        this.npIdade.setValue(25);

    }

    public void concluir(View v){

        p.setNome(this.editNome.getText().toString());
        p.setIdade(this.npIdade.getValue());

        Intent it = new Intent(this,ResultadoActivity.class);
        it.putExtra("pessoa", p);

        startActivity(it);


    }
}
