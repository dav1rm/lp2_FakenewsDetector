package br.ufrn.imd.lp2.controller;

public class MainController {

	public static void main(String[] args) {
		DataProcessorController processor = new DataProcessorController(3);
		String str = "“Se você enviar para apenas 20 contatos em um minuto... o Brasil inteiro vai desmascarar este Bandido. NÃO quebre essa corrente. Os incautos precisam ser esclarecidos antes que seja tarde demais...”. “Essa é a jornalista Patrícia Campos Mello, que fez matéria contra Bolsonaro na Folha. Petista de carteirinha!!”";
		
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
