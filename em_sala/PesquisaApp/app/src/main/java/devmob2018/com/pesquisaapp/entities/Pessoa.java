package devmob2018.com.pesquisaapp.entities;

/**
 * Created by main on 22/03/18.
 */

public class Pessoa {

    private String nome;
    private Integer idade;

    private Contato contato;
    private Endereco endereco;



    public Pessoa() {
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pessoa pessoa = (Pessoa) o;

        if (nome != null ? !nome.equals(pessoa.nome) : pessoa.nome != null) return false;
        if (idade != null ? !idade.equals(pessoa.idade) : pessoa.idade != null) return false;
        if (contato != null ? !contato.equals(pessoa.contato) : pessoa.contato != null)
            return false;
        return endereco != null ? endereco.equals(pessoa.endereco) : pessoa.endereco == null;
    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (idade != null ? idade.hashCode() : 0);
        result = 31 * result + (contato != null ? contato.hashCode() : 0);
        result = 31 * result + (endereco != null ? endereco.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", contato=" + contato +
                ", endereco=" + endereco +
                '}';
    }
}
