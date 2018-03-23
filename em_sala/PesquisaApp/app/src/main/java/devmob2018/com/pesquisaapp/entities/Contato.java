package devmob2018.com.pesquisaapp.entities;

import java.io.Serializable;

public class Contato implements Serializable {

    private String telefone;
    private String email;

    public Contato() {
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contato contato = (Contato) o;

        if (telefone != null ? !telefone.equals(contato.telefone) : contato.telefone != null)
            return false;
        return email != null ? email.equals(contato.email) : contato.email == null;
    }

    @Override
    public int hashCode() {
        int result = telefone != null ? telefone.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
