package src.br.ufrn.imd.lp2.controller;

public class MainController {

	public static void main(String[] args) {
		DataProcessorController processor = new DataProcessorController(3);
		
		String str = "teste";
		String result = "";
		System.out.println("antes: "+str);
		result = processor.removeUnqualifiedWords(str);
		result = processor.removeSpecialCharacters(result);
		result = processor.removeRepeatedWords(result);
		result = processor.alphabeticalSort(result);
		System.out.println("depois: "+result);
		
		System.out.println("hash: "+processor.generateHash(result));
	}

}
