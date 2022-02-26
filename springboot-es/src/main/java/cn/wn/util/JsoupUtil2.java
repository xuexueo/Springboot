package cn.wn.util;
import cn.wn.pojo.Goods;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsoupUtil2 {
    public static List<Goods> get(String str) throws IOException {

        String url = "http://search.jd.com/Search?keyword="+str;
        //下方添加
//        Connection connect = Jsoup.connect(url).proxy("101.132.186.39", 	9090);
//        Map<String, String> header = new HashMap<String, String>();
//        header.put("Host", "search.jd.com");
//        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Safari/537.36");
//        header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//        header.put("Accept-Language", "zh,en-GB;q=0.9,en-US;q=0.8,en;q=0.7,zh-CN;q=0.6");
//        header.put("Connection", "keep-alive");
//        Connection data = connect.headers(header);
        //1、通过jsoup爬取网页内容，返回一个浏览器的文档对象
        //Fetch a URL, and parse it as HTML. Provided for compatibility; in most cases use connect(String) instead.
        Document document = Jsoup.parse(new URL(url), 30000);
        //上方添加
        //2. 通过document对象获取指定元素（查看jd页面源码）
        //Find an element by ID, including or under this element.
        Element ele_div = document.getElementById("J_goodsList");
        //3. 根据标签名称，获取所有的li
        //Finds elements, including and recursively under this element, with the specified tag name.
        Elements ele_lis = ele_div.getElementsByTag("li");
        //4. 遍历所有li元素，获取：商品图片、价格、名称
        List<Goods> list = new ArrayList<>();
        for (Element ele_li : ele_lis) {
            //getElementsByClass->Find elements that have this class, including or under this element. Case insensitive.
            //                eq->Get the nth matched element as an Elements object.
            //              text->Get the combined text of all the matched elements.
            String name = ele_li.getElementsByClass("p-name").eq(0).text();
            String price = ele_li.getElementsByClass("p-price").eq(0).text();
            String img = ele_li.getElementsByTag("img").eq(0).attr("data-lazy-img");
            // 构建Goods对象，添加到集合
            list.add(Goods.builder().name(name).price(price).img(img).build());
        }
        return list;
    }


}
