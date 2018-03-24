package devmob2018.com.pesquisaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import devmob2018.com.pesquisaapp.entities.Contato;
import devmob2018.com.pesquisaapp.entities.Pessoa;

public class DadosContatoActivity extends BaseActivity {

    private Pessoa pessoa;

    private Contato contato;
    private EditText editTelefone, editEmail;

    public DadosContatoActivity() {
        super(790);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_contato);

        setTitle(getString(R.string.app_name) + " | " + getString(R.string.dadosContatoActivityName));

        this.contato = new Contato();

        this.editTelefone = findViewById(R.id.editTelefone);
        this.editEmail = findViewById(R.id.editEmail);

        Bundle b = getIntent().getExtras();
        if ((Pessoa) b.getSerializable("pessoa") != null) {
            this.pessoa = (Pessoa) b.getSerializable("pessoa");

            this.editTelefone.setText(this.pessoa.getContato().getTelefone());
            this.editEmail.setText(this.pessoa.getContato().getEmail());

        }

    }

    @Override
    protected void implementarExtras(Intent it) {

//        this.contato.setEmail(this.editEmail.getText().toString());
//        this.contato.setTelefone(this.editTelefone.getText().toString());
//        it.putExtra("contato", this.contato);

        this.pessoa.getContato().setEmail(this.editEmail.getText().toString());
        this.pessoa.getContato().setTelefone(this.editTelefone.getText().toString());
        it.putExtra("pessoa", this.pessoa);

    }

    @Override
    protected boolean todosPreenchidos() {

        if (this.editEmail.getText().length() == 0 || this.editTelefone.getText().length() == 0) {
            return false;
        }

        return true;
    }

}
