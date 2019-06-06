package br.ufrn.imd.lp2.controller;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class WebScrapingController {

	public ArrayList<String> toCollectData(String url) {
		
		ArrayList<String> data = new ArrayList<String>();
		
		try {
			Document doc = Jsoup.connect(url).get();
			
			Elements paragraphs = doc.select("p");
			
			for (Element paragraph : paragraphs) {
				if (paragraph.text().length() > 152) {
					data.add(paragraph.text());
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	public double measureAccuracy(String quote) {
		return 0;
	}

}
