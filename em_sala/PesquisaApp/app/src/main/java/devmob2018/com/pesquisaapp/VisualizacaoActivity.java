package devmob2018.com.pesquisaapp;

import android.app.Activity;
import android.os.Bundle;

public class VisualizacaoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacao);

        setTitle(getString(R.string.app_name) + " | " + getString(R.string.visualizacaoActivityName));
    }
}
