package devmob2018.com.disciplinasapp.entities;

import java.io.Serializable;

public class Disciplina implements Serializable {

    private String nome;

    private Double nota1 = 0.00;
    private Double nota2 = 0.00;
    private Double nota3 = 0.00;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getNota1() {
        return nota1;
    }

    public void setNota1(Double nota1) { this.nota1 = nota1; }

    public Double getNota2() {
        return nota2;
    }

    public void setNota2(Double nota2) { this.nota2 = nota2; }

    public Double getNota3() {
        return nota3;
    }

    public void setNota3(Double nota3) {
        this.nota3 = nota3;
    }


    public Double getSoma() {

        return (this.nota1 + this.nota2 + this.nota3);
    }

    public Double getMedia() {

        if (this.getSoma() <= 0) {

            return 0.00;
        } else {

            return this.getSoma() / 3;
        }
    }

    public String getSituacao() {

        if (this.getMedia() < 7.00) {
            return "Reprovado";
        } else {
            return "Aprovado";
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Disciplina that = (Disciplina) o;

        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (nota1 != null ? !nota1.equals(that.nota1) : that.nota1 != null) return false;
        if (nota2 != null ? !nota2.equals(that.nota2) : that.nota2 != null) return false;
        return nota3 != null ? nota3.equals(that.nota3) : that.nota3 == null;
    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (nota1 != null ? nota1.hashCode() : 0);
        result = 31 * result + (nota2 != null ? nota2.hashCode() : 0);
        result = 31 * result + (nota3 != null ? nota3.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "nome='" + nome + '\'' +
                ", nota1=" + nota1 +
                ", nota2=" + nota2 +
                ", nota3=" + nota3 +
                '}';
    }
}
