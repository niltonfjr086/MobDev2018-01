package devmob2018.com.minhasnotasapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import devmob2018.com.minhasnotasapp.entities.Disciplina;

public class DadosDisciplinaActivity extends BaseActivity {

    private Disciplina disciplina;

    private EditText editNome, editProfessor;

    public DadosDisciplinaActivity() {
        super(2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dados_disciplina);
        setTitle(getString(R.string.app_name) + " | " + getString(R.string.app_dadosdisciplina_name));

        this.editNome = findViewById(R.id.editNome);
        this.editProfessor = findViewById(R.id.editProfessor);

        Bundle b = getIntent().getExtras();
        Integer requestCode = b.getInt("requestCode");
        if (requestCode == 1000) {

            this.disciplina = (Disciplina) b.getSerializable("disciplina");

            this.editNome.setText(this.disciplina.getNomeDisciplina());
            this.editProfessor.setText(this.disciplina.getNomeProfessor());
        }

    }

    @Override
    protected void implementarExtras(Intent it) {
        this.disciplina.setNomeDisciplina(this.editNome.getText().toString());
        this.disciplina.setNomeProfessor(this.editProfessor.getText().toString());

        it.putExtra("disciplina", this.disciplina);
    }

    @Override
    protected boolean todosPreenchidos() {

        if (this.editNome.getText().toString().length() == 0 ||
                this.editProfessor.getText().toString().length() == 0) {

            return false;
        }
        return true;
    }

}
