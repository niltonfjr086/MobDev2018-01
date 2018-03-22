package devmob2018.com.pesquisaapp;

import android.app.Activity;
import android.os.Bundle;

public class DadosEnderecoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_endereco);

        setTitle(getString(R.string.app_name) + " | " + getString(R.string.dadosEnderecoActivityName));
    }
}
