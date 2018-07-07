package com.guto.pousadaapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.guto.pousadaaapplication.component.EstadiaAdapter;
import com.guto.pousadaaapplication.component.ResponseHandler;
import com.guto.pousadaapplication.model.entity.Estadia;
import com.guto.pousadaapplication.model.filtro.Filtro;
import com.guto.pousadaapplication.model.filtro.FiltroHistoricoEstadia;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.guto.pousadaapplication.NavigationActivity.wsAddress;

public class ModuloActivity extends Activity {

    ListView listagem;
    TabHost tabhost;
    Button btPesquisar;
    LinearLayout mainFiltroLayout;
    LinearLayout mainListLayout;

    Filtro filtro;
    String[] fieldTitles;
    String[] fieldNames;
    Gson gson = new Gson();
    AsyncHttpClient client = new AsyncHttpClient();
    // Registro de filtros
    FiltroHistoricoEstadia fhe;
    StringBuilder httpAddress = new StringBuilder("http://" + wsAddress + "/ws_pousada/estadia/listByFilter");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        this.filtro = (Filtro) this.getIntent().getSerializableExtra("filtro");
        identificarCampos(filtro);
        gerarComponentes();
    }

    public void init() {
        setContentView(R.layout.activity_modulo);
        mainFiltroLayout = findViewById(R.id.main_filtro_layout);
        mainListLayout = findViewById(R.id.main_list_layout);
        listagem = findViewById(R.id.listagem);
        btPesquisar = findViewById(R.id.btPesquisar);
        tabhost = findViewById(R.id.main_tab_host);
        tabhost.setup();
        TabHost.TabSpec ts = tabhost.newTabSpec("tab1");
        ts.setContent(R.id.list_layout);
        ts.setIndicator("Listagem");
        tabhost.addTab(ts);
        ts = tabhost.newTabSpec("tab2");
        ts.setContent(R.id.filtro_layout);
        ts.setIndicator("Filtro");
        tabhost.addTab(ts);
    }

    // TODO para pesquisas por faixa de valor, o formato deve ser:
    // por exemplo: valortotal_diarias_consumidasinicial

    public void pesquisarPorFiltro(View v) {
        // RequestParams requestParams = new RequestParams();
        Boolean first = new Boolean(true);

        for (int i = 0; i < fieldNames.length; i++) {
            EditText e = findViewById(i);
            if (!e.getText().toString().trim().isEmpty()) {

                if (first) {

                    httpAddress.append("?");
                    first = false;

                } else {

                    httpAddress.append("&");

                }

                httpAddress.append(fieldNames[i] + "=" + e.getText().toString());
            }
        }

        System.out.println(httpAddress.toString());
        client.setTimeout(20000);
        client.get(this, httpAddress.toString(), new ResponseHandler<Estadia>(this, new Estadia()));
        httpAddress = new StringBuilder("http://" + wsAddress + "/ws_pousada/estadia/listByFilter");

    }

    public void popularListView(List<Object> list) {
        // Registro de filtros
        if (filtro.getClass() == FiltroHistoricoEstadia.class) {
            List<Estadia> listEstadia = new ArrayList<>();

            for (Object o : list) {

                Estadia e = gson.fromJson(gson.toJson(o), Estadia.class);
                listEstadia.add(e);

            }

            /*ArrayAdapter<Estadia> adapter = new ArrayAdapter<>(ModuloActivity.this, android.R.layout.simple_list_item_1,
                    listEstadia);*/
            EstadiaAdapter adapter = new EstadiaAdapter(ModuloActivity.this, R.layout.estadia_custom_view,
                    listEstadia);


            listagem.setAdapter(adapter);
        }
    }

    private void identificarCampos(Filtro filtro) {
        // Registro de filtros
        if (filtro.getClass() == FiltroHistoricoEstadia.class) {

            fhe = (FiltroHistoricoEstadia) filtro;
            fieldNames = fhe.getFieldNames();
            fieldTitles = fhe.getFieldTitles();

        }
    }

    private void gerarComponentes() {
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        Boolean primeiraData = true;
        Boolean primeiroValor = true;

        for (int i = 0; i < fieldTitles.length; i++) {
            if (fieldTitles[i].contains("Data")) {
                if (primeiraData) {
                    TextView t = new TextView(this);
                    t.setText("Por período de data: ");
                    mainFiltroLayout.addView(t, params);
                    primeiraData = false;
                }

                if (fieldTitles[i].contains("Data") && fieldTitles[i].contains("inicial")) {
                    TextView t = new TextView(this);
                    t.setText(fieldTitles[i]);
                    EditText e = new EditText(this);
                    e.setId(i);
                    mainFiltroLayout.addView(t, params);
                    mainFiltroLayout.addView(e, params);
                    // Substituir por datePicker
                    continue;
                }
                if (fieldTitles[i].contains("Data") && fieldTitles[i].contains("final")) {
                    TextView t = new TextView(this);
                    t.setText(fieldTitles[i]);
                    EditText e = new EditText(this);
                    e.setId(i);
                    mainFiltroLayout.addView(t, params);
                    mainFiltroLayout.addView(e, params);
                    // Substituir por datePicker
                    continue;
                }
            } else {
                if (fieldTitles[i].contains("Valor")) {
                    if (primeiroValor) {
                        TextView t = new TextView(this);
                        t.setText("Por faixa de valor: ");
                        mainFiltroLayout.addView(t, params);
                        primeiroValor = false;
                    }

                    if (fieldTitles[i].contains("Valor") && fieldTitles[i].contains("inicial")) {
                        TextView t = new TextView(this);
                        t.setText(fieldTitles[i]);
                        EditText e = new EditText(this);
                        e.setId(i);
                        mainFiltroLayout.addView(t, params);
                        mainFiltroLayout.addView(e, params);
                        // Substituir por spinner
                        continue;
                    }
                    if (fieldTitles[i].contains("Valor") && fieldTitles[i].contains("final")) {
                        TextView t = new TextView(this);
                        t.setText(fieldTitles[i]);
                        EditText e = new EditText(this);
                        e.setId(i);
                        mainFiltroLayout.addView(t, params);
                        mainFiltroLayout.addView(e, params);
                        // Substituir por spinner
                        continue;
                    }
                } else {
                    TextView t = new TextView(this);
                    t.setText(fieldTitles[i]);
                    EditText e = new EditText(this);
                    e.setId(i);
                    mainFiltroLayout.addView(t, params);
                    mainFiltroLayout.addView(e, params);
                }
            }
        }
    }

}
// TODO Clear na lista ao fechar activity