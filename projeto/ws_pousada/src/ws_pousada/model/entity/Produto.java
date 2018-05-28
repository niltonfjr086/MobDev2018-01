package ws_pousada.model.entity;

import java.io.Serializable;

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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(schema = "Pousada", name = "tb_produto")
public class Produto extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 4615510397426763665L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String nome;

	@JsonBackReference
	@ManyToOne(targetEntity = Categoria.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categoria categoria;

	// @Column(nullable = false)
	// private Long categoria_id;

	// cascade=
	// { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
	// CascadeType.DETACH }
	//
	// @OneToMany(mappedBy = "produto", fetch = FetchType.EAGER, cascade = {
	// CascadeType.ALL })
	// private List<ItemProduto> itens = new ArrayList<>();

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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	// public Long getCategoria_id() {
	// return categoria_id;
	// }
	//
	// public void setCategoria_id(Long categoria_id) {
	// this.categoria_id = categoria_id;
	// }

	/*
	 * public List<ItemProduto> getItens() { return itens; }
	 * 
	 * public void setItens(List<ItemProduto> itens) { this.itens = itens; }
	 */

}
