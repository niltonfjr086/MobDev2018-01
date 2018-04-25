package devmob2018.com.comandaapp.model.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import devmob2018.com.comandaapp.controller.MainActivity;
import devmob2018.com.comandaapp.model.database.DataBaseConnectionFactory;
import devmob2018.com.comandaapp.model.entity.ItemComanda;

public class ItemComandaDAO {

    final String TABELA_NOME = "tb_item_comanda";

//    DataBaseConnectionFactory d;

//    public boolean cadastrar(ItemComanda item) {
//
//        ContentValues values = new ContentValues();
//        values.put("produto_id", item.getProduto().getId());
//        values.put("quantidade", item.getQuantidade());
//        values.put("subtotal", item.getSubtotal());
//
//        //Realiza o cadastro no banco
//        long res = MainActivity.db.insert(TABELA_NOME, null, values);
//
//        if (res == -1)
//            return false;
//        else
//            return true;
//
//    }

    /**
     * Created by Renato on 31/08/2016.
     * Método responsável pelo cadastro de um itemComanda
     *
     * @param itemComanda
     * @return boolean
     */
    public boolean cadastrar(ItemComanda itemComanda, SQLiteDatabase db) {

        //Monta valores para parâmetro no insert
        ContentValues values = new ContentValues();
        values.put("id", itemComanda.getId() != null ? itemComanda.getId() : null);

        values.put("produto_id", itemComanda.getProduto().getId());
        values.put("quantidade", itemComanda.getQuantidade());
//        values.put("comanda_id", itemComanda.getComanda().getId());
        values.put("subtotal", itemComanda.getSubtotal());

        //Realiza o cadastro no banco
        long res = db.insert(TABELA_NOME, null, values);

        if (res == -1)
            return false;
        else
            return true;

    }
}
