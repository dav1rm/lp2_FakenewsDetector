package br.ufrn.imd.lp2.controller;

import java.util.ArrayList;

import br.ufrn.imd.lp2.model.Quote;

public class Controller {

	public static void main(String[] args) {
		FileReaderController FR = new FileReaderController("assets/boatos.csv");

		DataProcessorController DP = new DataProcessorController(3);

		DataStorageController DS = new DataStorageController();

		for (Quote quote : FR.quotes) {
			quote.setTreatedContent(DP.stardardizeQuote(quote.getContent()));
			String hash = DP.generateHash(quote.getTreatedContent());
			DS.addToDataStorage(quote, hash);
		}
//		DS.showAll();

		WebScrapingController WS = new WebScrapingController();
		ArrayList<String> data = WS
				.toCollectData("https://www.boatos.org/brasil/usina-itaipu-alerta-vermelho-estourar.html");

		SimilarityAnalysisController SA = new SimilarityAnalysisController();
		
		LevenshteinDistanceStrategy LD = new LevenshteinDistanceStrategy();
		JaroWinklerStrategy JW = new JaroWinklerStrategy();
		ConsineSimilarity CS = new ConsineSimilarity();
		
		double percentage = 0;

		System.out.println("levens:  "+LD.score("bolo preto casa", "bola preto casa"));
		System.out.println("jarowinkler:  "+JW.score("bolo preto casa", "bola preto casa"));
		System.out.println("consine:  "+CS.cosineSimilarity("bolo preto casa", "bola preto casa"));
//		String treatedText = "";
//		Quote a = null;
//		String txt = "";
//		for (String text : data) {
//			treatedText = DP.stardardizeQuote(text);
//			String hash = DP.generateHash(treatedText);
////			System.out.println("hash: "+hash+ "\ntext: "+treatedText);
//
//			// search by hash
//			if (DS.getByHash(hash) != null) {
//
//				System.out.println("Sua notícia é 100% uma FAKE NEWS!!!");
//				return;
//
//			}
//			// if not found, aply similarity analytises
//			for (Quote quote : DS.getQuotes()) {
//				if (SA.cosineSimilarity(treatedText, quote.getTreatedContent()) > percentage) {
//					percentage = SA.cosineSimilarity(treatedText, quote.getTreatedContent());
//					a = quote;
//					txt = text;
//				}
//			}
//		}
//		
//		System.out.printf("Sua notícia é %.1f%% semelhante a uma FAKE NEWS!!!\n", percentage * 100);
//		System.out.println("trecho: " + treatedText);
//		System.out.println("fake news: " + a.getTreatedContent());
//		
//		new_quote.setTreatedContent(DP.stardardizeQuote(new_quote.getContent()));
//		String hash = DP.generateHash(new_quote.getTreatedContent());

//		System.out.println("hash: "+hash + "\nconteudo: "+new_quote.getTreatedContent());

	}

}
