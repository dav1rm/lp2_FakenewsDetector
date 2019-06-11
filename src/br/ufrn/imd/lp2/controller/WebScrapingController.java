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

	public ArrayList<String> toCollectData(String url) throws IOException {

		ArrayList<String> data = new ArrayList<String>();

		Document doc = Jsoup.connect(url).get();

		Elements paragraphs = doc.select("p");

		for (Element paragraph : paragraphs) {
			if (paragraph.text().length() > 152) {
				data.add(paragraph.text());
			}
		}

		return data;
	}

	public double measureAccuracy(String quote) {
		return 0;
	}

}
