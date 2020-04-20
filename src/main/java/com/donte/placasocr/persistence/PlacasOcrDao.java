package com.donte.placasocr.persistence;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.donte.placasocr.model.PlatesOcr;

public class PlacasOcrDao {

	private static DataSourceCPDS instance = DataSourceCPDS.getInstance();

	public void processarEntradas(){              	
		try(Connection conn = instance.getConnection()){			    		
			try (PreparedStatement stmt = conn.prepareStatement("{ CALL dbo.CAPTURAR_PLACAS_OCR() }")) {	    	
				stmt.execute();	       
			}			    
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ocorreu uma excecao", JOptionPane.ERROR_MESSAGE);
		}
	}

	public List<PlatesOcr> findByEnviado(boolean flag) {
		List<PlatesOcr> lista = new ArrayList<>();
		try(Connection conn = instance.getConnection()){
			String sql = "SELECT * FROM dbo.PLATESOCR p WHERE p.sent = ?";
			try (PreparedStatement ps = conn.prepareStatement(sql)) {	    	
				ps.setBoolean(1,flag);
				ResultSet rs = ps.executeQuery();	           
				while (rs.next()) {	                 
					lista.add(new PlatesOcr(rs));
				}
				rs.close();	           
			}			    
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ocorreu uma excecao", JOptionPane.ERROR_MESSAGE);
		}
		return lista;
	}

	public void atualizarEnviado(Long id, boolean flag) {
		try(Connection conn = instance.getConnection()){
			String sql = "UPDATE PLATESOCR SET SENT = ?, IMAGE = NULL WHERE ID = ?";      
			try (PreparedStatement ps = conn.prepareStatement(sql)) {	    	
				ps.setBoolean(1, flag);
				ps.setLong(2, id);
				ps.execute();	           	           	          
			}			    
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ocorreu uma excecao", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void findImagemById(long codigo)  {
		try(Connection conn = instance.getConnection()){
			String sql = "SELECT CAST(IMAGE AS VARBINARY(MAX)) FROM EMPLAC.dbo.EVENTS WHERE EVENTID = ?";
		    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
		    	stmt.setLong(1,codigo); 		    			        
		        try (ResultSet rs = stmt.executeQuery()) {
		            if (rs.next()) {
		            	byte[] fileBytes = rs.getBytes(1);
						OutputStream targetFile = new FileOutputStream("c:/tmp/"+codigo+".jpg");
						targetFile.write(fileBytes);
						targetFile.close();					
		            }
		        }
		    }	
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ocorreu uma excecao", JOptionPane.ERROR_MESSAGE);
		}
	}

}
