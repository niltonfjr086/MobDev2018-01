package devmob2018.com.comandaapp.model.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@DatabaseTable(tableName = "tb_categoria")
public class Categoria implements Serializable {

    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Long id;

    @DatabaseField(columnName = "nome", canBeNull = false)
    private String nome;

    @ForeignCollectionField(eager = true)
    private Collection<Produto> produtos = new ArrayList<>();

    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Collection<Produto> getProdutos() {

        return this.produtos;
    }

    public void setProdutos(Collection<Produto> produtos) {

        this.produtos = produtos;
    }

    public List<Produto> getListProdutos() {

        List<Produto> r = new ArrayList<>();

        for (Produto p : this.produtos) {
            r.add(p);
        }

        return r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categoria categoria = (Categoria) o;

        if (id != null ? !id.equals(categoria.id) : categoria.id != null) return false;
        return nome != null ? nome.equals(categoria.nome) : categoria.nome == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return nome + "(" + getListProdutos().size() + ")";
    }
}
