package devmob2018.com.comandaapp.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.util.ArrayList;

import devmob2018.com.comandaapp.R;
import devmob2018.com.comandaapp.model.database.MyOrmLiteOpenHelper;
import devmob2018.com.comandaapp.model.entity.Categoria;
import devmob2018.com.comandaapp.model.entity.Comanda;
import devmob2018.com.comandaapp.model.entity.ItemComanda;
import devmob2018.com.comandaapp.model.entity.Produto;

public class MainActivity extends Activity {

    private Comanda comanda;

    private TextView valorTotal;

    private ArrayList<ItemComanda> listProdutos;
    private ArrayAdapter adapterProdutos;
    private ListView viewProdutos;

    private MyOrmLiteOpenHelper db;
    private SQLiteDatabase dbForRead;
    private SQLiteDatabase dbForWrite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
//            this.comanda = new Comanda();
            this.db = MyOrmLiteOpenHelper.getInstance(this);


//            this.dbForRead = this.db.getReadableDatabase();
//            this.dbForWrite = this.db.getWritableDatabase();

//            this.dbForWrite.beginTransaction();

            Dao<Categoria, Long> categoriaDAO = this.db.getDao(Categoria.class);
            categoriaDAO.createOrUpdate(new Categoria("Bebida"));
            categoriaDAO.createOrUpdate(new Categoria("Entrada"));
            categoriaDAO.createOrUpdate(new Categoria("Comida"));
            categoriaDAO.createOrUpdate(new Categoria("Sobremesa"));

//            this.dbForWrite.setTransactionSuccessful();
//            this.dbForWrite.endTransaction();

            Dao<Produto, Long> produtoDAO = this.db.getDao(Produto.class);
            produtoDAO.createOrUpdate(new Produto("Água com gás", categoriaDAO.queryForId(1L), 3.5));
            produtoDAO.createOrUpdate(new Produto("Cheese Salada", categoriaDAO.queryForId(3L), 12.0));
            produtoDAO.createOrUpdate(new Produto("Sorvete Chocolate", categoriaDAO.queryForId(4L), 8.8));
            produtoDAO.createOrUpdate(new Produto("Batata Frita", categoriaDAO.queryForId(2L), 4.5));

            Dao<Comanda, Long> comandaDAO = this.db.getDao(Comanda.class);
            comandaDAO.createOrUpdate(this.comanda);

            ArrayList<Produto> produtos = (ArrayList<Produto>) produtoDAO.queryForAll();
            this.comanda = new Comanda(produtos);

            for (Produto p : Comanda.produtosDisponiveis) {

                this.comanda.getItens().add(new ItemComanda(p, 5, this.comanda));

            }

            Dao<ItemComanda, Long> itemComandaDAO = this.db.getDao(ItemComanda.class);
            for (ItemComanda ic : this.comanda.getItens()) {

                itemComandaDAO.createOrUpdate(ic);

            }


            this.valorTotal = findViewById(R.id.valorTotal);
            this.valorTotal.setText(this.comanda.getTotal().toString());

            this.listProdutos = (ArrayList<ItemComanda>) this.comanda.getItens();

            this.adapterProdutos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, this.listProdutos);
            this.viewProdutos = findViewById(R.id.produtos);
            this.viewProdutos.setAdapter(this.adapterProdutos);

            this.viewProdutos.setOnItemClickListener(this.editarItem());
            this.viewProdutos.setOnItemLongClickListener(this.removerItem());

//            Toast.makeText(this, String.valueOf(cursor.getCount()), Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, String.valueOf(e), Toast.LENGTH_LONG).show();
        }

    }

    public void adicionar(View v) {

        Intent it = new Intent(this, AdicaoItemComandaActivity.class);
        it.putExtra("reqCode", "adicionar");
        it.putExtra("itemComanda", new ItemComanda());
        startActivityForResult(it, 1001);

    }

    public void limpar(View v) {
        this.adapterProdutos.clear();
        this.valorTotal.setText(this.comanda.getTotal().toString());
    }

    public void finalizar(View v) {

    }

    private AdapterView.OnItemClickListener editarItem() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.this.adapterProdutos.getItem(position);

                Intent it = new Intent(MainActivity.this, AdicaoItemComandaActivity.class);
                it.putExtra("reqCode", "editar");
                it.putExtra("itemComanda", (ItemComanda) MainActivity.this.adapterProdutos.getItem(position));
                it.putExtra("position", position);
                startActivityForResult(it, 1002);
            }
        };

    }

    private AdapterView.OnItemLongClickListener removerItem() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                alerta.setTitle("Editar Estado");
//                MainActivity.this.adapterProdutos.getItem(position).getProduto().getNome();

                Object o = MainActivity.this.adapterProdutos.getItem(position);
                ItemComanda ic = (ItemComanda) o;

                alerta.setMessage("Deseja excluir o item: " + ic.getProduto().getNome());
                alerta.setIcon(android.R.drawable.ic_menu_edit);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Excluir
                        MainActivity.this.adapterProdutos.remove(MainActivity.this.adapterProdutos.getItem(position));
                        MainActivity.this.valorTotal.setText(MainActivity.this.comanda.getTotal().toString());
                        Toast.makeText(MainActivity.this, "Excluído", Toast.LENGTH_SHORT).show();
                    }
                });
                alerta.setNeutralButton("Não", null);
                alerta.show();

                return true;
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != 0 && data.getExtras() != null) {
            Bundle b = data.getExtras();

            if (b.getSerializable("itemComanda") != null) {

                if (requestCode == 1001) {

                    this.adapterProdutos.add((ItemComanda) b.getSerializable("itemComanda"));

                } else {

                    if (requestCode == 1002) {
                        Integer pos = b.getInt("position");
                        ItemComanda ic = (ItemComanda) b.getSerializable("itemComanda");

                        Object o = MainActivity.this.adapterProdutos.getItem(pos);
                        ItemComanda icc = (ItemComanda) o;

                        icc.setProduto(ic.getProduto());
                        icc.setQuantidade(ic.getQuantidade());
                        this.adapterProdutos.notifyDataSetChanged();

                        Toast.makeText(this, String.valueOf(resultCode), Toast.LENGTH_LONG).show();

                    }
                }

                this.valorTotal.setText(this.comanda.getTotal().toString());
            }
        }

    }


}