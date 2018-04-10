package devmob2018.com.comandaapp;

import org.junit.Test;

import devmob2018.com.comandaapp.model.database.DataBaseConnectionFactory;

public class DataBaseTest {

    private static final String BANCO_NOME = "banco-produtos.db";


    @Test
    public void toRunTest() {
        System.out.println("FUNCIONOU");

//        System.out.println("createDataBase: " + BANCO_NOME + ": " + this.createDataBase());
//        System.out.println("createTable: produtos: " + this.createTable("produtos"));
    }

    private Boolean createDataBase() {
        String dbName = DataBaseConnectionFactory.getInstance(BANCO_NOME, 1, 0).getDatabaseName();
        if (dbName != null) {
            return true;
        }

        return false;
    }

    private Boolean createTable(String TABLE_NAME) {
        //Definir SQL para criação da tabela de produtos
        String SQL_CRIACAO_TABELA = "" +
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                "id integer not null primary key autoincrement," +
                "nome varchar(45) not null," +
                "valor float not null)";

        return false;
    }


}
