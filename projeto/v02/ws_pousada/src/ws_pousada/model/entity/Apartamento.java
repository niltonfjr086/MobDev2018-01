package ws_pousada.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(schema = "Pousada", name = "apartamento")
public class Apartamento extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5576261158682746917L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = true)
	private String nomenclatura;
	@Column(nullable = true)
	private Integer capacidade;
	@Column(nullable = true)
	private Integer categoria;
	@Column(nullable = true)
	private String observacoes;
	@Column(nullable = true)
	private Double valor_economico;
	@Column(nullable = true)
	private Double valor_basico;
	@Column(nullable = true)
	private Double valor_completo;
	@Column(nullable = true)
	private Double valor_premium;
	@Column(nullable = true)
	private Boolean isReservado;
	
	public Apartamento() {
		super();

	}

	public Apartamento(Long id, String nomenclatura, Integer capacidade, Integer categoria, String observacoes,
			Double valor_economico, Double valor_basico, Double valor_completo, Double valor_premium,
			Boolean isReservado) {
		super();
		this.id = id;
		this.nomenclatura = nomenclatura;
		this.capacidade = capacidade;
		this.categoria = categoria;
		this.observacoes = observacoes;
		this.valor_economico = valor_economico;
		this.valor_basico = valor_basico;
		this.valor_completo = valor_completo;
		this.valor_premium = valor_premium;
		this.isReservado = isReservado;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomenclatura() {
		return nomenclatura;
	}

	public void setNomenclatura(String nomenclatura) {
		this.nomenclatura = nomenclatura;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Double getValor_economico() {
		return valor_economico;
	}

	public void setValor_economico(Double valor_economico) {
		this.valor_economico = valor_economico;
	}

	public Double getValor_basico() {
		return valor_basico;
	}

	public void setValor_basico(Double valor_basico) {
		this.valor_basico = valor_basico;
	}

	public Double getValor_completo() {
		return valor_completo;
	}

	public void setValor_completo(Double valor_completo) {
		this.valor_completo = valor_completo;
	}

	public Double getValor_premium() {
		return valor_premium;
	}

	public void setValor_premium(Double valor_premium) {
		this.valor_premium = valor_premium;
	}

	public Boolean getIsReservado() {
		return isReservado;
	}

	public void setIsReservado(Boolean isReservado) {
		this.isReservado = isReservado;
	}
}
