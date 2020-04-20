package com.donte.placasocr.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import com.donte.placasocr.model.Propriedades;

public class DataSourceCPDS {		

	private final String driver = Propriedades.getString("mssql.driver");
	private final String url    = Propriedades.getString("mssql.url");
	private final String user   = Propriedades.getString("mssql.user");
	private final String pass   = Propriedades.getString("mssql.pass");

	private static DataSourceCPDS datasource;
	private final ComboPooledDataSource cpds;

	private DataSourceCPDS() {    	
		cpds = new ComboPooledDataSource();
		try {        	     			   			
			cpds.setDriverClass(driver);
			cpds.setJdbcUrl(url);
			cpds.setUser(user);
			cpds.setPassword(pass);
			cpds.setMinPoolSize(5);
			cpds.setAcquireIncrement(5);
			cpds.setMaxPoolSize(20);
			cpds.setMaxStatements(180);
		} catch (Exception e) {		
			System.err.println("Erro ao carregar as configuracoes de banco:\n" + e.getMessage());
			System.exit(1);
		}        
	}

	public static DataSourceCPDS getInstance(){
		if (datasource == null) {
			datasource = new DataSourceCPDS();
		}
		return datasource;        
	}

	public Connection getConnection() throws SQLException {
		return this.cpds.getConnection();
	}

	public void mostarAtributos() throws SQLException{
		System.out.println("Driver " + driver);
		System.out.println("Url " + url);
		System.out.println("Usuário " + user);
		System.out.println("Senha " + pass);
		System.out.println("NumActive: " + cpds.getNumConnections());
		System.out.println("NumIdle: " + cpds.getNumBusyConnections());
	}

}
