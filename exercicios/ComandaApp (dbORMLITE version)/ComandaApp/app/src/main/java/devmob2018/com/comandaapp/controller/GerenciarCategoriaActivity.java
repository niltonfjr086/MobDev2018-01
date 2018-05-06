package devmob2018.com.comandaapp.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
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

public class GerenciarCategoriaActivity extends Activity {

    private MyOrmLiteOpenHelper db;
    private SQLiteDatabase dbForRead;
    private SQLiteDatabase dbForWrite;

    Dao<Categoria, Long> categoriaDAO;

    private List<Categoria> categoriaList;
    private ArrayAdapter<Categoria> categoriaAdapter;
    private ListView lvCategorias;

    private EditText editNomeCategoria;

    private Categoria c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_categoria);

        this.db = MyOrmLiteOpenHelper.getInstance(this);

        this.dbForRead = this.db.getReadableDatabase();
        this.dbForWrite = this.db.getWritableDatabase();

        this.categoriaList = new ArrayList<>();

        try {
            this.categoriaDAO = this.db.getDao(Categoria.class);

            this.categoriaList = (ArrayList<Categoria>) categoriaDAO.queryForAll();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.categoriaAdapter = new ArrayAdapter<Categoria>(this, android.R.layout.simple_spinner_item, this.categoriaList);


        this.editNomeCategoria = findViewById(R.id.editNomeCategoria);

        this.lvCategorias = findViewById(R.id.lvCategorias);
        this.lvCategorias.setAdapter(this.categoriaAdapter);

        this.lvCategorias.setOnItemClickListener(this.editarItem());
        this.lvCategorias.setOnItemLongClickListener(this.removerItem());

    }

    public void salvar(View v) {

        Editable categoriaNome = this.editNomeCategoria.getText();

        if (categoriaNome.length() > 0) {

            long res;
            if (c == null) {
                c = new Categoria(categoriaNome.toString());
                try {
                    res = categoriaDAO.create(c);

                    if (res != -1)
                        this.categoriaAdapter.add(c);
                    Toast.makeText(this, "Adicionado", Toast.LENGTH_LONG).show();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {
                c.setNome(categoriaNome.toString());

                try {
                    res = categoriaDAO.update(c);
                    if (res != -1)
                        Toast.makeText(this, "Atualizado", Toast.LENGTH_LONG).show();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            this.categoriaAdapter.notifyDataSetChanged();
            this.editNomeCategoria.setText("");
            this.editNomeCategoria.requestFocus();
        }
        c = null;
    }

    private AdapterView.OnItemClickListener editarItem() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(GerenciarCategoriaActivity.this);
                alerta.setTitle("Editar Categoria");

                Object o = GerenciarCategoriaActivity.this.lvCategorias.getItemAtPosition(position);
                GerenciarCategoriaActivity.this.c = (Categoria) o;

                alerta.setMessage("Deseja editar a categoria: " + c.getNome());
                alerta.setIcon(android.R.drawable.ic_menu_edit);

                alerta.setNeutralButton("Sim", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Editar
                        GerenciarCategoriaActivity.this.editNomeCategoria.setText(c.getNome());
                    }
                });
                alerta.setNegativeButton("Não", null);
                alerta.show();
//                c = null;
            }
        };

    }

    private AdapterView.OnItemLongClickListener removerItem() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(GerenciarCategoriaActivity.this);
                alerta.setTitle("Remover Produto");

                Object o = GerenciarCategoriaActivity.this.lvCategorias.getItemAtPosition(position);
                GerenciarCategoriaActivity.this.c = (Categoria) o;

                final Categoria cc = GerenciarCategoriaActivity.this.c;
                final int listaSize = GerenciarCategoriaActivity.this.c.getListProdutos().size();

                alerta.setMessage("Deseja excluir a categoria: " + c.getNome());
                alerta.setIcon(android.R.drawable.ic_menu_edit);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Excluir
                        if (listaSize > 0) {
                            Toast.makeText(GerenciarCategoriaActivity.this, "Categoria possui produto(s)", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(GerenciarCategoriaActivity.this, cc.toString(), Toast.LENGTH_LONG).show();
                            Toast.makeText(GerenciarCategoriaActivity.this, "Sem produto(s)", Toast.LENGTH_LONG).show();

                            try {

                                long res = GerenciarCategoriaActivity.this.categoriaDAO.delete(cc);

//                                Toast.makeText(GerenciarCategoriaActivity.this, String.valueOf(res), Toast.LENGTH_SHORT).show();

                                GerenciarCategoriaActivity.this.categoriaAdapter.remove(GerenciarCategoriaActivity.this.categoriaAdapter.getItem(position));
                                Toast.makeText(GerenciarCategoriaActivity.this, "Excluída", Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            GerenciarCategoriaActivity.this.categoriaAdapter.notifyDataSetChanged();
                        }

                    }
                });
                alerta.setNeutralButton("Não", null);
                alerta.show();

                c = null;
                return true;
            }
        };
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }

}
