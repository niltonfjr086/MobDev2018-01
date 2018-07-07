/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws_pousada.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Augusto
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(schema = "Pousada", name = "Estadia")
public class Estadia extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=true)
    @JoinColumn(name = "idApartamento", nullable = true)
    private Apartamento apartamento;
    @Column(nullable = true, length = 10000)
    private String observacoes;
    @Column(nullable = true)
    private Integer diarias_consumidas;
    @Column(nullable = true)
    private Integer diarias_reservadas;
    @Column(nullable = true)
    private Integer diarias_pagas;
    @Column(nullable = true)
    private Integer qtde_hospede;
    @Column(nullable = true)
    private Double consumo_pago;
    @Column(nullable = true)
    private Double total_diarias_consumidas;
    @Column(nullable = true)
    private Double total_produto;
    @Column(nullable = true)
    private Double total_servico;
    @Column(nullable = true)
    private Double total_absoluto;
    @Column(nullable = true)
    private LocalDateTime data_entrada;
    @Column(nullable = true)
    private LocalDateTime data_saida;
    @Column(nullable = true)
    private Boolean isAtivo;
    
//    @JsonBackReference(value = "estadia")
//    @OneToMany(mappedBy = "estadia", fetch = FetchType.LAZY)
//    private ArrayList<Cliente> lista;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Apartamento getApartamento() {
        return apartamento;
    }

    public void setApartamento(Apartamento apartamento) {
        this.apartamento = apartamento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getDiarias_consumidas() {
        return diarias_consumidas;
    }

    public void setDiarias_consumidas(Integer diarias_consumidas) {
        this.diarias_consumidas = diarias_consumidas;
    }

    public Integer getDiarias_reservadas() {
        return diarias_reservadas;
    }

    public void setDiarias_reservadas(Integer diarias_reservadas) {
        this.diarias_reservadas = diarias_reservadas;
    }

    public Integer getDiarias_pagas() {
        return diarias_pagas;
    }

    public void setDiarias_pagas(Integer diarias_pagas) {
        this.diarias_pagas = diarias_pagas;
    }

    public Integer getQtde_hospede() {
        return qtde_hospede;
    }

    public void setQtde_hospede(Integer qtde_hospede) {
        this.qtde_hospede = qtde_hospede;
    }

    public Double getConsumo_pago() {
        return consumo_pago;
    }

    public void setConsumo_pago(Double consumo_pago) {
        this.consumo_pago = consumo_pago;
    }

    public Double getTotal_diarias_consumidas() {
        return total_diarias_consumidas;
    }

    public void setTotal_diarias_consumidas(Double total_diarias_consumidas) {
        this.total_diarias_consumidas = total_diarias_consumidas;
    }

    public Double getTotal_produto() {
        return total_produto;
    }

    public void setTotal_produto(Double total_produto) {
        this.total_produto = total_produto;
    }

    public Double getTotal_servico() {
        return total_servico;
    }

    public void setTotal_servico(Double total_servico) {
        this.total_servico = total_servico;
    }

    public Double getTotal_absoluto() {
        return total_absoluto;
    }

    public void setTotal_absoluto(Double total_absoluto) {
        this.total_absoluto = total_absoluto;
    }
    
    public LocalDateTime getData_entrada() {
		return data_entrada;
	}

	public void setData_entrada(LocalDateTime data_entrada) {
		this.data_entrada = data_entrada;
	}

	public LocalDateTime getData_saida() {
		return data_saida;
	}

	public void setData_saida(LocalDateTime data_saida) {
		this.data_saida = data_saida;
	}

	public Boolean getIsAtivo() {
        return isAtivo;
    }

    public void setIsAtivo(Boolean isAtivo) {
        this.isAtivo = isAtivo;
    }

//    public ArrayList<Cliente> getLista() {
//        return lista;
//    }
//
//    public void setLista(ArrayList<Cliente> lista) {
//        this.lista = lista;
//    }
}
