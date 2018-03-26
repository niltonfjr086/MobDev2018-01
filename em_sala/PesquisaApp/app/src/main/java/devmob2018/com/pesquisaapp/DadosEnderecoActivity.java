package devmob2018.com.pesquisaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import devmob2018.com.pesquisaapp.entities.Pessoa;

public class DadosEnderecoActivity extends BaseActivity {

    private Pessoa pessoa;

    private EditText editCep, editCidade;


    private Spinner spEstado;
    private ArrayAdapter<CharSequence> adapter;


    public DadosEnderecoActivity() {
        super(791);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_endereco);

        setTitle(getString(R.string.app_name) + " | " + getString(R.string.dadosEnderecoActivityName));

//        this.pessoa.setEndereco(new Endereco());

        this.editCep = findViewById(R.id.editCep);
        this.editCidade = findViewById(R.id.editCidade);


        this.spEstado = findViewById(R.id.spEstado);
        this.spEstado.setOnItemSelectedListener(this.dispararNaSelecaoEstado());
        this.adapter = ArrayAdapter.createFromResource(this, R.array.estates, android.R.layout.simple_spinner_dropdown_item);
        this.adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spEstado.setAdapter(adapter);

        Bundle b = getIntent().getExtras();
        if ((Pessoa) b.getSerializable("pessoa") != null) {

            this.pessoa = (Pessoa) b.getSerializable("pessoa");

            this.editCep.setText(this.pessoa.getEndereco().getCep());
            this.editCidade.setText(this.pessoa.getEndereco().getCidade());

            String estado = this.pessoa.getEndereco().getEstado();
            Integer spinnerListSize = this.adapter.getCount();

            for (int i = 0; i < spinnerListSize; i++) {
                if (this.adapter.getItem(i).equals(estado)) {
                    this.spEstado.setSelection(i);
                    return;
                }
            }

        }

    }

    @Override
    protected void implementarExtras(Intent it) {

        this.pessoa.getEndereco().setCep(this.editCep.getText().toString());
        this.pessoa.getEndereco().setCidade(this.editCidade.getText().toString());
        this.pessoa.getEndereco().setEstado(this.spEstado.getSelectedItem().toString());
        it.putExtra("pessoa", this.pessoa);

    }

    @Override
    protected boolean todosPreenchidos() {

        if (this.editCep.getText().length() == 0 || this.editCidade.getText().length() == 0) {
            return false;
        }

        return true;
    }

    private OnItemSelectedListener dispararNaSelecaoEstado() {

        return new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                DadosEnderecoActivity.this.pessoa.getEndereco().setEstado(DadosEnderecoActivity.this.spEstado.getSelectedItem().toString());
                Toast.makeText(DadosEnderecoActivity.this, String.valueOf(DadosEnderecoActivity.this.adapter.getItem(position)), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(DadosEnderecoActivity.this, String.valueOf("NOTHING"), Toast.LENGTH_LONG).show();

            }
        };
    }

}
