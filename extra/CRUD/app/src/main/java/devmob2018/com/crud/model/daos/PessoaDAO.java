package devmob2018.com.crud.model.daos;

import java.util.List;

import devmob2018.com.crud.model.data_base.Delete;
import devmob2018.com.crud.model.data_base.Read;
import devmob2018.com.crud.model.data_base.Update;
import devmob2018.com.crud.model.entities.Pessoa;

public class PessoaDAO {

    public Pessoa garregar(Integer posicao) {

        List<Pessoa> pessoas = new Read().getPessoas();

        if (posicao >= pessoas.size()) return null;


        return pessoas.get(posicao);
    }

    public String adicionarPessoa(Pessoa p) {

        return "Class PessoDAO adicionarPessoa: " + new Update().addPessoa(p);
    }

    public String editarPessoa() {

        return "Class PessoDAO editarPessoa";
    }

    public String removerPessoa() {

        return "Class PessoDAO removerPessoa";
    }

    public String removerTabela() {

        new Delete().removerTabela();

        return "Class PessoDAO removerTabela";
    }

    public List<Pessoa> verRegistros() {

        List<Pessoa> pessoas = new Read().getPessoas();

        if (pessoas.size() <= 0) {
            System.out.println("NENHUM REGISTRO");

        } else {

            for (Pessoa p : pessoas) {
                System.out.println(p.toString());
            }

        }

//        return "Class PessoDAO verRegistros";

        return pessoas;
    }


}
