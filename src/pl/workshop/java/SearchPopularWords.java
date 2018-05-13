package pl.workshop.java;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SearchPopularWords {

	public static void main(String[] args) {
		try (PrintWriter out = new PrintWriter("popular_words.txt")) {
			Connection connect = Jsoup.connect("http://www.onet.pl/");
			try {
				Document document = connect.get();
				Elements links = document.select("span.title");
				for (Element elem : links) {
//					System.out.println(elem.text());
					String sentence = elem.text();
					StringTokenizer strTok = new StringTokenizer(sentence, "\n,.\"'()!?");
					while(strTok.hasMoreTokens()) {
						out.println(strTok.nextToken());
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}
