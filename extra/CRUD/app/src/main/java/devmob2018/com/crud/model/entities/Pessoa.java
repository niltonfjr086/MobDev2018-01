package devmob2018.com.crud.model.entities;

import java.util.Random;

public class Pessoa {
    public static String criarUID() {
        return "@Pessoa:" + System.currentTimeMillis() + new Random().nextDouble();
    }

    private final String UID;
    //    private Long id;
    private String nome;
    private Integer idade;
    private Double peso;
    private Boolean deficiencia;


//    public Pessoa() {
//    }

    public Pessoa(String UID) {
        this.UID = UID;
    }

    public String getUID() {
        return UID;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Boolean getDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(Boolean deficiencia) {
        this.deficiencia = deficiencia;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "UID='" + UID + '\'' +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", peso=" + peso +
                ", deficiencia=" + deficiencia +
                '}';
    }
}
