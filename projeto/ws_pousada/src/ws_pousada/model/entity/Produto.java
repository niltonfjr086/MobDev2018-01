package ws_pousada.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "Pousada", name = "tb_produto")
public class Produto extends BaseEntity {

	private static final long serialVersionUID = -3867629168993196940L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String nome;

	@ManyToOne(targetEntity = Produto.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categoria categoria;

	// cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
	// CascadeType.DETACH }
	/*
	 * @OneToMany(mappedBy = "produto", fetch = FetchType.EAGER, cascade = {
	 * CascadeType.ALL }) private List<ItemProduto> itens = new ArrayList<>();
	 */

	public Produto() {
		super();
	}

	public Produto(String nome) {
		super();
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

	/*
	 * public List<ItemProduto> getItens() { return itens; }
	 * 
	 * public void setItens(List<ItemProduto> itens) { this.itens = itens; }
	 */

}
