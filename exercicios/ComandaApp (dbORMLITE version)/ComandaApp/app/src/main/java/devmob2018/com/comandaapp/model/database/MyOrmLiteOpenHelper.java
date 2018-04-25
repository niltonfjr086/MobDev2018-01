package devmob2018.com.comandaapp.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import devmob2018.com.comandaapp.model.entity.Categoria;
import devmob2018.com.comandaapp.model.entity.Comanda;
import devmob2018.com.comandaapp.model.entity.ItemComanda;
import devmob2018.com.comandaapp.model.entity.Produto;

public class MyOrmLiteOpenHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "comandas.db";

    private static final int DATABASE_VERSION = 2;

    private static MyOrmLiteOpenHelper instance = null;

    Dao<Categoria, Long> categoriaDao = null;
    Dao<Produto, Long> produtoDao = null;

    private MyOrmLiteOpenHelper(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static MyOrmLiteOpenHelper getInstance(Context c) {

        if (instance == null) {
            instance = new MyOrmLiteOpenHelper(c);
        }
//        c.deleteDatabase("comandas.db");

        return instance;
    }

//    public Dao<Categoria, Long> getCategoriaDao() throws Exception {
//        if (categoriaDao == null) {
//            categoriaDao = getDao(Categoria.class);
//        }
//
//        return categoriaDao;
//    }

//    public Dao<Produto, Long> getProdutoDao() throws Exception {
//        if (produtoDao == null) {
//            produtoDao = getDao(Produto.class);
//        }
//
//        return produtoDao;
//    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {

            TableUtils.createTable(connectionSource, Categoria.class);
            TableUtils.createTable(connectionSource, Produto.class);
            TableUtils.createTable(connectionSource, Comanda.class);
            TableUtils.createTable(connectionSource, ItemComanda.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
//            this.onCreate(database, connectionSource);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
