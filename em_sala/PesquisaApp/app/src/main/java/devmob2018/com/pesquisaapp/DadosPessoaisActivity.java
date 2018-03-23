package devmob2018.com.pesquisaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import devmob2018.com.pesquisaapp.entities.Pessoa;

public class DadosPessoaisActivity extends BaseActivity {

    private Pessoa pessoa;

    private EditText editNome, editIdade;

    public DadosPessoaisActivity() {
        super(789);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_pessoais);

        setTitle(getString(R.string.app_name) + " | " + getString(R.string.dadosPessoaisActivityName));

        this.editNome = findViewById(R.id.editNome);
        this.editIdade = findViewById(R.id.editIdade);


        Bundle b = getIntent().getExtras();
        if ((Pessoa) b.getSerializable("pessoa") != null) {
            this.pessoa = (Pessoa) b.getSerializable("pessoa");

            this.editNome.setText(this.pessoa.getNome());

            if (this.pessoa.getIdade() == null) {
                this.editIdade.setText("");

            } else {
                this.editIdade.setText(this.pessoa.getIdade().toString());

            }
        }

    }


    protected void implementarExtras(Intent it) {

        it.putExtra("nome", this.editNome.getText().toString());

        if (!this.editIdade.getText().toString().equals("")) {
            it.putExtra("idade", Integer.valueOf(this.editIdade.getText().toString()));
        }
    }

    protected boolean todosPreenchidos() {
        return false;
    }


}
