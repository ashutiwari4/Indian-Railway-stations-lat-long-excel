package railwaystationlocations;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LocationKisser {
	static String temp = null;
	static ArrayList<String> tempList;

	public static void main(String... args) {
		Document doc = null;
		Element x = null;
		tempList = gettingURL.HeaderQuery();
		for (int k = 0; k < tempList.size(); k++) {
			String temp = tempList.get(k);
			try {
				if(temp.contains(" ")){
					temp = temp.replace(" ", "-") ;
				}
				doc = Jsoup.connect(
						"http://www.mapsofindia.com/railways/station-code/"
								+ temp + ".html").get();

				int i = 0;
				Elements scriptElements = doc.getElementsByTag("script");
				for (Element element : scriptElements) {
					if (i == 4)
						x = element;
					for (DataNode node : element.dataNodes()) {
						i++;
					}
				}
			} catch (IOException se) {
				 se.printStackTrace();
			}

			String d = x.toString().split("latt123=")[1];
			String lat = d.split(";")[0].substring(1,
					d.split(";")[0].indexOf(","));
			String lon = d.split(";")[1].substring(
					d.split(";")[1].indexOf("'") + 1,
					d.split(";")[1].indexOf(","));
			String cont = d.split(";")[2].substring(
					d.split(";")[2].indexOf("'") + 1,
					d.split(";")[2].lastIndexOf("'"));

			Element elementsByTag = doc.getElementsByTag("table").get(5);
			String stationCode = elementsByTag.getElementsByTag("tr").get(1)
					.getElementsByTag("td").get(1).text();

			// System.out.println(stationCode);
			/*
			 * System.out.println(lat); System.out.println(lon);
			 * System.out.println(cont);
			 */
			ExcelReadAndWrite.writtingToExcel(cont, stationCode, lat, lon);

		}
	}
}
