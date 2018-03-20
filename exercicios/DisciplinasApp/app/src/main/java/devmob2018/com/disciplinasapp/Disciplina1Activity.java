package devmob2018.com.disciplinasapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

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

        try {
            d1.setNome(this.nomeDisciplina.getText().toString());
            d1.setNota1(Double.valueOf(String.valueOf(np1.getValue())));
            d1.setNota2(Double.valueOf(String.valueOf(np2.getValue())));
            d1.setNota3(Double.valueOf(String.valueOf(np3.getValue())));

            if (d1.getSoma() <= 0.00) {
                this.onBackPressed();

            } else {

                Intent it = new Intent(this, Disciplina2Activity.class);
                it.putExtra("d1", d1);

                startActivity(it);
            }

        } catch (Exception e) {

            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();

        }


    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.confirm_alert_title)
                .setMessage(R.string.confirm_alert)
                .setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent it = new Intent(Disciplina1Activity.this, Disciplina2Activity.class);
                        it.putExtra("d1", d1);

                        startActivity(it);
                    }
                })
                .setNegativeButton(R.string.negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Disciplina1Activity.this, "Preencha as notas corretamente", Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }


}
