package devmob2018.com.minhasnotasapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import devmob2018.com.minhasnotasapp.entities.Disciplina;

public class DadosAvaliacaoActivity extends BaseActivity {

    private Disciplina disciplina;

    private EditText editTitulo, editNota;

    public DadosAvaliacaoActivity() {
        super(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_avaliacao);

        this.editTitulo = findViewById(R.id.editTitulo);
        this.editNota = findViewById(R.id.editNota);

        Bundle b = getIntent().getExtras();
        this.disciplina = (Disciplina) b.getSerializable("disciplina");

        int requestCode = b.getInt("requestCode");

        if (requestCode == 1001) {
            setTitle(getString(R.string.app_name) + " | " + "Dados Avaliação 1");
            this.resultCode = 2001;
            this.editTitulo.setText(this.disciplina.getAvaliacao1().getTitulo());

            if (this.disciplina.getAvaliacao1().getNota() != null) {
                this.editNota.setText(String.valueOf(this.disciplina.getAvaliacao1().getNota()));
            }

        } else {
            if (requestCode == 1002) {
                setTitle(getString(R.string.app_name) + " | " + "Dados Avaliação 2");
                this.resultCode = 2002;

                this.editTitulo.setText(this.disciplina.getAvaliacao2().getTitulo());

                if (this.disciplina.getAvaliacao2().getNota() != null) {
                    this.editNota.setText(String.valueOf(this.disciplina.getAvaliacao2().getNota()));
                }
            }
        }


    }

    @Override
    protected void implementarExtras(Intent it) {

        String titulo = this.editTitulo.getText().toString();
        Float nota = null;

        if (this.editNota.getText().toString().length() > 0) {
            nota = Float.valueOf(this.editNota.getText().toString());
            Toast.makeText(this, String.valueOf(nota), Toast.LENGTH_SHORT).show();

        }

        switch (resultCode) {
            case 2001:
                this.disciplina.getAvaliacao1().setTitulo(titulo);
                this.disciplina.getAvaliacao1().setNota(nota);
                break;

            case 2002:
                this.disciplina.getAvaliacao2().setTitulo(titulo);
                this.disciplina.getAvaliacao2().setNota(nota);
                break;

            default:
                Toast.makeText(this, "Escolha não reconhecida!", Toast.LENGTH_SHORT).show();
        }

        it.putExtra("disciplina", this.disciplina);
    }

    @Override
    protected boolean todosPreenchidos() {
        if (this.editTitulo.getText().toString().length() <= 0 ||
                this.editNota.getText().toString().length() <= 0) {

            return false;
        }

        return true;
    }


}
