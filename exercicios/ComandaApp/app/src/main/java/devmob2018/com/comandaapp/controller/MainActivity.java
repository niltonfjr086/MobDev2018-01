package devmob2018.com.comandaapp.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import devmob2018.com.comandaapp.R;
import devmob2018.com.comandaapp.model.entity.Comanda;
import devmob2018.com.comandaapp.model.entity.ItemComanda;
import devmob2018.com.comandaapp.model.entity.Produto;

public class MainActivity extends Activity {

    private Comanda comanda;
    private ItemComanda item;
    private Produto produto;

    Spinner produtos;
    NumberPicker qtdProduto;
    TextView valorTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.comanda = new Comanda();

        this.valorTotal = findViewById(R.id.valorTotal);
        this.valorTotal.setText(this.comanda.getTotal().toString());

    }

    public void adicionar(View v) {

    }

    public void remover(View v) {

    }

    public void limpar(View v) {

    }
}
