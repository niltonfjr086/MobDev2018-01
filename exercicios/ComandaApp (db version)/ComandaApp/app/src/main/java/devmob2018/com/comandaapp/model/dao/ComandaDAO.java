package devmob2018.com.comandaapp.model.dao;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

import devmob2018.com.comandaapp.model.entity.Comanda;

public class ComandaDAO {
    final String TABELA_NOME = "tb_comanda";


    public boolean cadastrar(Comanda comanda, SQLiteDatabase db) {

        db.beginTransaction();

        //Monta valores para parâmetro no insert
        ContentValues values = new ContentValues();

        values.put("dtAbertura", String.valueOf(comanda.getDtAbertura()));


        //Realiza o cadastro no banco
        long res = db.insert(TABELA_NOME, null,values);
//        datetime('now')
        db.setTransactionSuccessful();
        db.endTransaction();

        if (res == -1)
            return false;
        else
            return true;

    }

}
