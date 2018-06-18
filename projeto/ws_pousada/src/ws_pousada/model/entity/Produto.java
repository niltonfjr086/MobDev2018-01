package ws_pousada.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(schema = "Pousada", name = "Produto")
public class Produto extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 3452137000848962234L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true, length = 150)
	private String descricao;

	@Column(nullable = false, length = 150, name = "tipo_produto")
	private String tipoProduto;

	@Column(nullable = true, length = 20, name = "cod_barras")
	private String codBarras;

	@Column(nullable = true, name = "valor_unitario") // DECIMAL(9,2)
	private Double valorUnitario;

	@Column(nullable = true, name = "estoque_minimo")
	private Integer estoqueMinimo;

	@Column(nullable = true, name = "estoque_maximo")
	private Integer estoqueMaximo;

	@JsonIgnore
	@OneToMany(mappedBy = "produto", fetch = FetchType.EAGER)
	List<ProdutoEntrada> produtosEntrada;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Integer getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(Integer estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	public Integer getEstoqueMaximo() {
		return estoqueMaximo;
	}

	public void setEstoqueMaximo(Integer estoqueMaximo) {
		this.estoqueMaximo = estoqueMaximo;
	}

	public List<ProdutoEntrada> getProdutosEntrada() {
		return produtosEntrada;
	}

	public void setProdutosEntrada(List<ProdutoEntrada> produtosEntrada) {
		this.produtosEntrada = produtosEntrada;
	}

}
