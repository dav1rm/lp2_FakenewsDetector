package br.ufrn.imd.lp2.controller;

import java.util.HashMap;

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
}
