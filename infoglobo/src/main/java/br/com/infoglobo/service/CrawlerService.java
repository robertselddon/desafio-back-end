package br.com.infoglobo.service;


import br.com.infoglobo.entity.Description;
import br.com.infoglobo.entity.Feed;
import br.com.infoglobo.entity.Item;
import br.com.infoglobo.types.CrawlerType;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Service
public class CrawlerService {

    @Value("${feeds.url}")
    private String url;


    public Feed capturarFeedsCrawler() throws IOException, FeedException{

        SyndFeed syndFeed = new SyndFeedInput().build(new XmlReader(new URL(url)));
        Feed feed = new Feed();

        List<Item> itemList = new ArrayList<>();
        for (Object o : syndFeed.getEntries()) {
            SyndEntry syndEntry = (SyndEntry) o;
            Item item = crawlerMapper(syndEntry);
            itemList.add(item);
        }
        feed.setItem(itemList);

        return feed;
    }

    private Item crawlerMapper(SyndEntry syndEntry){
        Item item = new Item();
        List<Description> descriptions = new ArrayList<>();

        item.setLink(syndEntry.getLink());
        item.setTitle(syndEntry.getTitle());
        SyndContent syndContent = syndEntry.getDescription();

        minerarImg(syndContent, descriptions);
        minerarLinks(syndContent, descriptions);
        minerarTexto(syndContent, descriptions);

        item.setDescription(descriptions);

        return item;
    }


    private void minerarImg(SyndContent syndContent, List<Description> descriptions){

        Document doc = Jsoup.parse(syndContent.getValue());
        Elements elements = doc.select("div img[src]");

        for (Element element: elements) {
            Description description = new Description();
            description.setType(CrawlerType.IMG.getValue());
            description.setContent(element.attr("src"));
            descriptions.add(description);
        }

    }


    private void minerarLinks(SyndContent syndContent, List<Description> descriptions){
        Document doc = Jsoup.parse(syndContent.getValue());
        Elements elements = doc.select("ul li a[href]");

        List<String> links = new ArrayList<>();


        Description description = new Description();
        for (Element element: elements) {
            description.setType(CrawlerType.LINKS.getValue());
            String link = element.attr("href");
            links.add(link);
        }
        description.setContent(links);
        descriptions.add(description);

    }

    private void minerarTexto(SyndContent syndContent, List<Description> descriptions){
        Document doc = Jsoup.parse(syndContent.getValue());
        Elements elements = doc.select("p");

        for (Element element: elements) {
            Description description = new Description();
            description.setType(CrawlerType.TEXT.getValue());
            description.setContent(element.text());
            descriptions.add(description);
        }
    }
}
