package devmob2018.com.minhasnotasapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import devmob2018.com.minhasnotasapp.entities.Avaliacao;
import devmob2018.com.minhasnotasapp.entities.Disciplina;

public class DadosResultadoActivity extends BaseActivity {

    Disciplina disciplina;

    TextView nome, professor, tituloAvaliacao1, notaAvaliacao1, tituloAvaliacao2, notaAvaliacao2, media;

    public DadosResultadoActivity() {
        super(2222);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_resultado);
        setTitle(getString(R.string.app_name) + " | " + getString(R.string.app_dadosresultado_name));

        this.nome = findViewById(R.id.nome);
        this.professor = findViewById(R.id.professor);
        this.tituloAvaliacao1 = findViewById(R.id.tituloAvaliacao1);
        this.notaAvaliacao1 = findViewById(R.id.notaAvaliacao1);
        this.tituloAvaliacao2 = findViewById(R.id.tituloAvaliacao2);
        this.notaAvaliacao2 = findViewById(R.id.notaAvaliacao2);
        this.media = findViewById(R.id.media);

        Bundle b = getIntent().getExtras();


        this.disciplina = (Disciplina) b.getSerializable("disciplina");

        this.nome.append(": " + this.disciplina.getNomeDisciplina());

        this.professor.append(": " + this.disciplina.getNomeProfessor());

        this.tituloAvaliacao1.append(": " + this.disciplina.getAvaliacao1().getTitulo());

        if (this.disciplina.getAvaliacao1().getNota() != null) {
            this.notaAvaliacao1.append(": " + this.disciplina.getAvaliacao1().getNota().toString());
        }

        this.tituloAvaliacao2.append(": " + this.disciplina.getAvaliacao2().getTitulo());

        if (this.disciplina.getAvaliacao2().getNota() != null) {
            this.notaAvaliacao2.append(": " + this.disciplina.getAvaliacao2().getNota().toString());
        }

        this.media.append(": " + this.disciplina.getMedia());
    }

    @Override
    protected void implementarExtras(Intent it) {
        this.disciplina = new Disciplina();
        this.disciplina.setAvaliacao1(new Avaliacao());
        this.disciplina.setAvaliacao2(new Avaliacao());

        it.putExtra("disciplina", this.disciplina);
    }

    @Override
    protected boolean todosPreenchidos() {
        return true;
    }
}
