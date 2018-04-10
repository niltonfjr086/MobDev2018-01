package devmob2018.com.comandaapp.model.entity;

import java.io.Serializable;

public class Produto implements Serializable {

    private Long id;
    private String nome;
    private Double valor;

    public Produto() {
    }

    public Produto(String nome, Double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produto produto = (Produto) o;

        if (nome != null ? !nome.equals(produto.nome) : produto.nome != null) return false;
        return valor != null ? valor.equals(produto.valor) : produto.valor == null;
    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (valor != null ? valor.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return nome + " | " + valor;
    }
}
