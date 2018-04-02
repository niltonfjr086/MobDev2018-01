package devmob2018.com.crud.model.data_base;

import android.database.sqlite.SQLiteDatabase;

import devmob2018.com.crud.model.entities.Pessoa;

public class Delete {

    public void removerTabela() {

        SQLiteDatabase db = MainDB.getInstance().getWritableDatabase();

        String query = "DROP DATABASE IF EXISTS " + MainDB.TABELA_PESSOA;

        db.execSQL(query, null);
    }

    public Boolean removerPessoa(Pessoa p){

        SQLiteDatabase db = MainDB.getInstance().getWritableDatabase();

        String where = "DELETE DATABASE IF EXISTS " + MainDB.TABELA_PESSOA;



    }

}
