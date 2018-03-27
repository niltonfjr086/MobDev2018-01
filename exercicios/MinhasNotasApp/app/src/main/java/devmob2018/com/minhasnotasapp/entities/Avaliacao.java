package devmob2018.com.minhasnotasapp.entities;

import java.io.Serializable;

public class Avaliacao implements Serializable {

    private String titulo;
    private Float nota;

    public Avaliacao() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Avaliacao avaliacao = (Avaliacao) o;

        if (titulo != null ? !titulo.equals(avaliacao.titulo) : avaliacao.titulo != null)
            return false;
        return nota != null ? nota.equals(avaliacao.nota) : avaliacao.nota == null;
    }

    @Override
    public int hashCode() {
        int result = titulo != null ? titulo.hashCode() : 0;
        result = 31 * result + (nota != null ? nota.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "titulo='" + titulo + '\'' +
                ", nota=" + nota +
                '}';
    }
}
