package devmob2018.com.eleitorapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import devmob2018.com.eleitorapp.entities.Pessoa;

public class ResultadoActivity extends Activity {

    private Pessoa p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        this.p = new Pessoa();

        Bundle params = getIntent().getExtras();
        this.p = (Pessoa) params.getSerializable("pessoa");


        LinearLayout layout = findViewById(R.id.layoutResultado);

        TextView tvNome = new TextView(this);
        tvNome.setText(getString(R.string.label_nome) + ": " + p.getNome());
        layout.addView(tvNome);

        TextView tvIdade = new TextView(this);
        tvIdade.setText(getString(R.string.label_idade) + ": " + p.getIdade());
        layout.addView(tvIdade);

        TextView tvResultado = new TextView(this);
        if (p.getIdade() >= 16) {
            tvResultado.setText(getString(R.string.pode_votar_questao) + ": " + getString(R.string.sim));
        } else {
            tvResultado.setText(getString(R.string.pode_votar_questao) + ": " + getString(R.string.nao));
        }
        layout.addView(tvResultado);


        Button btn = new Button(this);
        btn.setText(R.string.concluir);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       Toast.makeText(ResultadoActivity.this,"Retornando",Toast.LENGTH_LONG);
                                       finish();
                                   }
                               }

        );
        layout.addView(btn);

    }
}
