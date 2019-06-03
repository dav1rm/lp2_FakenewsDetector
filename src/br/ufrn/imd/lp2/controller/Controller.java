package br.ufrn.imd.lp2.controller;

import br.ufrn.imd.lp2.model.Quote;

public class Controller {

	public static void main(String[]args) 
	{
		FileReaderController FR = new FileReaderController("assets/boatos.csv");

		DataProcessorController DP = new DataProcessorController(3);

		DataStorageController DS = new DataStorageController();

		for(Quote quote : FR.quotes) 
		{
			quote.setTreatedContent(DP.stardardizeQuote(quote.getContent()));
			String hash = DP.generateHash(quote.getTreatedContent());
			DS.addToDataStorage(quote, hash);
		}
//		DS.showAll();
		
		WebScrapingController WS = new WebScrapingController();
		Quote new_quote = WS.toCollectData("https://www.boatos.org/brasil/usina-itaipu-alerta-vermelho-estourar.html");
		
		new_quote.setTreatedContent(DP.stardardizeQuote(new_quote.getContent()));
		String hash = DP.generateHash(new_quote.getTreatedContent());
		
//		System.out.println("hash: "+hash + "\nconteudo: "+new_quote.getTreatedContent());
		
		
		SimilarityAnalysisController SA = new SimilarityAnalysisController();
		double percentage = 0;
		
		//search by hash
		if (DS.getByHash(hash) == null) {
			// if not found, aply similarity analytises			
			for(Quote quote : DS.getQuotes()) 
			{
				if(SA.jaroWinkler(new_quote.getTreatedContent(), quote.getTreatedContent()) > percentage) {
					percentage = SA.jaroWinkler(new_quote.getTreatedContent(), quote.getTreatedContent());
				}
			}
			
			System.out.printf("Sua notícia é %.1f%% semelhante a uma FAKE NEWS!!!", percentage*100);
		}else {
			System.out.println("Sua notícia é 100% uma FAKE NEWS!!!");
		}
		
	}

}
