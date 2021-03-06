package devmob2018.com.comandaapp.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import devmob2018.com.comandaapp.R;
import devmob2018.com.comandaapp.model.entity.Comanda;
import devmob2018.com.comandaapp.model.entity.ItemComanda;
import devmob2018.com.comandaapp.model.entity.Produto;

public class AdicaoProdutoActivity extends Activity {

    private String requestCommand;
    private Integer position;
    private ItemComanda itemComanda;

    private ArrayAdapter<Produto> adapterProduto;
    private Spinner produtos;

    private NumberPicker qtdProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicao_produto);

//        Toast.makeText(this, String.valueOf(getRequestedOrientation()), Toast.LENGTH_LONG).show();

        this.adapterProduto = new ArrayAdapter<Produto>(this, android.R.layout.simple_spinner_item, Comanda.produtosDisponiveis);
        this.produtos = findViewById(R.id.spProdutos);
        this.produtos.setAdapter(this.adapterProduto);

        this.qtdProduto = findViewById(R.id.qtdProduto);
        this.qtdProduto.setMinValue(1);
        this.qtdProduto.setMaxValue(10);
        this.qtdProduto.setValue(1);


        Bundle b = getIntent().getExtras();
        this.itemComanda = (ItemComanda) b.getSerializable("itemComanda");

        this.requestCommand = b.getString("reqCode");

        if (requestCommand.equals("editar")) {
            this.position = b.getInt("position");

            for (int i = 0; i < Comanda.produtosDisponiveis.size(); i++) {
                if (Comanda.produtosDisponiveis.get(i).equals(this.itemComanda.getProduto())) {
                    this.produtos.setSelection(i);
                    break;
                }
            }

            this.qtdProduto.setValue(this.itemComanda.getQuantidade());
        }


    }


    public void salvar(View v) {

        this.itemComanda.getProduto().getId();

        Toast.makeText(this, this.produtos.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

        this.itemComanda.setProduto((Produto) this.produtos.getSelectedItem());
        this.itemComanda.setQuantidade(this.qtdProduto.getValue());

        Intent it = new Intent(this, MainActivity.class);
        it.putExtra("itemComanda", this.itemComanda);

        if (requestCommand.equals("editar")) {
            it.putExtra("position", this.position);
        }

        setResult(RESULT_OK, it);
        finish();
    }
}
