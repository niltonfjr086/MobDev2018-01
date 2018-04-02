package devmob2018.com.crud.model.data_base;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import devmob2018.com.crud.model.entities.Pessoa;

public class Read {

    public List<Pessoa> getPessoas() {

        SQLiteDatabase db = MainDB.getInstance().getReadableDatabase();

        String query = "SELECT * FROM " + MainDB.TABELA_PESSOA;

        List<Pessoa> list = new ArrayList<>();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {

            do {

                Pessoa p = new Pessoa(c.getString(0));
                p.setNome(c.getString(1));
                p.setIdade(c.getInt(2));
                p.setPeso(c.getDouble(3));
                p.setDeficiencia(c.getInt(4) == 1);

                list.add(p);

            } while (c.moveToNext());
        }

        c.close();

        return list;
    }
}
