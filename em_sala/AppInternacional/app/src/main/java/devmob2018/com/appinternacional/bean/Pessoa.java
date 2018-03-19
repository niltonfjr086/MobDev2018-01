package devmob2018.com.appinternacional.bean;

import java.io.Serializable;

/**
 * Created by main on 28/02/18.
 */

public class Pessoa implements Serializable {

    private String nome;
    private Integer idade;

    public Pessoa() {
    }

    public Pessoa(String nome, Integer idade) {
        this.nome = nome;
        this.idade = idade;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pessoa pessoa = (Pessoa) o;

        if (nome != null ? !nome.equals(pessoa.nome) : pessoa.nome != null) return false;
        return idade != null ? idade.equals(pessoa.idade) : pessoa.idade == null;
    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (idade != null ? idade.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
}
