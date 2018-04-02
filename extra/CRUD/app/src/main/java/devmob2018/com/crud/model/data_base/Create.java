package devmob2018.com.crud.model.data_base;

import android.database.sqlite.SQLiteDatabase;

public class Create {

    public void createTable() {

        SQLiteDatabase db = MainDB.getInstance().getWritableDatabase();

        String colunas = "(UID TEXT, NOME TEXT, IDADE INTEGER, PESO REAL, DEFICIENCIA INTEGER)";

        String query = "CREATE TABLE IF NOT EXISTS " + MainDB.TABELA_PESSOA + colunas;

        db.execSQL(query);

    }
}
