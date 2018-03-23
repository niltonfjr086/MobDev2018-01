package devmob2018.com.pesquisaapp.entities;

import java.io.Serializable;

public class Endereco implements Serializable {

    private String cep;
    private String cidade;
    private String estado;

    public Endereco() {
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Endereco endereco = (Endereco) o;

        if (cep != null ? !cep.equals(endereco.cep) : endereco.cep != null) return false;
        if (cidade != null ? !cidade.equals(endereco.cidade) : endereco.cidade != null)
            return false;
        return estado != null ? estado.equals(endereco.estado) : endereco.estado == null;
    }

    @Override
    public int hashCode() {
        int result = cep != null ? cep.hashCode() : 0;
        result = 31 * result + (cidade != null ? cidade.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "cep='" + cep + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
