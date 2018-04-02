package devmob2018.com.crud.model.data_base;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import devmob2018.com.crud.model.entities.Pessoa;

public class Update {

    public Boolean addPessoa(Pessoa p) {


        SQLiteDatabase db = MainDB.getInstance().getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("UID", p.getUID());
        cv.put("NOME", p.getNome());
        cv.put("IDADE", p.getIdade());
        cv.put("PESO", p.getPeso());
        cv.put("DEFICIENCIA", p.getDeficiencia());

        return db.insert(MainDB.TABELA_PESSOA, null, cv) != -1;
    }

    public Boolean updatePessoa(Pessoa p) {


        SQLiteDatabase db = MainDB.getInstance().getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("UID", p.getUID());
        cv.put("NOME", p.getNome());
        cv.put("IDADE", p.getIdade());
        cv.put("PESO", p.getPeso());
        cv.put("DEFICIENCIA", p.getDeficiencia());

        String where = "UID = '" + p.getUID() + "'";

        return db.update(MainDB.TABELA_PESSOA, cv, where, null) > 0;
    }

}
