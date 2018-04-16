package devmob2018.com.comandaapp.model.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DatabaseTable(tableName = "tb_comanda")
public class Comanda implements Serializable {

    //PREENCHIDA PELO BANCO COM PARAMETRO
    public static List<Produto> produtosDisponiveis;

    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;

    private List<ItemComanda> itens;

    @DatabaseField(columnName = "dtAbertura", canBeNull = false)
    private Date dtAbertura;

    @DatabaseField(columnName = "dtFechamento")
    private Date dtFechamento;


    public Comanda() {

        this.dtAbertura = new Date();

        if (produtosDisponiveis == null) {

            produtosDisponiveis = new ArrayList<>();

            produtosDisponiveis.add(new Produto("Coca-cola", 3.50));
            produtosDisponiveis.add(new Produto("Sorvete", 10.90));
        }

        this.itens = new ArrayList<>();

    }

    public Comanda(List<Produto> produtos) {

        this.dtAbertura = new Date();

        if (produtos != null && produtos.size() > 0) {

            produtosDisponiveis = produtos;
        }

        this.itens = new ArrayList<>();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ItemComanda> getItens() {
        return itens;
    }

    public void setItens(List<ItemComanda> itens) {
        this.itens = itens;
    }

    public Date getDtAbertura() {
        return dtAbertura;
    }

    public void setDtAbertura(Date dtAbertura) {
        this.dtAbertura = dtAbertura;
    }

    public Date getDtFechamento() {
        return dtFechamento;
    }

    public void setDtFechamento(Date dtFechamento) {
        this.dtFechamento = dtFechamento;
    }

    public void adicionarItem(ItemComanda ic) {
        this.itens.add(ic);
    }

    public void removerItem(ItemComanda ic) {
        this.itens.remove(ic);
    }

    public Double getTotal() {

        Double somaTotal = 0.00;

        if (this.itens != null && this.itens.size() > 0) {

            for (ItemComanda ic : this.itens) {

                somaTotal += ic.getSubtotal();
            }
        }

        return somaTotal;
    }

}
