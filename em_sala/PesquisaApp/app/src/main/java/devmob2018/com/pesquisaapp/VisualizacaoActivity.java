package devmob2018.com.pesquisaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import devmob2018.com.pesquisaapp.entities.Contato;
import devmob2018.com.pesquisaapp.entities.Endereco;
import devmob2018.com.pesquisaapp.entities.Pessoa;

public class VisualizacaoActivity extends Activity {

    private Pessoa pessoa;

    TextView nomePessoa, idadePessoa, telefonePessoa, emailPessoa, cepPessoa, cidadePessoa, estadoPessoa;
//    String[] chaves = {"nomePessoa", "idadePessoa",
//            "telefonePessoa", "emailPessoa",
//            "cepPessoa", "cidadePessoa", "estadoPessoa"};

//    Map<String, TextView> dadosParaView = new HashMap<String, TextView>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacao);

        setTitle(getString(R.string.app_name) + " | " + getString(R.string.visualizacaoActivityName));

        this.nomePessoa = findViewById(R.id.nomePessoa);
        this.idadePessoa = findViewById(R.id.idadePessoa);
        this.telefonePessoa = findViewById(R.id.telefonePessoa);
        this.emailPessoa = findViewById(R.id.emailPessoa);
        this.cepPessoa = findViewById(R.id.cepPessoa);
        this.cidadePessoa = findViewById(R.id.cidadePessoa);
        this.estadoPessoa = findViewById(R.id.estadoPessoa);

        Bundle b = getIntent().getExtras();
        if ((Pessoa) b.getSerializable("pessoa") != null) {
            this.pessoa = (Pessoa) b.getSerializable("pessoa");


            this.nomePessoa.setText(this.pessoa.getNome() != null ? this.pessoa.getNome() : "");

            if (this.pessoa.getIdade() != null) {
                this.idadePessoa.setText(String.valueOf(this.pessoa.getIdade()));
            } else {
                this.idadePessoa.setText("");
            }

            this.telefonePessoa.setText(this.pessoa.getContato().getTelefone() != null ? this.pessoa.getContato().getTelefone() : "");
            this.emailPessoa.setText(this.pessoa.getContato().getEmail() != null ? this.pessoa.getContato().getEmail() : "");
            this.cepPessoa.setText(this.pessoa.getEndereco().getCep() != null ? this.pessoa.getEndereco().getCep() : "");
            this.cidadePessoa.setText(this.pessoa.getEndereco().getCidade() != null ? this.pessoa.getEndereco().getCidade() : "");
            this.estadoPessoa.setText(this.pessoa.getEndereco().getEstado() != null ? this.pessoa.getEndereco().getEstado() : "");
        }

    }

    public void limpar(View v) {
        this.pessoa = new Pessoa();
        this.pessoa.setEndereco(new Endereco());
        this.pessoa.setContato(new Contato());

        this.nomePessoa.setText("");
        this.idadePessoa.setText("");
        this.telefonePessoa.setText("");
        this.emailPessoa.setText("");
        this.cepPessoa.setText("");
        this.cidadePessoa.setText("");
        this.estadoPessoa.setText("");
    }

    public void retornar(View v) {
        this.onBackPressed();
    }

    @Override
    public void onBackPressed() {

        Intent it = new Intent(this, PrincipalActivity.class);
        it.putExtra("pessoa", this.pessoa);
        setResult(1001, it);

        super.onBackPressed();

    }
}
