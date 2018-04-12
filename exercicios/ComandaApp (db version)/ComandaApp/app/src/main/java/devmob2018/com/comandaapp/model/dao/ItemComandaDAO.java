package devmob2018.com.comandaapp.model.dao;

import android.content.ContentValues;

import devmob2018.com.comandaapp.controller.MainActivity;
import devmob2018.com.comandaapp.model.database.DataBaseConnectionFactory;
import devmob2018.com.comandaapp.model.entity.ItemComanda;

public class ItemComandaDAO {

    DataBaseConnectionFactory d;

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
}
