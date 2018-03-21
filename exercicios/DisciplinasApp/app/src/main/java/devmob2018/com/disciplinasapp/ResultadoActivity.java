package devmob2018.com.disciplinasapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import devmob2018.com.disciplinasapp.entities.Disciplina;

public class ResultadoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        LinearLayout layout = findViewById(R.id.layoutResultado);

        Bundle b = getIntent().getExtras();

        List<Disciplina> disciplinas = new ArrayList<>();
        disciplinas.add((Disciplina) b.getSerializable("d1"));
        disciplinas.add((Disciplina) b.getSerializable("d2"));

        Double somaMedias = 0.00;
        String resultadoFinal = "Aprovado";
        for (Disciplina d : disciplinas) {
            TextView disciplinaNome = new TextView(this);
            disciplinaNome.setText("Disciplina: " + d.getNome());
            disciplinaNome.setPadding(2, 2, 2, 2);
            layout.addView(disciplinaNome);

            TextView disciplinaMedia = new TextView(this);
            disciplinaMedia.setText("Média: " + d.getMedia());
            disciplinaMedia.setPadding(2, 2, 2, 2);
            layout.addView(disciplinaMedia);

            TextView disciplinaSituacao = new TextView(this);
            disciplinaSituacao.setText("Situação: " + d.getSituacao());
            disciplinaSituacao.setPadding(2, 2, 2, 50);
            layout.addView(disciplinaSituacao);

            somaMedias += d.getMedia();
            if (d.getSituacao().equalsIgnoreCase("Reprovado")) {
                resultadoFinal = d.getSituacao();
            }

        }

        TextView mediaGeral = new TextView(this);
        mediaGeral.setPadding(2, 2, 2, 2);
        if (somaMedias <= 0) {
            mediaGeral.setText("Média geral: " + "zero");
        } else {
            mediaGeral.setText(String.valueOf("Média geral: " + (somaMedias / disciplinas.size())));
        }
        layout.addView(mediaGeral);

        TextView situacaoGeral = new TextView(this);
        situacaoGeral.setPadding(2, 2, 2, 50);
        situacaoGeral.setText("Situação Geral: " + resultadoFinal);
        layout.addView(situacaoGeral);


        Button btn = new Button(this);
        btn.setText("Retornar");
        btn.setGravity(1);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       Toast.makeText(ResultadoActivity.this, "Retornando", Toast.LENGTH_LONG);
//                                       Disciplina2Activity.finish();
//                                       Intent intent = new Intent(this, Disciplina2Activity.class);
//                                       intent.putExtra("caller", this.getClass().getSimpleName());
//                                       startActivity(intent);
//                                       finish();
                                       onBackPressed();
                                   }
                               }

        );
        layout.addView(btn);
    }


    @Override
    public void onBackPressed() {
        Intent it = new Intent();
//        it.putExtra("caller", this.getClass().getSimpleName());
        it.putExtra("caller", "Resultado");
        setResult(1, it);
        super.onBackPressed();
    }

}
