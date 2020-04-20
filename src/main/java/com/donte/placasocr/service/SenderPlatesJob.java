package com.donte.placasocr.service;

import static com.donte.placasocr.model.Propriedades.urlOcr;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.donte.placasocr.model.PlatesOcr;
import com.donte.placasocr.persistence.PlacasOcrDao;

public class SenderPlatesJob implements Job{
	
	private PlacasOcrDao repo = new PlacasOcrDao();		
		
	public void execute(JobExecutionContext context)throws JobExecutionException {				
		processarEntradas();
		System.out.println("Rodando..");
	}

	private void processarEntradas() {		
		repo.processarEntradas();
		List<PlatesOcr> lista = repo.findByEnviado(false);
		for (PlatesOcr ocr : lista) {			
			repo.atualizarEnviado(ocr.getId(), true);
			prepararTransmissao(ocr);			
		}
	}

	private void prepararTransmissao(PlatesOcr ocr) {
		Thread thread = new Thread(new Notificador(urlOcr, ocr));
		thread.start();	
	}		
	
}
