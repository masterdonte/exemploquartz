package com.donte.placasocr.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.donte.placasocr.model.PlatesOcr;
import com.donte.placasocr.model.Propriedades;

public class Notificador implements Runnable {

	private PlatesOcr ocr;
	private String url;

	public Notificador(String url, PlatesOcr ocr) {
		this.url = url;
		this.ocr = ocr;
	}

	@Override
	public void run() {	
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5 * 1000).build();
		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
		HttpPost post = new HttpPost(url);		
		try {
			StringEntity postingString = new StringEntity(ocr.toJson());
			post.setEntity(postingString);
			post.setHeader("Content-type", "application/json");
			httpClient.execute(post);
			System.out.println("[SENT]" + ocr.toString());	
		} catch (Exception e) {			
			System.err.println("[ERRO]" + ocr.toString() + " <=> MSG: " + e.getMessage());
			salvandoImagem();
		} finally{
			try {httpClient.close();} catch (IOException e) {System.err.println(e.getMessage());}	
		}
	}

	private void salvandoImagem() {		
		try {
			OutputStream targetFile = new FileOutputStream(Propriedades.pathImg + ocr.getId() + ".jpg");
			targetFile.write(ocr.getImage());
			targetFile.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}		
	}
}
