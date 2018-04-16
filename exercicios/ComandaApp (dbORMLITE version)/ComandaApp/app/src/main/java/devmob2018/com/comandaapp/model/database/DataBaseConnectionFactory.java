package devmob2018.com.comandaapp.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.database.sqlite.SQLiteOpenHelper;

import devmob2018.com.comandaapp.MyApp;

public class DataBaseConnectionFactory extends SQLiteOpenHelper {

    //    private static SQLiteDatabase instance;
    private static DataBaseConnectionFactory instance;

    public static final String NOME_DB = "comanda.db";
    private static final int VERSAO_DB = 1;

    /*  Definir o tipo de acesso ao banco
        0 => Modo Privado
        1 => Outros aplicativos podem ler
        2 => Outros aplicativos podem ler e escrever
     */
    private static final int BANCO_ACESSO = 0; //NÃO ESTÁ SENDO USADO - O QUE IMPLICA?

//    private DataBaseConnectionFactory() {
//
//    }

//    public static SQLiteDatabase getConnection() {
//
//        if (instance == null) {
//            instance = MyApp.getContext().openOrCreateDatabase(NOME_DB, 0, null);
//        }
//
//        return instance;
//    }

    private DataBaseConnectionFactory() {

        super((new MyApp()).getApplicationContext(), NOME_DB, null, VERSAO_DB);
//        MyApp.getContext().openOrCreateDatabase(NOME_DB, BANCO_ACESSO, null);
    }

    public DataBaseConnectionFactory(Context c) {
        super(c, NOME_DB, null, VERSAO_DB);
//        MyApp.getContext().openOrCreateDatabase(NOME_DB, BANCO_ACESSO, null);
    }

    //
//    private DataBaseConnectionFactory(String NOME_DB, int VERSAO_DB, int BANCO_ACESSO) {
//        super(MyApp.getContext(), NOME_DB, null, VERSAO_DB);
////        MyApp.getContext().openOrCreateDatabase(NOME_DB, BANCO_ACESSO, null);
//    }
//
    public static DataBaseConnectionFactory getInstance() {
        MyApp m = new MyApp();
        if (instance == null) instance = new DataBaseConnectionFactory(m.getApplicationContext());

        return instance;
    }

    public static DataBaseConnectionFactory getInstance(Context c) {
        if (instance == null) instance = new DataBaseConnectionFactory(c.getApplicationContext());

        return instance;
    }


//    public static DataBaseConnectionFactory getInstance(String NOME_DB, int VERSAO_DB, int BANCO_ACESSO) {
//        instance = new DataBaseConnectionFactory(NOME_DB, VERSAO_DB, BANCO_ACESSO);
//
//        return instance;
//    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
