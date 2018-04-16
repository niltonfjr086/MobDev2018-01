package devmob2018.com.comandaapp.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import devmob2018.com.comandaapp.model.entity.Categoria;
import devmob2018.com.comandaapp.model.entity.Produto;

/**
 * Created by main on 16/04/18.
 */

public class MyOrmLiteOpenHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "comanda2.db";

    private static final int DATABASE_VERSION = 1;

    private static MyOrmLiteOpenHelper instance = null;

    Dao<Categoria, Integer> categoriaDao = null;
    Dao<Produto, Integer> produtoDao = null;


    private MyOrmLiteOpenHelper(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static MyOrmLiteOpenHelper getInstance(Context c) {

        if (instance == null) {
            instance = new MyOrmLiteOpenHelper(c);
        }

        return instance;
    }

    public Dao<Categoria, Integer> getCategoriaDao() throws Exception {
        if (categoriaDao == null) {
            categoriaDao = getDao(Categoria.class);
        }

        return categoriaDao;
    }

    public Dao<Produto, Integer> getProdutoDao() throws Exception {
        if (produtoDao == null) {
            produtoDao = getDao(Produto.class);
        }

        return produtoDao;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, Categoria.class);
        } catch (Exception e) {
            database.endTransaction();
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
