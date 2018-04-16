package devmob2018.com.comandaapp.model.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import devmob2018.com.comandaapp.model.entity.Comanda;
import devmob2018.com.comandaapp.model.entity.Produto;

public class CreatePopulatedTables {


    public static boolean createTableProduto(SQLiteDatabase db) {

        long res = -1;

        db.beginTransaction();

        db.execSQL("CREATE TABLE IF NOT EXISTS " + "tb_produto" + "(" +
                "id integer not null primary key autoincrement," +
                "nome varchar(45) not null," +
                "valor float not null)");


        for (Produto p : Comanda.produtosDisponiveis) {
            ContentValues values = new ContentValues();
            values.put("nome", p.getNome());
            values.put("valor", p.getValor());
            res = db.insert("tb_produto", null, values);
        }

        db.setTransactionSuccessful();
        db.endTransaction();

        if (res == -1) return false;
        else return true;
    }

    public static boolean createTableItemComanda(SQLiteDatabase db) {

        db.beginTransaction();

        db.execSQL("CREATE TABLE IF NOT EXISTS " + "tb_item_comanda" + "(" +
                "id integer not null primary key autoincrement," +
                "produto_id integer not null, " +
                "quantidade integer not null," +

                "comanda_id integer not null," +

                "subtotal float not null," +

                "FOREIGN KEY (produto_id) REFERENCES tb_produto(id)," +
                "FOREIGN KEY (comanda_id) REFERENCES tb_comanda(id))");


        Cursor cursor = db.rawQuery("SELECT * FROM tb_item_comanda", null);


        db.setTransactionSuccessful();
        db.endTransaction();

        if (Character.isDigit(cursor.getCount())) return true;
        else return false;

    }

    public static boolean dropTableItemComanda(SQLiteDatabase db) throws Exception {

        db.beginTransaction();

        db.execSQL("DROP TABLE IF EXISTS " + "tb_item_comanda");


//        Cursor cursor = db.rawQuery("SELECT * FROM tb_item_comanda", null);


        db.setTransactionSuccessful();
        db.endTransaction();
        return true;
//        if (!Character.isDigit(cursor.getCount())) return true;
//        else return false;


    }


    public static boolean createTableComanda(SQLiteDatabase db) {

        db.beginTransaction();

        db.execSQL("CREATE TABLE IF NOT EXISTS " + "tb_comanda" + "(" +
                "id integer not null primary key autoincrement," +
                "dtAbertura datetime not null," +
                "dtFechamento datetime)");
//                dtFechamento datetime default datetime('now')


        Cursor cursor = db.rawQuery("SELECT * FROM tb_comanda", null);


        db.setTransactionSuccessful();
        db.endTransaction();

        if (Character.isDigit(cursor.getCount())) return true;
        else return false;

    }


    public static boolean dropTableComanda(SQLiteDatabase db) throws Exception {

        db.beginTransaction();

        db.execSQL("DROP TABLE IF EXISTS " + "tb_comanda");


//        Cursor cursor = db.rawQuery("SELECT * FROM tb_comanda", null);


        db.setTransactionSuccessful();
        db.endTransaction();
        return true;
//        if (!Character.isDigit(cursor.getCount())) return true;
//        else return false;


    }
}