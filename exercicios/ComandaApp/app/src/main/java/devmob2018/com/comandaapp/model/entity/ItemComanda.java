package devmob2018.com.comandaapp.model.entity;

import java.io.Serializable;

public class ItemComanda implements Serializable {

    private Produto produto;
    private Integer quantidade;
    private Double subtotal;

    public ItemComanda() {
    }

    public ItemComanda(Produto produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getSubtotal() {

        if (this.produto != null && this.produto.getValor() != null && this.quantidade != null) {
            return this.produto.getValor() * this.quantidade;

        } else {
            return 0.00;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemComanda that = (ItemComanda) o;

        if (produto != null ? !produto.equals(that.produto) : that.produto != null) return false;
        return quantidade != null ? quantidade.equals(that.quantidade) : that.quantidade == null;
    }

    @Override
    public int hashCode() {
        int result = produto != null ? produto.hashCode() : 0;
        result = 31 * result + (quantidade != null ? quantidade.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return produto.getNome() + " - " + produto.getValor() + " - " + quantidade.toString() + " - " + this.getSubtotal();
    }
}
