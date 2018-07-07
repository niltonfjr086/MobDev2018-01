package com.guto.pousadaapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.guto.pousadaapplication.model.filtro.FiltroHistoricoEstadia;

import java.util.ArrayList;
import java.util.List;

public class NavigationActivity extends Activity {

    public static List<Object> transientList = new ArrayList<>();
    public static Object transientObject = new Object();
    public static Usuario loggedUser = null;
    public static String wsAddress = "192.168.0.5:8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        if(loggedUser==null) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(loggedUser==null) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
    }

    public void abrirHistoricoEstadia(View v){
        Intent i = new Intent(this, ModuloActivity.class);
        i.putExtra("filtro", new FiltroHistoricoEstadia());
        startActivity(i);
    }
}
