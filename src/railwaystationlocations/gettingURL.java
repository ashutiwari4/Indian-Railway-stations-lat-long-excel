package railwaystationlocations;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class gettingURL {

    static ArrayList<String> stationList = new ArrayList<String>();

    public static ArrayList<String> HeaderQuery() {
        Document doc = null;
        try {
            for (int i = 65; i < 90; i++) {
                doc = Jsoup.connect("http://www.mapsofindia.com/railways/station-code/name.php?name=" + Character.toString((char) i)).get();
                org.jsoup.select.Elements links = doc.select("ul li");
                for (Element ele : links) {
                    stationList.add(ele.text());
                }
            }
        } catch (IOException se) {
            se.printStackTrace();
        }
        return stationList;
    }
}
