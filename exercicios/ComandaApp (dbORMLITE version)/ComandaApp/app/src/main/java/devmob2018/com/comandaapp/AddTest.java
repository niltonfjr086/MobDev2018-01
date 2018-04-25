package devmob2018.com.comandaapp;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import devmob2018.com.comandaapp.model.database.MyOrmLiteOpenHelper;
import devmob2018.com.comandaapp.model.entity.Comanda;
import devmob2018.com.comandaapp.model.entity.ItemComanda;
import devmob2018.com.comandaapp.model.entity.Produto;

public class AddTest {

    public void adicionarComanda(MyOrmLiteOpenHelper db) {
        Dao<Produto, Long> proDAO;
        try {
            proDAO = db.getDao(Produto.class);

            List<Produto> prts = proDAO.queryForAll();
            Comanda c = new Comanda(prts);
            c.getItens().add(new ItemComanda(Comanda.produtosDisponiveis.get(0), 10/*, c*/));
            c.getItens().add(new ItemComanda(Comanda.produtosDisponiveis.get(2), 10/*, c*/));
            c.getItens().add(new ItemComanda(Comanda.produtosDisponiveis.get(1), 10/*, c*/));

            ((Dao<Comanda, Long>) db.getDao(c.getClass())).create(c);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
