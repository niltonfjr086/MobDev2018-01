package devmob2018.com.minhasnotasapp.entities;

import java.io.Serializable;

public class Disciplina implements Serializable {

    private String nomeDisciplina;
    private String nomeProfessor;
    private String nomeAluno;

    private Avaliacao avaliacao1;
    private Avaliacao avaliacao2;

    public Disciplina() {
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public Avaliacao getAvaliacao1() {
        return avaliacao1;
    }

    public void setAvaliacao1(Avaliacao avaliacao1) {
        this.avaliacao1 = avaliacao1;
    }

    public Avaliacao getAvaliacao2() {
        return avaliacao2;
    }

    public void setAvaliacao2(Avaliacao avaliacao2) {
        this.avaliacao2 = avaliacao2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Disciplina that = (Disciplina) o;

        if (nomeDisciplina != null ? !nomeDisciplina.equals(that.nomeDisciplina) : that.nomeDisciplina != null)
            return false;
        if (nomeAluno != null ? !nomeAluno.equals(that.nomeAluno) : that.nomeAluno != null)
            return false;
        if (nomeProfessor != null ? !nomeProfessor.equals(that.nomeProfessor) : that.nomeProfessor != null)
            return false;
        if (avaliacao1 != null ? !avaliacao1.equals(that.avaliacao1) : that.avaliacao1 != null)
            return false;
        return avaliacao2 != null ? avaliacao2.equals(that.avaliacao2) : that.avaliacao2 == null;
    }

    @Override
    public int hashCode() {
        int result = nomeDisciplina != null ? nomeDisciplina.hashCode() : 0;
        result = 31 * result + (nomeAluno != null ? nomeAluno.hashCode() : 0);
        result = 31 * result + (nomeProfessor != null ? nomeProfessor.hashCode() : 0);
        result = 31 * result + (avaliacao1 != null ? avaliacao1.hashCode() : 0);
        result = 31 * result + (avaliacao2 != null ? avaliacao2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "nomeDisciplina='" + nomeDisciplina + '\'' +
                ", nomeAluno='" + nomeAluno + '\'' +
                ", nomeProfessor='" + nomeProfessor + '\'' +
                ", avaliacao1=" + avaliacao1 +
                ", avaliacao2=" + avaliacao2 +
                '}';
    }
}
