package br.ufrn.imd.lp2.controller;

import java.io.IOException;
import java.util.ArrayList;

import br.ufrn.imd.lp2.model.AnalysisResult;
import br.ufrn.imd.lp2.model.ConsineSimilarity;
import br.ufrn.imd.lp2.model.JaroWinklerStrategy;
import br.ufrn.imd.lp2.model.LevenshteinDistanceStrategy;
import br.ufrn.imd.lp2.model.Quote;

public class MainController {
	private DataStorageController DS;
	private DataProcessorController DP;

	MainController() {
		FileReaderController FR = new FileReaderController("assets/boatos.csv");

		this.DP = new DataProcessorController(3);

		this.DS = new DataStorageController();

		for (Quote quote : FR.quotes) {
			quote.setTreatedContent(DP.stardardizeQuote(quote.getContent()));
			String hash = DP.generateHash(quote.getTreatedContent());
			this.DS.addToDataStorage(quote, hash);
		}
	}

	public DataStorageController getDataStorage() {
		return DS;
	}

	public AnalysisResult analyze(Boolean webscraping, String content, String similarityAlgorithm) throws IOException {
		LevenshteinDistanceStrategy LD = new LevenshteinDistanceStrategy();

		double percentage = 0;
		String finalQuote = "";
		String txt = "";
		AnalysisResult AR = new AnalysisResult();

		if (webscraping) {
			WebScrapingController WS = new WebScrapingController();
			ArrayList<String> data = WS.toCollectData(content);

			for (String text : data) {
				String treatedText = DP.stardardizeQuote(text);
				String hash = DP.generateHash(treatedText);

				// search by hash
				if (DS.getByHash(hash) != null) {
					AR.setFakenews(text);
					AR.setContent(text);
					AR.setAccuracy(1);
					return AR;
				}

				// if not found, apply similarity analytises
				for (Quote quote : DS.getQuotes()) {
					if (LD.score(treatedText, quote.getTreatedContent()) > percentage) {
						percentage = LD.score(treatedText, quote.getTreatedContent());
						finalQuote = quote.getContent();
						txt = text;
					}
				}
			}

		} else {

			String treatedText = DP.stardardizeQuote(content);
			String hash = DP.generateHash(treatedText);

			if (DS.getByHash(hash) != null) {
				AR.setFakenews(content);
				AR.setContent(content);
				AR.setAccuracy(1);
				return AR;
			}

			// if not found, apply similarity analytises
			for (Quote quote : DS.getQuotes()) {
				if (LD.score(treatedText, quote.getTreatedContent()) > percentage) {
					percentage = LD.score(treatedText, quote.getTreatedContent());
					finalQuote = quote.getContent();
					txt = content;
				}
			}
		}

		AR.setFakenews(finalQuote);
		AR.setContent(txt);
		AR.setAccuracy(percentage);

		return AR;
	}
}
