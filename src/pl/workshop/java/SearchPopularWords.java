package pl.workshop.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SearchPopularWords {
	
	private static String[] COMMONWORDS = {"jest", "oraz", "było", "były", "ponieważ", "dlatego", "czemu", "czego"};

	public static void main(String[] args) {
		downloadFromWebPage();
		filterPopularWords();
	}

	private static void downloadFromWebPage() {
		Connection connect = Jsoup.connect("http://www.onet.pl/");
		try {
			Document document = connect.get();
			Elements links = document.select("span.title");
			for (Element elem : links) {
				// System.out.println(elem.text());
				String sentence = elem.text();
				savePopularWords(sentence);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void savePopularWords(String word) {
		File popularFile = new File("popular_words.txt");
		try (FileWriter out = new FileWriter(popularFile, true)) {
			StringTokenizer strTok = new StringTokenizer(word, "\n,.\"'()!? :-[]1234567890");
			while (strTok.hasMoreTokens()) {
				String token = strTok.nextToken().toLowerCase();
				if (token.length() > 3) {
					out.append(token).append("\n");
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private static void filterPopularWords() {
		File popular = new File("popular_words.txt");
		File filtered = new File("filtered_words.txt");
		try {
			if (!filtered.exists()) {
				filtered.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Path filteredFile = Paths.get(filtered.getName());
		List<String> popularList = new ArrayList<>();

		if (Files.exists(filteredFile)) {
			try (FileWriter filteredOut = new FileWriter(filtered, true);
					Scanner popularScan = new Scanner(popular)) {
				System.out.println("File created.");
				while (popularScan.hasNext()) {
					popularList.add(popularScan.next());
				}
				for (String s : COMMONWORDS) {
					while (popularList.contains(s)) {
						popularList.remove(s);
					}
				}
				Iterator itr = popularList.iterator();
				while (itr.hasNext()) {
					filteredOut.append(itr.next() + "\n");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			System.out.println("File doesn't exists - somethign went wrong");
		}
	}
}
