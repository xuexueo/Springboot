package cn.wn.controller;
import cn.wn.pojo.Goods;
import cn.wn.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    /**
     * 接口描述：根据关键字，爬取京东数据，保存到es(索引库中)
     * 接口地址：http://localhost:8080/parse/路径参数
     */
    /**
     * 接口描述：提供关键字分页查询服务
     * http://localhost:8080/goods/java/1/5
     * @PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值
     */
    @GetMapping("/goods/{keyword}/{pageNum}/{pageSize}")
    public List<Goods> search(
            @PathVariable("keyword") String keyword,
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("pageSize") Integer pageSize) {
        return goodsService.search(keyword,pageNum,pageSize);
    }

    @GetMapping("/parse/{keyword}")
    public Boolean parseJd(@PathVariable("keyword") String keyword){
        goodsService.parseJd(keyword);
        return true;
    }
}