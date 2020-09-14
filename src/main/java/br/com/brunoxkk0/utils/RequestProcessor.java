package br.com.brunoxkk0.utils;

import br.com.brunoxkk0.core.Event;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashSet;

public class RequestProcessor {

    private  final LinkedHashSet<Event> events;

    public RequestProcessor(byte[] data) throws InvalidSroException {
        Document document = Jsoup.parse(new String(data, StandardCharsets.UTF_8));

        Elements element = document.body().getElementsByClass("listEvent sro");

        events = new LinkedHashSet<>();

        if(element != null && !element.isEmpty()) {
            for (Element row : element.select("tr")) {
                events.add(new Event(
                        row.getElementsByClass("sroDtEvent").text(),
                        row.getElementsByClass("sroLbEvent").text()
                ));
            }
        }else{
            throw new InvalidSroException();
        }

    }

    public LinkedHashSet<Event> getEvents() {
        return events;
    }
}
