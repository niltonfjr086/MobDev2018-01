package devmob2018.com.comandaapp.model.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "tb_item_comanda")
public class ItemComanda implements Serializable {

    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Long id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "produto_id", canBeNull = false)
    private Produto produto;

    @DatabaseField(columnName = "quantidade", canBeNull = false)
    private Integer quantidade;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, columnName = "comanda_id", canBeNull = false)
    private Comanda comanda;


    @DatabaseField(columnName = "subtotal", canBeNull = false)
    private Double subtotal;

    public ItemComanda() {
        this.produto = new Produto();
    }

    public ItemComanda(Produto produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ItemComanda(Produto produto, Integer quantidade, Comanda comanda) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.comanda = comanda;
        this.subtotal = this.getSubtotal();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        if (this.produto != null && this.produto.getValor() != null && this.quantidade != null) {
            this.subtotal = this.getSubtotal();
        }
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getSubtotal() {

        if (this.subtotal == null && this.produto != null && this.produto.getValor() != null && this.quantidade != null) {

            this.subtotal = this.produto.getValor() * this.quantidade;

        } else {

            if (this.subtotal == null) {
//                this.subtotal = 0.00;
                return 0.00;
            }

        }

        return this.subtotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemComanda that = (ItemComanda) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (produto != null ? !produto.equals(that.produto) : that.produto != null) return false;
        if (quantidade != null ? !quantidade.equals(that.quantidade) : that.quantidade != null)
            return false;
        if (comanda != null ? !comanda.equals(that.comanda) : that.comanda != null)
            return false;
        return subtotal != null ? subtotal.equals(that.subtotal) : that.subtotal == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (produto != null ? produto.hashCode() : 0);
        result = 31 * result + (quantidade != null ? quantidade.hashCode() : 0);
        result = 31 * result + (comanda.getId() != null ? comanda.getId().hashCode() : 0);
        result = 31 * result + (subtotal != null ? subtotal.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ItemComanda{" +
                "id=" + id +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", comanda=" + comanda +
                ", subtotal=" + subtotal +
                '}';
    }
}
