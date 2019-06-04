package br.ufrn.imd.lp2.controller;

import java.util.HashMap;
import java.util.Set;

import br.ufrn.imd.lp2.model.Quote;

public class SimilarityAnalysisController {
	
	public double cousineSimilarity() 
	{
		return 0;
	}
	
	public double levenshteinDistance() 
	{
		return 0;
	}
	
	//FUNÇÃO jaroWinkler RETIRADA DE http://www.edneiparmigiani.com.br/similaridade-entre-strings-com-jaro-winkler/
	public double jaroWinkler(String str1, String str2) 
	{
	 	int str1_len = str1.length();
        int str2_len = str2.length();
  
        if (str1_len == 0 && str2_len == 0) return 1;
  
        int match_distance = Integer.max(str1_len, str2_len) / 2 - 1;
  
        boolean[] str1_matches = new boolean[str1_len];
        boolean[] str2_matches = new boolean[str2_len];
 
        int matches = 0;
        int transpositions = 0;
 
        for (int i = 0; i < str1_len; i++) {
            int start = Integer.max(0, i - match_distance);
            int end = Integer.min(i + match_distance + 1, str2_len);
 
            for (int j = start; j < end; j++) {
                if (str2_matches[j])
                    continue;
                if (str1.charAt(i) != str2.charAt(j))
                    continue;
                str1_matches[i] = true;
                str2_matches[j] = true;
                matches++;
                break;
            }
        }
 
        if (matches == 0)
            return 0;
 
        int k = 0;
        for (int i = 0; i < str1_len; i++) {
            if (!str1_matches[i])
                continue;
            while (!str2_matches[k])
                k++;
            if (str1.charAt(i) != str2.charAt(k))
                transpositions++;
            k++;
        }
 
        return (((double) matches / str1_len) + ((double) matches / str2_len)
                + (((double) matches - transpositions / 2.0) / matches)) / 3.0;
	}
	
	//https://commons.apache.org/sandbox/commons-text/jacoco/org.apache.commons.text.similarity/CosineSimilarity.java.html
	public double cosineSimilarity(String str1, String str2) 
	{
		if(str1.equals("") || str2.equals("")) 
		{
			//throw exception
		}
		
		
		String [] str1_arr = str1.split(" ");
	    String [] str2_arr = str2.split(" ");
    	HashMap <String, Integer> st1_hash = new HashMap <String, Integer>();
	    for (String s1 : str1_arr) st1_hash.put(s1,s1.length());
	    
	    HashMap <String, Integer> st2_hash = new HashMap <String, Integer>();
	    for (String s2 : str2_arr) st2_hash.put(s2,s2.length());
	    
	    
	    //PEGA INTERSECAO ENTRE AS STRINGS
		Set<String> intersection = getIntersection(st1_hash, st2_hash);
		
		double dotProduct = dotProduct(st1_hash, st2_hash, intersection);
		double d1 = 0.0d;
        for (final Integer value : st1_hash.values()) {
            d1 += Math.pow(value, 2);
        }
        double d2 = 0.0d;
        for (final Integer value : st2_hash.values()) {
            d2 += Math.pow(value, 2);
        }
        double cosineSimilarity;
        if (d1 <= 0.0 || d2 <= 0.0) {
            cosineSimilarity = 0.0;
        } else {
            cosineSimilarity = (double) (dotProduct / (double) (Math.sqrt(d1) * Math.sqrt(d2)));
        }
        return cosineSimilarity;
	}
	
	/**
     * Returns a set with strings common to the two given maps.
     *
     * @param leftVector left vector map
     * @param rightVector right vector map
     * @return common strings
     */
	
    private Set<String> getIntersection(HashMap <String, Integer> st1_hash,HashMap <String, Integer> st2_hash) {

	    st1_hash.keySet().retainAll(st2_hash.keySet());
	    
	    return st1_hash.keySet();
    }
    
    private double dotProduct(HashMap <String, Integer> st1_hash, HashMap <String, Integer> st2_hash, Set<String> intersection) 
    {
    	 long dotProduct = 0;
         for (String key : intersection) {
             dotProduct += st1_hash.get(key) * st2_hash.get(key);
         }
         return dotProduct;
    }
}
