package devmob2018.com.comandaapp.model.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "tb_produto")
public class Produto implements Serializable {

    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Long id;

    @DatabaseField(canBeNull = false, width = 20)
    private String nome;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "categoria_id", canBeNull = false)
    private Categoria categoria;

    @DatabaseField(canBeNull = false)
    private Double valor;

    public Produto() {
    }

    public Produto(String nome, Categoria categoria, Double valor) {
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

        if (id != null ? !id.equals(produto.id) : produto.id != null) return false;
        if (nome != null ? !nome.equals(produto.nome) : produto.nome != null) return false;
        if (categoria != null ? !categoria.equals(produto.categoria) : produto.categoria != null)
            return false;
        return valor != null ? valor.equals(produto.valor) : produto.valor == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        result = 31 * result + (valor != null ? valor.hashCode() : 0);
        return result;
    }

/*    @Override
    public String toString() {
        return nome + " | " + categoria.getNome() + " | " + valor;
    }*/

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", categoria=" + categoria +
                ", valor=" + valor +
                '}';
    }
}
