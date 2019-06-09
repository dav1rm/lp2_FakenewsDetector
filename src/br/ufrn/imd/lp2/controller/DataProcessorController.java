package src.br.ufrn.imd.lp2.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

public class DataProcessorController {
	private int MIN_CHARACTERS;

	DataProcessorController(int min_char) {
		this.MIN_CHARACTERS = min_char;
	}
	public String stardardizeQuote(String content) 
	{
		content = this.removeUnqualifiedWords(content);
		content = this.removeSpecialCharacters(content);
		content = this.removeRepeatedWords(content);
		content = this.alphabeticalSort(content);
		return content;
	}
	public String removeUnqualifiedWords(String content) {
		// Removes punctuation
		String str = content.replaceAll("\\p{P}", "");
		// Transform in array of words
		String[] splitedArray = str.split(" ");

		StringBuilder builder = new StringBuilder();
		for (String word : splitedArray) {
			if (word.length() > this.MIN_CHARACTERS) {
				builder.append(word + " ");
			}
		}

		return builder.toString().trim();
	}

	public String removeSpecialCharacters(String content) {
		content = Normalizer.normalize(content, Normalizer.Form.NFD).replaceAll("[^a-zA-Z\\s]", "");

		return content.toLowerCase();
	}

	public String removeRepeatedWords(String content) {
		String[] splitedArray = content.split(" ");

		// hash_set to saves the words adds
		Set<String> saved_words = new HashSet<String>();
		StringBuilder builder = new StringBuilder();

		for (String word : splitedArray) {
			if (!saved_words.contains(word)) {
				builder.append(word + " ");
				saved_words.add(word);
			}
		}

		return builder.toString().trim();
	}

	public String alphabeticalSort(String content) {
		String[] splitedArray = content.split(" ");
		Arrays.sort(splitedArray);

		StringBuilder builder = new StringBuilder();

		for (String word : splitedArray) {
			builder.append(word + " ");
		}

		return builder.toString().trim();
	}

	public String generateHash(String content) {
		String sha1 = "";

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(content.getBytes("utf-8"));

			sha1 = DatatypeConverter.printHexBinary(md.digest());
		} catch (NoSuchAlgorithmException e) {
			System.out.println("No Such Algorithm Exception!!!");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unsupported Encoding Exception!!!");
		}

		return sha1;
	}
}
