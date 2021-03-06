package devmob2018.com.comandaapp.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import devmob2018.com.comandaapp.R;
import devmob2018.com.comandaapp.component.ItemComandaAdapter;
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

    Dao<Comanda, Long> comandaDAO;
    Dao<ItemComanda, Long> itemComandaDAO;
    Dao<Categoria, Long> categoriaDAO;
    Dao<Produto, Long> produtoDAO;

    final int REQUEST_QR_CODE = 10;
    final int REQUEST_BAR_CODE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
//            this.comanda = new Comanda();

//            this.deleteDatabase("comandas.db");

            this.db = MyOrmLiteOpenHelper.getInstance(this);

            this.comandaDAO = this.db.getDao(Comanda.class);
            this.itemComandaDAO = this.db.getDao(ItemComanda.class);

            this.categoriaDAO = this.db.getDao(Categoria.class);
            if (categoriaDAO.queryForAll().size() <= 0) {
                categoriaDAO.createOrUpdate(new Categoria("Bebida"));
                categoriaDAO.createOrUpdate(new Categoria("Entrada"));
                categoriaDAO.createOrUpdate(new Categoria("Comida"));
                categoriaDAO.createOrUpdate(new Categoria("Sobremesa"));
            }

            this.produtoDAO = this.db.getDao(Produto.class);
            if (produtoDAO.queryForAll().size() <= 0) {
                produtoDAO.createOrUpdate(new Produto("Água com gás", categoriaDAO.queryForId(1L), 3.5));
                produtoDAO.createOrUpdate(new Produto("Cheese Salada", categoriaDAO.queryForId(3L), 12.0));
                produtoDAO.createOrUpdate(new Produto("Sorvete Chocolate", categoriaDAO.queryForId(4L), 8.8));
                produtoDAO.createOrUpdate(new Produto("Batata Frita", categoriaDAO.queryForId(2L), 4.5));
            }


//            Dao<Comanda, Long> comandaDAO = this.db.getDao(Comanda.class);
//            comandaDAO.createOrUpdate(this.comanda);

            try {
                this.comanda = (Comanda) savedInstanceState.getSerializable("comandaTransient");
            } catch (Exception e) {
                ArrayList<Produto> produtos = (ArrayList<Produto>) produtoDAO.queryForAll();
                this.comanda = new Comanda(produtos);
            }

//            for (Produto p : Comanda.produtosDisponiveis) {
//
//                this.comanda.getItens().add(new ItemComanda(p, 5, this.comanda));
//
//            }

//            Dao<ItemComanda, Long> itemComandaDAO = this.db.getDao(ItemComanda.class);
//            for (ItemComanda ic : this.comanda.getItens()) {
//
//                itemComandaDAO.createOrUpdate(ic);
//
//            }


            this.valorTotal = findViewById(R.id.valorTotal);
            this.valorTotal.setText(this.comanda.getTotal().toString());

            this.listProdutos = (ArrayList<ItemComanda>) this.comanda.getItens();

//            this.adapterProdutos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, this.listProdutos);
            this.adapterProdutos = new ItemComandaAdapter(this, R.layout.item_comanda_list_view_item, this.listProdutos);

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

        if (this.comanda.getItens().size() == 0) {
            try {
                comandaDAO.createOrUpdate(this.comanda);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

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
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                alerta.setTitle("Editar Item");

                Object o = MainActivity.this.adapterProdutos.getItem(position);
                final ItemComanda ic = (ItemComanda) o;

                alerta.setMessage("Deseja editar o item: " + ic.getProduto().getNome());
                alerta.setIcon(android.R.drawable.ic_menu_edit);

                alerta.setNeutralButton("Sim", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Editar
                        Intent it = new Intent(MainActivity.this, AdicaoItemComandaActivity.class);
                        it.putExtra("reqCode", "editar");
                        it.putExtra("itemComanda", ic);
                        it.putExtra("position", position);
                        startActivityForResult(it, 1002);
                    }
                });

                alerta.setNegativeButton("Não", null);

                alerta.setPositiveButton("+", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int qtd = ic.getQuantidade() + 1;
                        ic.setQuantidade(qtd);
                        try {
                            itemComandaDAO.createOrUpdate(ic);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        MainActivity.this.adapterProdutos.notifyDataSetChanged();
                        MainActivity.this.valorTotal.setText(Double.valueOf(MainActivity.this.comanda.getTotal()).toString());
                    }
                });
                alerta.show();


            }
        };

    }

    private AdapterView.OnItemLongClickListener removerItem() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                alerta.setTitle("Remover Item");
//                MainActivity.this.adapterProdutos.getItem(position).getProduto().getNome();

                Object o = MainActivity.this.adapterProdutos.getItem(position);
                final ItemComanda ic = (ItemComanda) o;

                alerta.setMessage("Deseja excluir o item: " + ic.getProduto().getNome());
                alerta.setIcon(android.R.drawable.ic_menu_edit);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Excluir
                        try {
                            produtoDAO.deleteById(ic.getId());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
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
                    ItemComanda ic = (ItemComanda) b.getSerializable("itemComanda");
                    try {
                        itemComandaDAO.createOrUpdate(ic);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    this.adapterProdutos.add(ic);

                } else {

                    if (requestCode == 1002) {
                        Integer pos = b.getInt("position");
                        ItemComanda ic = (ItemComanda) b.getSerializable("itemComanda");
                        try {
                            itemComandaDAO.createOrUpdate(ic);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        Object o = MainActivity.this.adapterProdutos.getItem(pos);
                        ItemComanda icc = (ItemComanda) o;

                        icc.setProduto(ic.getProduto());
                        icc.setQuantidade(ic.getQuantidade());
                        this.valorTotal.setText(this.comanda.getTotal().toString());
                        this.adapterProdutos.notifyDataSetChanged();

//                        Toast.makeText(this, String.valueOf(resultCode), Toast.LENGTH_LONG).show();

                    }
                }

                this.valorTotal.setText(this.comanda.getTotal().toString());
            } else {

                if (resultCode == RESULT_OK) {
//                    Toast.makeText(this, "DEU OK: " + resultCode, Toast.LENGTH_LONG).show();

                    String resultado;
                    switch (requestCode) {

                        case REQUEST_QR_CODE:
//                            Toast.makeText(this, "QRCode: \n" + data.getStringExtra("SCAN_RESULT"), Toast.LENGTH_LONG).show();
                            try {

                                String retorno = data.getStringExtra("SCAN_RESULT");
                                char[] cs = retorno.toCharArray();
                                retorno = "";
                                for (char c : cs) {
                                    if (Character.isDigit(c)) {
                                        retorno += c;
                                    }
                                }

                                Produto p = null;
                                if(retorno.length() > 0){
                                    Long id = Long.parseLong(retorno);
                                    p = produtoDAO.queryForId(id);
                                }

                                if (p != null && p.getId() != null) {
                                    this.comanda.adicionarItem(new ItemComanda(p, 1));
//                                produtoDAO.queryForId(Long.parseLong(data.getStringExtra("SCAN_RESULT")));
                                    this.valorTotal.setText(this.comanda.getTotal().toString());
                                    this.adapterProdutos.notifyDataSetChanged();

                                } else {
                                    Toast.makeText(this, "Produto inexistente", Toast.LENGTH_LONG).show();
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            break;

                        case REQUEST_BAR_CODE:
                            Toast.makeText(this, "BarCode: \n" + data.getStringExtra("SCAN_RESULT"), Toast.LENGTH_LONG).show();
                            break;

                    }

                }

            }

        } else {

            Toast.makeText(this, "Cancelou/voltou: " + resultCode, Toast.LENGTH_LONG).show();

        }

    }

    // Autor: Professor Renato
    public void scanQRCode(View v) {
        try {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, REQUEST_QR_CODE);
        } catch (ActivityNotFoundException anfe) {
            Toast.makeText(this, "Baixe o qr code", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    // Autor: Professor Renato
    public void scanBarCode(View v) {
        try {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, REQUEST_BAR_CODE);
        } catch (ActivityNotFoundException anfe) {
            Toast.makeText(this, "Baixe o qr code", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

//    @Override
//    public void onDestroy() {
//        if(this.comanda != null && this.comanda.getId() != null){
//            this.comanda.setDtFechamento(new Date());
//            try {
//                this.comandaDAO.createOrUpdate(this.comanda);
//            } catch (SQLException e) {
//                this.comanda.setDtFechamento(null);
//                e.printStackTrace();
//            }
//        }
//        super.onDestroy();
//    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putSerializable("comandaTransient", this.comanda);

        super.onSaveInstanceState(savedInstanceState);
    }

//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//
//        super.onRestoreInstanceState(savedInstanceState);
//
//        Toast.makeText(this, "Restaurou", Toast.LENGTH_LONG).show();
//    }

}