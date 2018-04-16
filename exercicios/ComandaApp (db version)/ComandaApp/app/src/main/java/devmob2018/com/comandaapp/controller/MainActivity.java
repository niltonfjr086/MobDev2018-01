package devmob2018.com.comandaapp.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import devmob2018.com.comandaapp.MyApp;
import devmob2018.com.comandaapp.R;
import devmob2018.com.comandaapp.model.dao.ComandaDAO;
import devmob2018.com.comandaapp.model.dao.ItemComandaDAO;
import devmob2018.com.comandaapp.model.dao.ProdutoDAO;
import devmob2018.com.comandaapp.model.database.CreatePopulatedTables;
import devmob2018.com.comandaapp.model.database.DataBaseConnectionFactory;
import devmob2018.com.comandaapp.model.entity.Comanda;
import devmob2018.com.comandaapp.model.entity.ItemComanda;
import devmob2018.com.comandaapp.model.entity.Produto;

public class MainActivity extends Activity {

    private Comanda comanda;

    private TextView valorTotal;

    private ArrayAdapter<ItemComanda> adapterProdutos;
    private ListView viewProdutos;

        SQLiteDatabase db;
    private SQLiteDatabase dbForRead;
    private SQLiteDatabase dbForWrite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            this.comanda = new Comanda();

//            this.dbForRead = DataBaseConnectionFactory.getInstance(this).getReadableDatabase();
            this.dbForWrite = DataBaseConnectionFactory.getInstance(this).getWritableDatabase();

//            ProdutoDAO produtoDAO = new ProdutoDAO();
//            List<Produto> produtos = produtoDAO.listarTodos(this.dbForRead);

//            this.comanda = new Comanda(produtos);
//                this.comanda.getItens().add(new ItemComanda(Comanda.produtosDisponiveis.get(1), 5));

            for (Produto p : Comanda.produtosDisponiveis) {

                this.comanda.getItens().add(new ItemComanda(p, 5));

            }

            this.valorTotal = findViewById(R.id.valorTotal);
            this.valorTotal.setText(this.comanda.getTotal().toString());

            this.adapterProdutos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, this.comanda.getItens());
            this.viewProdutos = findViewById(R.id.produtos);
            this.viewProdutos.setAdapter(this.adapterProdutos);

            this.viewProdutos.setOnItemClickListener(this.editarItem());
            this.viewProdutos.setOnItemLongClickListener(this.removerItem());


//            CreatePopulatedTables.createTableProduto(this.dbForWrite);

//            CreatePopulatedTables.dropTableComanda(this.dbForWrite);
//            CreatePopulatedTables.createTableComanda(this.dbForWrite);
//            CreatePopulatedTables.createTableItemComanda(this.dbForWrite);
//            CreatePopulatedTables.dropTableItemComanda(this.dbForWrite);

//            Cursor cursor = DataBaseConnectionFactory.getConnection().rawQuery("SELECT * FROM tb_item_comanda", null);
//            Toast.makeText(this, String.valueOf(cursor.getCount()), Toast.LENGTH_LONG).show();

//            this.dbForWrite.beginTransaction();
//            ContentValues values = new ContentValues();
//            values.put("nome", "Pastel");
//            values.put("valor", 5.5);
//            this.dbForWrite.insert("tb_produto", null, values);
//            this.dbForWrite.setTransactionSuccessful();
//            this.dbForWrite.endTransaction();

//            ComandaDAO cd = new ComandaDAO();
//            Comanda c = new Comanda();
//            cd.cadastrar(c,this.dbForWrite);

//            this.dbForWrite.beginTransaction();
//            ContentValues values = new ContentValues();
//            values.put("dtAbertura", String.valueOf(new Date()));
//
//            this.dbForWrite.insert("tb_comanda", null, values);
//            this.dbForWrite.setTransactionSuccessful();
//            this.dbForWrite.endTransaction();


//            this.db = this.openOrCreateDatabase("comanda.db", 0, null);
//            this.db.beginTransaction();
//            this.db.insert("tb_comanda", null, new ContentValues());
//            this.db.setTransactionSuccessful();
//            this.db.endTransaction();

//            cd.cadastrar(c, this.db);

//            this.dbForWrite.insert("tb_comanda", null, null);
//            Cursor cursor = this.db.rawQuery("SELECT * FROM tb_comanda", null);
//            Toast.makeText(this, String.valueOf(cursor.getCount()), Toast.LENGTH_LONG).show();

//            ItemComandaDAO icd = new ItemComandaDAO();
//            ItemComanda ic = new ItemComanda();
//            ic.setQuantidade(23);
//            ic.setProduto(Comanda.produtosDisponiveis.get(3));
//            ic.setComandaId(1l);
//            this.dbForWrite.insert();
//            icd.cadastrar(ic, this.dbForWrite);

//            Cursor cursor = dbForRead.rawQuery("SELECT * FROM tb_item_comanda", null);
//
//            Toast.makeText(this, String.valueOf(cursor.getCount()), Toast.LENGTH_LONG).show();


        } catch (Exception e) {


            Toast.makeText(this, String.valueOf(e), Toast.LENGTH_LONG).show();
        }

    }

    public void adicionar(View v) {

        Intent it = new Intent(this, AdicaoProdutoActivity.class);
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

                Intent it = new Intent(MainActivity.this, AdicaoProdutoActivity.class);
                it.putExtra("reqCode", "editar");
                it.putExtra("itemComanda", MainActivity.this.adapterProdutos.getItem(position));
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
                alerta.setMessage("Deseja excluir o item: " + MainActivity.this.adapterProdutos.getItem(position).getProduto().getNome());
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

                        this.adapterProdutos.getItem(pos).setProduto(ic.getProduto());
                        this.adapterProdutos.getItem(pos).setQuantidade(ic.getQuantidade());
                        this.adapterProdutos.notifyDataSetChanged();

                        Toast.makeText(this, String.valueOf(resultCode), Toast.LENGTH_LONG).show();

                    }
                }

                this.valorTotal.setText(this.comanda.getTotal().toString());
            }
        }

    }


}