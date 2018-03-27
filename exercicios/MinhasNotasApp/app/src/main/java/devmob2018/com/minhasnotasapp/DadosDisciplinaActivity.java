package devmob2018.com.minhasnotasapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import devmob2018.com.minhasnotasapp.entities.Disciplina;

public class DadosDisciplinaActivity extends BaseActivity {

    private Disciplina disciplina;

    private EditText editNome, editProfessor;

    public DadosDisciplinaActivity() {
        super(678);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dados_disciplina);
        setTitle(getString(R.string.app_name) + " | " + getString(R.string.app_dadosdisciplina_name));

        Bundle b = getIntent().getExtras();
//        this.disciplina =

        this.editNome = findViewById(R.id.editNome);
        this.editProfessor = findViewById(R.id.editProfessor);

    }

    public void salvar(View v) {

        this.editNome.getText();

    }

    @Override
    protected void implementarExtras(Intent it) {

    }

    @Override
    protected boolean todosPreenchidos() {
        return false;
    }

}
