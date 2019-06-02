package br.ufrn.imd.lp2.controller;

import br.ufrn.imd.lp2.model.DataStorage;
import br.ufrn.imd.lp2.model.Quote;

public class DataStorageController {
	
	private DataStorage dataStorage;

	DataStorageController() {
		this.dataStorage = new DataStorage();
	}

	public void showAll() {
		System.out.println(this.dataStorage.getStorage());
	}

	public void getByHash(String hash) {
		this.dataStorage.getStorage().get(hash);
	}

	public void addToDataStorage(Quote quote, String hash) {
		this.dataStorage.getStorage().put(hash, quote);
	}

}
