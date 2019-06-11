package br.ufrn.imd.lp2.controller;

import java.util.ArrayList;

import br.ufrn.imd.lp2.model.AnalysisResult;
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
	
	public AnalysisResult analyze(Boolean webscraping, String content, String similarityAlgorithm) 
	{
		LevenshteinDistanceStrategy LD = new LevenshteinDistanceStrategy();
		
		double percentage = 0;
		AnalysisResult AR = null;
		if(webscraping)
		{
			WebScrapingController WS = new WebScrapingController();
			ArrayList<String> data = WS.toCollectData(content);
			
			String treatedText = "";
			
			for (String text : data) {
				AR = compare(text, LD, percentage);

				percentage = AR.getAccuracy();
			}

		}else 
		{
			AR = compare(content, LD, percentage);
		}
		return AR;
	}
	public AnalysisResult compare(String content, LevenshteinDistanceStrategy LD, double percentage) 
	{

		AnalysisResult AR = new AnalysisResult();
		String treatedText = DP.stardardizeQuote(content);
		String hash = DP.generateHash(treatedText);
		
		// search by hash
		if (DS.getByHash(hash) != null) {
			AR.setFakenews(content);
			AR.setAccuracy(1);
			AR.setContent(content);
			return AR;
		}
		
		// if not found, apply similarity analytises
		Quote tmp = null;
		for (Quote quote : DS.getQuotes()) {
			if (LD.score(treatedText, quote.getTreatedContent()) > percentage) {
				percentage = LD.score(treatedText, quote.getTreatedContent());
				tmp = quote;
			}
		}
		
		AR.setFakenews(tmp.getContent());
		AR.setAccuracy(percentage);
		AR.setContent(content);
		return AR;
	}
}
