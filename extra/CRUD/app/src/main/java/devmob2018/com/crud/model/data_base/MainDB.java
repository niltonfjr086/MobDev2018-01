package devmob2018.com.crud.model.data_base;

/**
 * https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper.html#getReadableDatabase()
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MainDB extends SQLiteOpenHelper {

    public static final String NOME_DB = "DB";
    private static final int VERSAO_DB = 1;
    public static final String TABELA_PESSOA = "TABELA_PESSOA";

    private static MainDB instance;

    public static MainDB getInstance() {
        if (instance == null) instance = new MainDB();

        return instance;
    }

    private MainDB() {
        super(MyApp.getContext(), NOME_DB, null, VERSAO_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
