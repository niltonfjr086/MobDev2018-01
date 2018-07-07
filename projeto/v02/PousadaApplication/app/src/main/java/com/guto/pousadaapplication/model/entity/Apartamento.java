package com.guto.pousadaapplication.model.entity;

public class Apartamento {

    private Long id;
    private String nomenclatura;
    private Integer capacidade;
    private Integer categoria;
    private String observacoes;
    private Double valor_diaria;
    private Boolean isReservado;

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

    public Double getValor_diaria() {
        return valor_diaria;
    }

    public void setValor_diaria(Double valor_diaria) {
        this.valor_diaria = valor_diaria;
    }

    public Boolean getIsReservado() {
        return isReservado;
    }

    public void setIsReservado(Boolean isReservado) {
        this.isReservado = isReservado;
    }

    @Override
    public String toString() {
        return "Apartamento{" + "id=" + id + ", nomenclatura=" + nomenclatura + ", capacidade=" + capacidade + ", categoria=" + categoria + ", observacoes=" + observacoes + ", valor_diaria=" + valor_diaria + '}';
    }
}
