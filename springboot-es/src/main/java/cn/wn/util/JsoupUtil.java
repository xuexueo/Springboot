package cn.wn.util;
import cn.wn.pojo.Goods;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsoupUtil {
    public static List<Goods> get(String str, Integer page) throws IOException {
        List<Goods> list = new ArrayList<>();
        for (int j=0;j<page;j++){

            String url = "http://search.jd.com/Search?keyword=" + str+"&pvid=3e574ec8be5a4b0e821c1b3bf2110b51" +"&page=" + j;

            Connection connect = Jsoup.connect(url).proxy("183.47.237.251", 	80);
            Map<String, String> header = new HashMap<String, String>();
//            header.put("Host", "search.jd.com");
//            header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Safari/537.36");
//            header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//            header.put("Accept-Language", "zh,en-GB;q=0.9,en-US;q=0.8,en;q=0.7,zh-CN;q=0.6");
//            header.put("Connection", "keep-alive");

            Connection data = connect.headers(header);


            //1、通过jsoup爬取网页内容，返回一个浏览器的文档对象
            Document document = data.get();
//            System.out.println("document = " + document);

            //2. 通过document对象获取指定元素（查看jd页面源码）
            Element ele_div = document.getElementById("J_goodsList");

            //3. 根据标签名称，获取所有的li
            Elements ele_lis = ele_div.getElementsByTag("li");
            //4. 遍历所有li元素，获取：商品图片、价格、名称

            for (Element ele_li : ele_lis) {
                String name = ele_li.getElementsByClass("p-name").eq(0).text();
                String price = ele_li.getElementsByClass("p-price").eq(0).text();
                String img = ele_li.getElementsByTag("img").eq(0).attr("data-lazy-img");
                String ids = ele_li.attr("data-sku");
                // 构建Goods对象，添加到集合
                list.add(Goods.builder().name(name).price(price).img(img).build());
            }


        }
        System.out.println("共" + list.size() +"条数据" );
        return list;
    }

}
