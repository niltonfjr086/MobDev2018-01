package devmob2018.com.minhasnotasapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class DadosDisciplinaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dados_disciplina);
        setTitle(getString(R.string.app_name) + " | " + getString(R.string.app_dadosdisciplina_name));

    }

    public void salvar(View v) {

    }

    public void cancelar(View v) {

    }
}
