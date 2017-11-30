import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class JavaParser {

    String url = "https://rozetka.com.ua/ua/universalnye-mobilnye-batarei/c387969";

    public ArrayList<Item> parseCategory() throws IOException {
        Document categoryHtml = Jsoup.connect(url).timeout(6000).get();
        Elements itemsPage = categoryHtml.select("div#catalog_goods_block");
        ArrayList<Item> allItemsInCAtegory = new ArrayList<Item>();

        String allPages = itemsPage.select("nav.paginator-catalog").text().trim();
        allPages = allPages.substring(allPages.lastIndexOf(" ") + 1, allPages.length());
        int numberOfPages = Integer.valueOf(allPages);
//        System.out.println("number of pages: " + numberOfPages);

        for (int i = 1; i <= numberOfPages; i++){
            String pageUrl = this.url + "/page=" + i + "/";
//            System.out.println("page url: " + pageUrl);
            allItemsInCAtegory.addAll(parsePage(pageUrl));
            System.out.println(allItemsInCAtegory);
        }
        return allItemsInCAtegory;
    }

    public ArrayList<Item> parsePage(String pageUrl) throws IOException {
        Document pageHtml = Jsoup.connect(pageUrl).timeout(6000).get();
        Elements goodsHtml = pageHtml.getElementsByClass("g-i-tile-i-title clearfix").select("a[href]");
        ArrayList<Item> itemsOnPage = new ArrayList<Item>();

        for (Element itemHtml  : goodsHtml) {
            String itemUrl = itemHtml.attr("href");
//            System.out.println("comments url: " + itemUrl);
            itemsOnPage.add(parseItem(itemUrl));
        }
        return itemsOnPage;
    }

    public Item parseItem(String itemUrl) throws IOException {
        Document itemHtml = Jsoup.connect(itemUrl).timeout(6000).get();
        String commentsUrl = itemUrl + "#tab=comments";
        String itemCode = itemHtml.getElementsByClass("detail-code-i").text();
        Item tempItem = new Item(itemUrl, itemCode, parseComments(commentsUrl));
        return tempItem;

    }

    public ArrayList<Comment> parseComments(String commentsUrl) throws IOException {
        Elements commentsHtml = Jsoup.connect(commentsUrl).timeout(6000).get().getElementsByClass("pp-review-i");
        ArrayList<Comment> commentsList = new ArrayList<Comment>();
        for (Element comment : commentsHtml) {
            String stars = comment.getElementsByClass("sprite g-rating-stars-i").attr("content");
            String commentText = comment.getElementsByClass("pp-review-text").text();
            if (!stars.equals("")){
                Comment tempComment = new Comment(stars, commentText);
                commentsList.add(tempComment);
            }
        }
        return commentsList;
    }
}
