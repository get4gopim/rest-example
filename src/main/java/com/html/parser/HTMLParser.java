package com.html.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParser {

	public static HTMLParser PARSER = new HTMLParser();
	
	public static void main(String[] args) throws Exception {
		PARSER.listMovieNames();
		//PARSER.getMovieDetails("https://en.wikipedia.org/wiki/From_Vegas_to_Macau_III");
	}

	public void listMovieNames() throws Exception {
		Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/1999_in_film").get();
		Element table = doc.select("div#mw-content-text table").get(2);
		Elements rows = table.select("tr");
		
		System.out.println("size = " + rows.size());
		
		if (rows.size() <= 2) {
			table = doc.select("div#mw-content-text table").get(3);
			rows = table.select("tr");
			System.out.println("revised size = " + rows.size());
		}		
		
		for (Element row : rows) {
			Elements link = row.select("td i a");
			String absHref = link.attr("abs:href");

			if (link.text().length() > 0) {
				System.out.println(link.text() + " (" + absHref + ") ");
				System.out.println(" ---------------------------------------------------- ");
				
				//PARSER.getMovieDetails(absHref);
				
				System.out.println("\n");
			}
		}		
	}
	
	public void getMovieDetails(String movieLink) throws Exception {
		Document doc = Jsoup.connect(movieLink).get();
		Element table = doc.select("div#mw-content-text table").get(0);
		
		int i = 0;
		for (Element row : table.select("tr")) {
			Elements th = row.select("th");			
			Elements td = row.select("td");
			
			if (i == 0) {
				System.out.println("Title = " + th.text());
			}
			if (i == 1) {
				Elements img = td.select("img");
				System.out.println("imgUrl = " + img.attr("abs:src"));
			}
			
			if (th.text().length() > 0 && td.text().length() > 0) {
				
				getContent("Directed by", th, td);
				getContent("Music by", th, td);
				
				if (th.text().trim().equalsIgnoreCase("Starring")) {
					//System.out.println("*** " + th.text() + " ****");					
					Elements starringDiv = row.select("div ul li");
					if (starringDiv.size() == 0) {
						starringDiv = row.select("a");
					}
					//System.out.println("starringDiv = " + starringDiv);
					
					if (starringDiv.size() >= 2) {
						Element actor = starringDiv.get(0);
						Element actress = starringDiv.get(1);
						System.out.println("Actor = " + actor.text());
						System.out.println("Actress = " + actress.text());
					}
					
					
				} /*else {
					System.out.println(th.text() + " :: " + td.text());
				}*/				
				
			}
			
			i++;
		}	
	}

	private String getContent(String header, Elements th, Elements td) {
		String content = "";
		if (th.text().equalsIgnoreCase(header)) {
			System.out.println(th.text() + " = " + td.text());
			content = td.text();
		}
		return content;
	}

}
