package com.guto.pousadaapplication.model.filtro;

import java.io.Serializable;
import java.lang.reflect.Field;

public class FiltroHistoricoEstadia extends Filtro implements Serializable {

    public String[] getFieldTitles (){
        String[] fieldTitles = {"CPF","Apartamento","Categoria de Apartamento", "Total de diárias - Valor inicial", "Total de diárias - Valor final", "Data inicial", "Data final"};

        return fieldTitles;
    }

    // por faixa de valor: valor[tabela].[campo]inicial e valor[tabela].[campo]final
    // por faixa de data: [tabela].[campo]inicial e [tabela].[campo]final
    public String[] getFieldNames () {
        String [] fieldNames = {"c.cpf", "a.nomenclatura", "a.categoria","VALe.total_diarias_consumidasINIT","VALe.total_diarias_consumidasFINAL", "e.data_entradaINIT", "e.data_entradaFINAL"};

        return fieldNames;
    }
}
