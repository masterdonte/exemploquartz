package com.donte.placasocr.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;

public class Propriedades {

	private final static SimpleDateFormat FORMATADOR = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
	private final static String arquivo   = "aplicacao.properties";			
	private static Properties props = null;
	
	public final static String urlOcr  = Propriedades.getString("ocr.url");
	public final static String pathImg = Propriedades.getString("ocr.img");

	private static Properties getResource() {
		if(props == null) {
			try {
				props = new Properties();//Carregando o arquivo quando ele está no classpath input = getClass().getClassLoader().getResourceAsStream(arquivo);
				InputStream input = new FileInputStream(arquivo);				
				props.load(input);
				input.close();
			} catch (IOException e) {
				System.err.println("Erro ao carregar as configuracoes:\n" + e.getMessage());
				System.exit(1);
			}			
		}
		return props;		
	}

	public static String getString(String key){
		return getResource().getProperty(key);		
	}

	public static String[] getStringArray(String key) throws Exception{
		String array = getResource().getProperty(key);
		StringTokenizer st = new StringTokenizer(array, ",");
		int tokens = st.countTokens();
		String[] retorno = new String[tokens];
		for (int i = 0; i < tokens; i++) {
			retorno[i] = st.nextToken();
		}
		return retorno;
	}

	public static int getInt(String key) throws Exception{
		return new Integer(getResource().getProperty(key)).intValue();

	}

	public boolean getBoolean(String key) throws Exception{		
		return new Boolean(getResource().getProperty(key)).booleanValue();		
	}

	public static String getDataFormatada(Date date){
		if(date == null) date = new Date();
		return FORMATADOR.format(date);
	}

	public static void showProperties() {
		Enumeration<?> e = props.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = props.getProperty(key);
			System.out.println("Key : " + key + ", Value : " + value);
		}
	}

	@SuppressWarnings("unused")
	private static void addProperties() {
		Properties prop = new Properties();
		OutputStream output = null;
		try {
			output = new FileOutputStream("aplicacao.properties");
			// set the properties value
			prop.setProperty("database", "http://172.10.2.25:9000/biometria");
			prop.setProperty("dbuser", "mkyong");
			prop.setProperty("dbpassword", "password");
			// save properties to project root folder
			prop.store(output, null);
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


}
