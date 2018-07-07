package com.guto.pousadaapplication.model.entity;


import java.io.Serializable;

/**
 *
 * @author aluno
 */

// Os clientes são adicionados gerando registro na tabela Cliente_Estadia
// O apartamento selecionado é um atributo
// Os produtos são adicionados gerando registro na tabela Produto_Saida
// Os serviços são adicionados gerando registro na tabela Servico_Saida

public class Estadia implements Serializable {

    private Long id;
    //private Apartamento apartamento;
    private String observacoes;
    private Integer diarias_consumidas;
    private Integer diarias_reservadas;
    private Integer diarias_pagas;
    private Integer qtde_hospede;
    private Double total_diarias_consumidas;
    private Double total_produto;
    private Double total_servico;
    private Double consumo_pago;
    private Double total_absoluto;
    private String data_entrada;
    private String data_saida;
    private Boolean isAtivo;

    public Estadia() {
        super();
    }

    public Estadia(Long id, String observacoes, Integer diarias_consumidas, Integer diarias_reservadas, Integer diarias_pagas, Integer qtde_hospede, Double consumo_pago, Double total_diarias_consumidas, Double total_produto, Double total_servico, Double total_absoluto, String data_entrada, String data_saida, Boolean isAtivo) {
        this.id = id;
        this.observacoes = observacoes;
        this.diarias_consumidas = diarias_consumidas;
        this.diarias_reservadas = diarias_reservadas;
        this.diarias_pagas = diarias_pagas;
        this.qtde_hospede = qtde_hospede;
        this.consumo_pago = consumo_pago;
        this.total_diarias_consumidas = total_diarias_consumidas;
        this.total_produto = total_produto;
        this.total_servico = total_servico;
        this.total_absoluto = total_absoluto;
        this.data_entrada = data_entrada;
        this.data_saida = data_saida;
        this.isAtivo = isAtivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(String data_entrada) {
        this.data_entrada = data_entrada;
    }

    public String getData_saida() {
        return data_saida;
    }

    public void setData_saida(String data_saida) {
        this.data_saida = data_saida;
    }

    public Boolean getAtivo() {
        return isAtivo;
    }

    public void setAtivo(Boolean ativo) {
        isAtivo = ativo;
    }

    public Boolean getIsAtivo() {
        return isAtivo;
    }

    public void setIsAtivo(Boolean isAtivo) {
        this.isAtivo = isAtivo;
    }

    @Override
    public String toString() {
        return "Estadia{" +
                "id=" + id +
                ", observacoes='" + observacoes + '\'' +
                ", diarias_consumidas=" + diarias_consumidas +
                ", diarias_reservadas=" + diarias_reservadas +
                ", diarias_pagas=" + diarias_pagas +
                ", qtde_hospede=" + qtde_hospede +
                ", consumo_pago=" + consumo_pago +
                ", total_diarias_consumidas=" + total_diarias_consumidas +
                ", total_produto=" + total_produto +
                ", total_servico=" + total_servico +
                ", total_absoluto=" + total_absoluto +
                ", data_entrada='" + data_entrada + '\'' +
                ", data_saida='" + data_saida + '\'' +
                ", isAtivo=" + isAtivo +
                '}';
    }
}