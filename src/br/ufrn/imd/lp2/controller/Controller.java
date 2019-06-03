package br.ufrn.imd.lp2.controller;

import br.ufrn.imd.lp2.model.Quote;

public class Controller {

	public static void main(String[]args) 
	{
		FileReaderController FR = new FileReaderController("assets/boatos.csv");

		DataProcessorController DP = new DataProcessorController(3);

		DataStorageController DS = new DataStorageController();

		SimilarityAnalysisController SA = new SimilarityAnalysisController();
		
		System.out.println(SA.jaroWinkler("PARMIGIANI", "MIGAN"));
		
		for(Quote quote : FR.quotes) 
		{
			quote.setTreatedContent(DP.stardardizeQuote(quote.getContent()));
			String hash = DP.generateHash(quote.getTreatedContent());
			DS.addToDataStorage(quote, hash);
		}
		DS.showAll();
		
	}

}
