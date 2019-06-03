package br.ufrn.imd.lp2.controller;

import br.ufrn.imd.lp2.model.DataStorage;
import br.ufrn.imd.lp2.model.Quote;

public class DataStorageController {
	private DataStorage dataStorage;

	DataStorageController() {
		this.dataStorage = new DataStorage();
	}

	public void showAll() {
		System.out.println("---------------------------------------");
		for (Quote quote : this.dataStorage.getStorage().values()) {
			System.out.println("ID: "+quote.getId());
			System.out.println("Conteúdo: "+quote.getContent());
			System.out.println("Conteúdo tratado: "+quote.getTreatedContent());
			System.out.println("Data de criação: "+quote.getDate());
			System.out.println("URL: "+quote.getUrl());
			System.out.println("---------------------------------------");
		}
	}

	public void getByHash(String hash) {
		this.dataStorage.getStorage().get(hash);
	}

	public void addToDataStorage(Quote quote, String hash) {
		this.dataStorage.addToDataStorage(quote, hash);

	}

}
