package ws_pousada.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(schema = "Pousada", name = "tb_categoria")
public class Categoria extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -7323718573505068973L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true, length = 30)
	private String nome;

//	@JsonBackReference(value = "categoria")
	@JsonIgnore
	@OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
	private List<OLDProduto> produtos;

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

	public List<OLDProduto> getProdutos() {

		return this.produtos;
	}

	public void setProdutos(List<OLDProduto> produtos) {

		this.produtos = produtos;
	}

}