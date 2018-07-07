package ws_pousada.model.filtro;

import java.lang.reflect.Field;

import ws_pousada.util.Validador;

public class FiltroHistoricoEstadia {

    private String cpf;
    private String ap_nomenclatura;
    private String ap_categoria;
    private String dataDe;
    private String dataAte;

    public boolean temCampoPreenchido() {
        boolean temCampoPreenchido = false;
        Field[] campos = this.getClass().getDeclaredFields();

        for (int i = 0; i < campos.length; i++) {
            campos[i].setAccessible(true);
            try {
                String valor = (String) campos[i].get(this);
                if (valor != null) {
                    if (!valor.trim().isEmpty()) {
                        temCampoPreenchido = true;
                        return temCampoPreenchido;
                    }
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return temCampoPreenchido;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if(cpf!=null){
            if(!cpf.replace(".", "").replace("-", "").trim().isEmpty()){
                this.cpf = cpf;
            }else{
                this.cpf = null;
            }
        }else{
            this.cpf = null;
        }
    }

    public String getAp_nomenclatura() {
        return ap_nomenclatura;
    }

    public void setAp_nomenclatura(String ap_nomenclatura) {
        this.ap_nomenclatura = ap_nomenclatura;
    }

    public String getAp_categoria() {
        return ap_categoria;
    }

    public void setAp_categoria(String ap_categoria) {
        this.ap_categoria = ap_categoria;
    }

    public String getDataDe() {
        return dataDe;
    }

    public void setDataDe(String dataDe) {
        String[] result = dataDe.split("/");
        if (!result[0].trim().isEmpty()) {
            if (!result[1].trim().isEmpty()) {
                if (!result[2].trim().isEmpty()) {
                    this.dataDe = new Validador().stringToLocalDate(dataDe).toString();
                }
            }
        } else {
            this.dataDe = null;
        }
    }

    public String getDataAte() {
        return dataAte;
    }

    public void setDataAte(String dataAte) {
        String[] result = dataAte.split("/");
        if (!result[0].trim().isEmpty()) {
            if (!result[1].trim().isEmpty()) {
                if (!result[2].trim().isEmpty()) {
                    this.dataAte = new Validador().stringToLocalDate(dataAte).toString();
                }
            }
        } else {
            this.dataAte = null;
        }
    }

}
