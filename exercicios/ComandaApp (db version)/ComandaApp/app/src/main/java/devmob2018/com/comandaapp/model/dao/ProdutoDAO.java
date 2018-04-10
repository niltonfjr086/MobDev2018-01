//package com.senac.renato.exemplobancodedadosnativo;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//import java.util.ArrayList;
//
//import devmob2018.com.comandaapp.model.entity.Produto;
//
///**
// * Created by Renato on 31/08/2016.
// */
//public class ProdutoDAO {
//    private Produto produto;
//    private SQLiteDatabase banco;
//
//    //Definir o nome do banco de dados
//    private static final String BANCO_NOME = "banco-produtos.db";
//
//    //Definir o tipo de acesso ao banco
//    /*
//        0 => Modo Privado
//        1 => Outros aplicativos podem ler
//        2 => Outros aplicativos podem ler e escrever
//     */
//    private static final int BANCO_ACESSO = 0;
//
//    //Definir o nome da tabela
//    private static final String TABELA_NOME = "produtos";
//
//    //Definir SQL para criação da tabela de produtos
//    private static final String SQL_CRIACAO_TABELA = "" +
//            "CREATE TABLE IF NOT EXISTS "+TABELA_NOME+"(" +
//            "id integer not null primary key autoincrement," +
//            "nome varchar(45) not null," +
//            "valor float not null)";
//
//    //Definir SQL para selecionar todos os produtos
//    private static final String SQL_SELECT_ALL = "" +
//            "SELECT id, nome, valor FROM "+TABELA_NOME+" ORDER BY id ASC";
//
//    //Variável para armazenar respostas de select's
//    private Cursor cursor;
//
//    /**
//     * Classe responsável pela conexao com o banco de dados
//     * @author Renato
//     */
//    public ProdutoBD(Context context){
//        this.banco = context.openOrCreateDatabase(BANCO_NOME, BANCO_ACESSO, null);
//        this.banco.execSQL(SQL_CRIACAO_TABELA);
//    }
//
//    /**
//     * Método responsável pelo cadastro de um produto
//     * @param produto
//     * @return boolean
//     */
//    public boolean cadastrar(Produto produto ){
//
//        //Monta valores para parâmetro no insert
//        ContentValues values = new ContentValues();
//        values.put("nome", produto.getNome());
//        values.put("valor", produto.getValor());
//
//        //Realiza o cadastro no banco
//        long res = this.banco.insert(TABELA_NOME, null, values);
//
//        if(res==-1)
//            return false;
//        else
//            return true;
//
//    }
//
//
//    /**
//     * Método responsável por deletar um produto
//     * @param produto
//     * @return boolean TRUE|FALSE
//     */
//    public boolean deletar(Produto produto){
//
//        //array/vetor de strings com valores de parâmetros
//        String[] argumentos = new String[]{ String.valueOf(produto.getId()) };
//
//        //Deletar o produto do banco de dados
//        int res = this.banco.delete(TABELA_NOME, "id=?", argumentos);
//        if(res==-1)
//            return false;
//        else
//            return true;
//
//    }
//
//
//    /**
//     * Método para atualizar os campos de um produto
//     * @param produto
//     * @return boolean
//     */
//    public boolean atualizar(Produto produto){
//
//
//        //Monta valores para parâmetro no insert
//        ContentValues values = new ContentValues();
//        values.put("nome", produto.getNome());
//        values.put("valor", produto.getValor());
//
//        //Valores de parÂmetro para clausula where
//        String[] args = new String[]{ String.valueOf(produto.getId()) };
//
//        int res = this.banco.update(
//                                TABELA_NOME,
//                                values,
//                                "id=?",
//                                args
//                        );
//
//        if(res==-1)
//            return false;
//        else
//            return true;
//
//    }
//
//
//    /**
//     * Método para retornar todos os produtos
//     * @return ArrayList<Produto>
//     */
//    public ArrayList<Produto> listarTodos(){
//
//        ArrayList<Produto> listProdutos = new ArrayList<Produto>();
//
//        //Executa o sql select_all
//        this.cursor = this.banco.rawQuery(SQL_SELECT_ALL, null);
//
//        while(this.cursor.moveToNext()){
//
//            this.produto = new Produto();
//            this.produto.setId( this.cursor.getInt( this.cursor.getColumnIndex("id") ) );
//            this.produto.setNome( this.cursor.getString( this.cursor.getColumnIndex("nome") ) );
//            this.produto.setValor( this.cursor.getDouble( this.cursor.getColumnIndex("valor") ) );
//
//            listProdutos.add(this.produto);
//
//        }
//
//        return listProdutos;
//
//    }
//
//}
