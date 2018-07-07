/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws_pousada.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ws_pousada.model.ManualFactoryDAO;
import ws_pousada.model.entity.Apartamento;
import ws_pousada.model.entity.Estadia;

/**
 *
 * @author Augusto
 */
public class EstadiaDAO extends GenericDAO<Estadia,Long>{
	
	private Connection conexaoManual;

	// Conclusão : utilizar método especificado de filtro ou sugestão do nilton de método que leva um hashmap?
	// provavelmente hashmap é a melhor saída, realizar amanhã?
	
	public ArrayList<Estadia> listarPorFiltro(HashMap<String,String> map, Boolean isAtivo) {
		
        ArrayList<Estadia> lista = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder("SELECT a.id, a.capacidade, a.categoria, a.nomenclatura, a.observacoes, a.valor_economico, a.valor_basico, a.valor_completo, a.valor_premium, e.id, e.idApartamento, e.observacoes, e.diarias_consumidas, e.diarias_reservadas, e.diarias_pagas, e.qtde_hospede, e.total_diarias_consumidas, e.total_produto, e.total_servico, e.consumo_pago, e.total_absoluto, e.data_entrada, e.data_saida, e.isAtivo, e.isCheckedIn FROM Estadia e"
                + " INNER JOIN Cliente_Estadia ce ON e.id = ce.idEstadia"
                + " INNER JOIN Apartamento a ON e.idApartamento = a.id"
                + " INNER JOIN Cliente c ON ce.idCliente = c.id");

        
        
        
        if ( !map.isEmpty() ) {
        	
            sql = preencherFiltrosConsulta(sql, map, isAtivo);
            
        } else {
        	
        	sql.append(" GROUP BY e.id");
        	
        }

        try {
        	
            java.sql.Statement instrucaoSQL = getConexaoManual().createStatement();
            ResultSet rs = instrucaoSQL.executeQuery(sql.toString());
            
            while (rs.next()) {
            	
                Estadia e = new Estadia();
                Apartamento a = new Apartamento();
                e.setId(rs.getLong("e.id"));
                a.setId(rs.getLong("a.id"));
                a.setCapacidade(rs.getInt("a.capacidade"));
                a.setCategoria(rs.getInt("a.categoria"));
                a.setNomenclatura(rs.getString("a.nomenclatura"));
                a.setObservacoes(rs.getString("a.observacoes"));
                a.setValor_economico(rs.getDouble("a.valor_economico"));
                a.setValor_basico(rs.getDouble("a.valor_basico"));
                a.setValor_completo(rs.getDouble("a.valor_completo"));
                a.setValor_premium(rs.getDouble("a.valor_premium"));
                e.setApartamento(a);
                e.setObservacoes(rs.getString("e.observacoes"));
                e.setDiarias_consumidas(rs.getInt("e.diarias_consumidas"));
                e.setDiarias_reservadas(rs.getInt("e.diarias_reservadas"));
                e.setDiarias_pagas(rs.getInt("e.diarias_pagas"));
                e.setQtde_hospede(rs.getInt("e.qtde_hospede"));
                e.setTotal_diarias_consumidas(rs.getDouble("e.total_diarias_consumidas"));
                e.setTotal_produto(rs.getDouble("e.total_produto"));
                e.setTotal_servico(rs.getDouble("e.total_servico"));
                e.setConsumo_pago(rs.getDouble("e.consumo_pago"));
                e.setTotal_absoluto(rs.getDouble("e.total_absoluto"));
                
                try {
                	
                    e.setData_entrada(rs.getTimestamp("e.data_entrada").toLocalDateTime());
                    
                } catch (Exception erro) {
                    
                	// do nothing
                	
                }
                
                try {
                	
                    e.setData_saida(rs.getTimestamp("e.data_saida").toLocalDateTime());
                    
                } catch (Exception erro) { 
                    
                	// do nothing
                	
                }
                
                lista.add(e);
                
            }
            
        } catch ( SQLException e ) {
        	
            e.printStackTrace();
            
        }
        
        return lista;
        
    }

    private StringBuilder preencherFiltrosConsulta(StringBuilder sql, HashMap<String, String> map, Boolean isAtivo) {
    	
        sql.append(" WHERE isAtivo = " + isAtivo);
        
        for ( Map.Entry<String, String> entry : map.entrySet() ) {
			if ( entry.getKey() != null && entry.getValue() != null ) {
				if ( !entry.getKey().trim().isEmpty() && !entry.getValue().trim().isEmpty() ) {
					
					sql.append(" AND ");
					
					if ( entry.getKey().contains("data") || entry.getKey().contains("VAL") ) {
						if ( entry.getKey().contains("data") ) {
							if( entry.getKey().contains("INIT")) {
								
								sql.append(entry.getKey().replace("INIT", "") + " >= " + "'" + getSqlDate(entry.getValue()) + "'");
								continue;
								
							} else {
								
								sql.append(entry.getKey().replace("FINAL", "") + " <= " + "'" + getSqlDate(entry.getValue()) + "'");
								continue;
								
							}
						}
						
						if ( entry.getKey().contains("VAL") ) {
							if  ( entry.getKey().contains("INIT")) {
								
								sql.append(entry.getKey().replace("VAL", "").replace("INIT", "") + " >= " + "'" + entry.getValue() + "'");
								continue;
								
							} else {
								
								sql.append(entry.getKey().replace("VAL", "").replace("FINAL", "") + " <= " + "'" + entry.getValue() + "'");
								continue;
								
							}
						}
						
					} else {
						
						// TODO como diferenciar igual de LIKE?
						sql.append(entry.getKey() + " LIKE '%" + entry.getValue() + "%'");
						continue;
						
					}
				}
			}
		}
        
        sql.append(" GROUP BY e.id");
        System.out.println("------------------------------------------------------------------------------------------------\n"
        			+ sql + 
        			 	   "------------------------------------------------------------------------------------------------");
        
        return sql;
        
    }
    
    
    
    private String getSqlDate(String date) {
    	try{
    		
            String [] a = date.split("/");
            String dataString = a[2]+"-"+a[1]+"-"+a[0];
            return dataString;
            
        } catch ( Exception e ){
        	
            return "";
            
        }
	}

	public Connection getConexaoManual() {
    	
        conexaoManual = ManualFactoryDAO.getInstance().obterConexao();
        
        return conexaoManual;
        
    }
    
}
