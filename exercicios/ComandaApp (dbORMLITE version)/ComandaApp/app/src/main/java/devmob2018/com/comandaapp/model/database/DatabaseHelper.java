package devmob2018.com.comandaapp.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import devmob2018.com.comandaapp.model.entity.Comanda;

/**
 * Created by https://blog.jayway.com/2016/03/15/android-ormlite/.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "comanda.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Comanda, Integer> mComandaDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Comanda.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Comanda.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* Comanda */

    public Dao<Comanda, Integer> getUserDao() throws SQLException {
        if (mComandaDao == null) {
            mComandaDao = getDao(Comanda.class);
        }

        return mComandaDao;
    }

    @Override
    public void close() {
        mComandaDao = null;

        super.close();
    }
}