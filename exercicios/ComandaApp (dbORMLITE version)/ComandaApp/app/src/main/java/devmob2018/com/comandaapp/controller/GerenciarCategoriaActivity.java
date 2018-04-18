package devmob2018.com.comandaapp.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import devmob2018.com.comandaapp.R;

public class GerenciarCategoriaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_categoria);
    }

    public void salvar(View v) {
        Toast.makeText(this, "IMPLEMENTAR", Toast.LENGTH_LONG).show();
    }
}
