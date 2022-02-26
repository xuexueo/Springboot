//package cn.wn.jsoup.test;
//import cn.wn.pojo.Goods;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.junit.Test;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//public class App {
//    @Test
//    public void test() throws Exception {
//        String url = "http://search.jd.com/Search?keyword=java";
//        //1、通过jsoup爬取网页内容，返回一个浏览器的文档对象
//        Document document = Jsoup.parse(new URL(url), 30000);
//        //2. 通过document对象获取指定元素（查看jd页面源码）
//        Element ele_div = document.getElementById("J_goodsList");
//        //3. 根据标签名称，获取所有的li
//        Elements ele_lis = ele_div.getElementsByTag("li");
//        //4. 遍历所有li元素，获取：商品图片、价格、名称
//        List<Goods> list = new ArrayList<>();
//        for (Element ele_li : ele_lis) {
//            String name = ele_li.getElementsByClass("p-name").eq(0).text();
//            String price = ele_li.getElementsByClass("p-price").eq(0).text();
//            String img = ele_li.getElementsByTag("img").eq(0).attr("data-lazy-img");
//            // 构建Goods对象，添加到集合
//            list.add(Goods.builder().name(name).price(price).img(img).build());
//        }
//    }
//    /**
//     * 接口1： http://localhost:8080/parse/java   调用工具类，爬取京东数据，保存到es索引库中。
//     * 接口1： http://localhost:8080/parse/衣服
//     *
//     * 接口2： http://localhost:8080/goods/java/1/20
//     * 搜索的关键字： java
//     * 当前页：       1
//     * 页大小：       20
//     * 搜索的数据，来自于接口1爬取的数据。
//     */
//    @Test
//    public void test2() throws IOException {
//        List<Goods> list = JsoupUtils.parseJd("java"); // 自己提取工具类
//        list.forEach(System.out::println);
//    }
//}