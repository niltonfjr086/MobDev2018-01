package ws_pousada.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(schema = "Pousada", name = "Produto_Entrada")
public class ProdutoEntrada extends BaseEntity {

	private static final long serialVersionUID = 2966988821893835861L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(targetEntity = Produto.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "idProduto", nullable = false)
	private Produto produto;

	@Column(nullable = true, name = "qtde")
	private Integer quantidade;

	@Column(nullable = true, name = "data_entrada")
	private Date dataEntrada;

	@Column(nullable = true, name = "valor_custoUnitario") // DECIMAL(9,2)
	private Double valorCustoUnitario;

	@Column(nullable = true, name = "valor_custoTotal") // DECIMAL(9,2)
	private Double valorCustoTotal;

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
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Double getValorCustoUnitario() {
		return valorCustoUnitario;
	}

	public void setValorCustoUnitario(Double valorCustoUnitario) {
		this.valorCustoUnitario = valorCustoUnitario;
	}

	public Double getValorCustoTotal() {
		return valorCustoTotal;
	}

	public void setValorCustoTotal(Double valorCustoTotal) {
		this.valorCustoTotal = valorCustoTotal;
	}

}
