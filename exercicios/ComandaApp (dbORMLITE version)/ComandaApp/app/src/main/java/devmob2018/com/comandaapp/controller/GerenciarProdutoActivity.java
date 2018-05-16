package devmob2018.com.comandaapp.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import devmob2018.com.comandaapp.R;
import devmob2018.com.comandaapp.component.ProdutoAdapter;
import devmob2018.com.comandaapp.model.database.MyOrmLiteOpenHelper;
import devmob2018.com.comandaapp.model.entity.Categoria;
import devmob2018.com.comandaapp.model.entity.Comanda;
import devmob2018.com.comandaapp.model.entity.Produto;

public class GerenciarProdutoActivity extends Activity {

    private MyOrmLiteOpenHelper db;
    private SQLiteDatabase dbForRead;
    private SQLiteDatabase dbForWrite;

    Dao<Categoria, Long> categoriaDAO;
    Dao<Produto, Long> produtoDAO;

    private List<Categoria> categoriaList;
    private ArrayAdapter<Categoria> categoriaAdapter;
    private Spinner spCategorias;

    private EditText editNome, editValor;

    private ProdutoAdapter produtoAdapter;
    private ListView lvProdutos;

    private Produto p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_produto);

        this.db = MyOrmLiteOpenHelper.getInstance(this);

        this.dbForRead = this.db.getReadableDatabase();
        this.dbForWrite = this.db.getWritableDatabase();

        this.categoriaList = new ArrayList<>();

        try {
            this.categoriaDAO = this.db.getDao(Categoria.class);

            this.categoriaList = (ArrayList<Categoria>) categoriaDAO.queryForAll();

            this.produtoDAO = this.db.getDao(Produto.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.categoriaAdapter = new ArrayAdapter<Categoria>(this, android.R.layout.simple_spinner_item, this.categoriaList);
        this.spCategorias = findViewById(R.id.spCategorias);
        this.spCategorias.setAdapter(this.categoriaAdapter);

//        this.spCategorias.setOnItemSelectedListener(this.onCategoriaSelect());

        this.editNome = findViewById(R.id.editNome);
        this.editValor = findViewById(R.id.editValor);

//        this.produtoAdapter = new ArrayAdapter<Produto>(this, android.R.layout.simple_list_item_1, Comanda.produtosDisponiveis);
        this.produtoAdapter = new ProdutoAdapter(this, R.layout.produto_list_view_item, Comanda.produtosDisponiveis);

        this.lvProdutos = findViewById(R.id.lvProdutos);
        this.lvProdutos.setOnItemClickListener(this.editarItem());
        this.lvProdutos.setOnItemLongClickListener(this.removerItem());
        this.lvProdutos.setAdapter(this.produtoAdapter);


    }

    public void salvar(View v) {
//        Toast.makeText(this, "IMPLEMENTADO", Toast.LENGTH_LONG).show();

        Editable nomeProduto = this.editNome.getText();
        Editable valorProduto = this.editValor.getText();

        if (nomeProduto.length() > 0 && valorProduto.length() > 0) {
            if (this.spCategorias.getSelectedItem() != null) {

                long res;
                try {

                    Categoria c = (Categoria) this.spCategorias.getSelectedItem();

                    if (p == null) {
                        p = new Produto(nomeProduto.toString(), c, Double.valueOf(valorProduto.toString()));

                        res = produtoDAO.create(p);
                        if (res != -1) {
//                            this.categoriaAdapter.getItem(this.spCategorias.getSelectedItemPosition()).;
                            c = this.categoriaDAO.queryForId(c.getId());

                            this.categoriaAdapter.getItem(this.spCategorias.getSelectedItemPosition()).setProdutos(c.getProdutos());
                            this.produtoAdapter.add(p);

                            Toast.makeText(this, "Adicionado", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        p.setNome(nomeProduto.toString());
                        p.setValor(Double.valueOf(valorProduto.toString()));
                        p.setCategoria(c);

                        res = produtoDAO.update(p);
                        if (res != -1) {
                            Toast.makeText(this, "Atualizado", Toast.LENGTH_LONG).show();
                        }
                    }

                    this.categoriaAdapter.notifyDataSetChanged();
                    this.produtoAdapter.notifyDataSetChanged();

                    this.editNome.setText("");
                    this.editValor.setText("");
                    this.editNome.requestFocus();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        } else {

            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
        }

        p = null;
    }

    private AdapterView.OnItemClickListener editarItem() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(GerenciarProdutoActivity.this);
                alerta.setTitle("Editar Produto");

                Object o = GerenciarProdutoActivity.this.lvProdutos.getItemAtPosition(position);
                p = (Produto) o;

                alerta.setMessage("Deseja editar o produto: " + p.getNome());
                alerta.setIcon(android.R.drawable.ic_menu_edit);

                alerta.setNeutralButton("Sim", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Editar
                        GerenciarProdutoActivity.this.editNome.setText(p.getNome());
                        GerenciarProdutoActivity.this.editValor.setText(String.valueOf(p.getValor()));

                        long ref = GerenciarProdutoActivity.this.p.getCategoria().getId();

                        int s = GerenciarProdutoActivity.this.categoriaList.size();
                        Categoria c;
                        for (int i = 0; i < s; i++) {
                            c = (Categoria) GerenciarProdutoActivity.this.spCategorias.getItemAtPosition(i);
                            long res = c.getId();
                            if (res == ref) {
                                GerenciarProdutoActivity.this.spCategorias.setSelection(i);
                                return;
                            }
                        }
                    }
                });

                alerta.setNegativeButton("Não", null);
                alerta.show();


            }
        };

    }

    private AdapterView.OnItemLongClickListener removerItem() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(GerenciarProdutoActivity.this);
                alerta.setTitle("Remover Produto");

                Object o = GerenciarProdutoActivity.this.produtoAdapter.getItem(position);
                p = (Produto) o;
                final Produto pp = p;
                alerta.setMessage("Deseja excluir o produto: " + p.getNome());
                alerta.setIcon(android.R.drawable.ic_menu_edit);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Excluir
                        try {
                            produtoDAO.delete(pp);

                            GerenciarProdutoActivity.this.produtoAdapter.remove((Produto)GerenciarProdutoActivity.this.produtoAdapter.getItem(position));
                            Toast.makeText(GerenciarProdutoActivity.this, "Excluído", Toast.LENGTH_SHORT).show();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        GerenciarProdutoActivity.this.produtoAdapter.notifyDataSetChanged();
                    }
                });
                alerta.setNeutralButton("Não", null);
                alerta.show();

                p = null;
                return true;
            }
        };
    }

    private AdapterView.OnItemSelectedListener onCategoriaSelect() {
        return new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GerenciarProdutoActivity.this.filtrar(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                GerenciarProdutoActivity.this.produtoAdapter.clear();
            }
        };
    }

    public void filtrar(View v) {

        Categoria c = (Categoria) this.spCategorias.getSelectedItem();

        try {
            c = categoriaDAO.queryForId(c.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.produtoAdapter.clear();
        this.produtoAdapter.addAll(c.getListProdutos());
        this.produtoAdapter.notifyDataSetChanged();

    }


    public void gerenciarCategoria(View v) {
        Intent it = new Intent(this, GerenciarCategoriaActivity.class);
        startActivityForResult(it, 5005);
    }

    @Override
    public void onBackPressed() {
        try {
            Comanda.produtosDisponiveis.clear();
            Comanda.produtosDisponiveis.addAll(produtoDAO.queryForAll());
            setResult(RESULT_OK);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            this.categoriaList = (ArrayList<Categoria>) categoriaDAO.queryForAll();
            this.categoriaAdapter.clear();
            this.categoriaAdapter.addAll(categoriaList);
            this.categoriaAdapter.notifyDataSetChanged();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
