package devmob2018.com.pesquisaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import devmob2018.com.pesquisaapp.entities.Endereco;
import devmob2018.com.pesquisaapp.entities.Pessoa;

public class DadosEnderecoActivity extends BaseActivity {

    private Pessoa pessoa;

    private Endereco endereco;

    private EditText editCep, editCidade;
    private Spinner spEstado;

    public DadosEnderecoActivity() {
        super(791);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_endereco);

        setTitle(getString(R.string.app_name) + " | " + getString(R.string.dadosEnderecoActivityName));

        this.endereco = new Endereco();

        this.editCep = findViewById(R.id.editCep);
        this.editCidade = findViewById(R.id.editCidade);
        this.spEstado = findViewById(R.id.spEstado);

        Bundle b = getIntent().getExtras();
        if ((Pessoa) b.getSerializable("pessoa") != null) {
            this.pessoa = (Pessoa) b.getSerializable("pessoa");

            this.editCep.setText(this.pessoa.getEndereco().getCep());
            this.editCidade.setText(this.pessoa.getEndereco().getCidade());
//            this.spEstado.setSelected(true);
//            this.pessoa.getEndereco().getEstado()

        }
    }

    @Override
    protected void implementarExtras(Intent it) {

        this.endereco.setCep(this.editCep.getText().toString());
        this.endereco.setCidade(this.editCidade.getText().toString());
//        this.endereco.setEstado(this.spEstado.getSelectedItem().toString());

        it.putExtra("endereco", this.endereco);
    }

    @Override
    protected boolean todosPreenchidos() {
        return true;
    }
}
