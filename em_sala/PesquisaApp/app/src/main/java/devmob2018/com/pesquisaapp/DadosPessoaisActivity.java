package devmob2018.com.pesquisaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DadosPessoaisActivity extends Activity {

    EditText editNome, editIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_pessoais);

        setTitle(getString(R.string.app_name) + " | " + getString(R.string.dadosPessoaisActivityName));

        this.editNome = findViewById(R.id.editNome);
        this.editIdade = findViewById(R.id.editIdade);

    }

    public void salvar(View v) {

        Intent it = new Intent(this, PrincipalActivity.class);
        it.putExtra("nome", this.editNome.getText().toString());
        it.putExtra("idade", Integer.valueOf(this.editIdade.getText().toString()));
        setResult(600, it);
        finish();

    }

    public void retornar() {
        this.onBackPressed();
    }


}
