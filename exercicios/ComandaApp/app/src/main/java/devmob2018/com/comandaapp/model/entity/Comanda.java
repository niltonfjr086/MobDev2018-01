package devmob2018.com.comandaapp.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Comanda implements Serializable {

    public static List<Produto> produtosDisponiveis;

    private List<ItemComanda> itens;

    public Comanda() {

        if (produtosDisponiveis == null) {

            produtosDisponiveis = new ArrayList<>();

            produtosDisponiveis.add(new Produto("Água Mineral", 2.50));
            produtosDisponiveis.add(new Produto("Burger Costela", 10.90));
            produtosDisponiveis.add(new Produto("Batata Frita", 9.50));
        }

        this.itens = new ArrayList<>();

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
