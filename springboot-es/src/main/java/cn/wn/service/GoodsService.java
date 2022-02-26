package cn.wn.service;

import cn.wn.pojo.Goods;
import cn.wn.repository.GoodsRepository;
import cn.wn.util.JsoupUtil2;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsService {
    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    @Autowired
    private GoodsRepository goodsRepository;

    /**
     * 搜索
     *
     * @param keyword  根据关键字查询索引库 （Goods.name）
     * @param pageNum  当前页
     * @param pageSize 页大小
     * @return
     */
//爬虫方法
    public void parseJd(String keyword) {
        try {
            // 通过jsoup爬取网页数据
            List<Goods> list = JsoupUtil2.get(keyword);
            // 保存到索引库
            goodsRepository.saveAll(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Goods> search(String keyword, Integer pageNum, Integer pageSize) {
        // 创建条件构造器: 添加查询条件、设置分页对象、高亮处理
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder()
                //查询条件
                //Creates a match query with type "BOOLEAN" for the provided field name and text.
                .withQuery(QueryBuilders.matchQuery("name", keyword))
                /*
                Creates a new unsorted PageRequest.
                Params:
                page – zero-based page index, must not be negative.
                size – the size of the page to be returned, must be greater than 0.
                 */
                .withPageable(PageRequest.of(pageNum - 1, pageSize))
                //高亮处理
                .withHighlightFields(
                        new HighlightBuilder.Field("name")
                                //preTags->Set the pre tags that will be used for highlighting.
                               //postTags->Set the post tags that will be used for highlighting.
                                .preTags("<span style='color:red'>").postTags("</span>"));
        // 执行搜索
        SearchHits<Goods> search = restTemplate.search(builder.build(), Goods.class);
        // 处理结果
        //.map->Returns a stream consisting of the results of applying the given function to the elements of this stream.
        List<Goods> list = search.get().map(searchHit -> {
            //Returns:the object data from the search.
            Goods goods = searchHit.getContent();
            //gets the highlight values for a field.
            List<String> highList = searchHit.getHighlightField("name");
            //非空才进行设置
            if (!CollectionUtils.isEmpty(highList)) {
                goods.setName(highList.get(0));
            }
            return goods;
            //Returns a Collector that accumulates the input elements into a new List.
        }).collect(Collectors.toList());
        return list;
    }
}