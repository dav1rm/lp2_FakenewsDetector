package br.ufrn.imd.lp2.controller;

import java.util.ArrayList;

import br.ufrn.imd.lp2.model.ConsineSimilarity;
import br.ufrn.imd.lp2.model.JaroWinklerStrategy;
import br.ufrn.imd.lp2.model.LevenshteinDistanceStrategy;
import br.ufrn.imd.lp2.model.Quote;
public class MainController {
	private DataStorageController DS;
	private DataProcessorController DP;
	
	MainController()
	{
		FileReaderController FR = new FileReaderController("assets/boatos.csv");

		this.DP = new DataProcessorController(3);

		this.DS = new DataStorageController();

		for (Quote quote : FR.quotes) {
			quote.setTreatedContent(DP.stardardizeQuote(quote.getContent()));
			String hash = DP.generateHash(quote.getTreatedContent());
			this.DS.addToDataStorage(quote, hash);
		}
	}
	public DataStorageController getDataStorage() 
	{
		return DS;
	}
	
	public double analyze(Boolean webscraping, String content, String similarityAlgorithm) 
	{
		LevenshteinDistanceStrategy LD = new LevenshteinDistanceStrategy();
		
		double percentage = 0;
		if(webscraping)
		{
			WebScrapingController WS = new WebScrapingController();
			ArrayList<String> data = WS.toCollectData(content);
			
			String treatedText = "";
			
			for (String text : data) {
				percentage = compare(text, LD, percentage);
			}
		}else 
		{
			percentage = compare(content, LD, percentage);
		}
		
		return percentage;
	}
	public double compare(String content, LevenshteinDistanceStrategy LD, double percentage) 
	{
		String treatedText = DP.stardardizeQuote(content);
		String hash = DP.generateHash(treatedText);
		
		// search by hash
		if (DS.getByHash(hash) != null) {
			return 1;
		}
		
		// if not found, apply similarity analytises
		Quote tmp = null;
		for (Quote quote : DS.getQuotes()) {
			if (LD.score(treatedText, quote.getTreatedContent()) > percentage) {
				percentage = LD.score(treatedText, quote.getTreatedContent());
				tmp = quote;
			}
		}
		return percentage;
	}
}
