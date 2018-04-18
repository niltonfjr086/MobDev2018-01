package devmob2018.com.comandaapp.controller;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import devmob2018.com.comandaapp.R;
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

    private ArrayAdapter<Produto> produtoAdapter;
    private ListView lvProdutos;

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
            this.categoriaList = categoriaDAO.queryForAll();

            this.produtoDAO = this.db.getDao(Produto.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.categoriaAdapter = new ArrayAdapter<Categoria>(this, android.R.layout.simple_spinner_item, this.categoriaList);
        this.spCategorias = findViewById(R.id.spCategorias);
        this.spCategorias.setAdapter(this.categoriaAdapter);

        this.editNome = findViewById(R.id.editNome);
        this.editValor = findViewById(R.id.editValor);

        this.produtoAdapter = new ArrayAdapter<Produto>(this, android.R.layout.simple_spinner_item, Comanda.produtosDisponiveis);
        this.lvProdutos = findViewById(R.id.lvProdutos);
        this.lvProdutos.setAdapter(this.produtoAdapter);

    }

    public void salvar(View v) {
        Toast.makeText(this, "IMPLEMENTAR", Toast.LENGTH_LONG).show();

        Editable nomeProduto = this.editNome.getText();
        Editable valorProduto = this.editValor.getText();

        if (nomeProduto.length() > 0 && valorProduto.length() > 0) {
            if (this.spCategorias.getSelectedItem() != null) {
                Produto p = new Produto(nomeProduto.toString(), (Categoria) this.spCategorias.getSelectedItem(), Double.valueOf(valorProduto.toString()));

                try {
                    produtoDAO.create(p);
                    this.produtoAdapter.add(p);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void gerenciarCategoria(View v) {
        Toast.makeText(this, "IMPLEMENTAR", Toast.LENGTH_LONG).show();
    }
}
