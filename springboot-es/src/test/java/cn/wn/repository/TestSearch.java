package cn.wn.repository;

import cn.wn.pojo.Book;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestSearch {
    @Autowired
    private BookRepository bookRepository;
    //如果在使用es的reposit实现索引库操作时候，可以结合Elas一起使用
    @Autowired
    // 在使用es的repositry实现索引库操作时候，如果一些复杂的查询需求，
    // 可以结合ElasticsearchRestTemplate使用, 是对RestHighLevelClient的封装
    private ElasticsearchRestTemplate restTemplate;
//    @Autowired
//    private BookMapper bookMapper;


    //根据条件查询
    @Test
    public void test() {
//        List<Book> list=bookMapper.findAll();
//        bookRepository.saveAll(list);
        List<Book> list = bookRepository.findByBookname("java");
        list.forEach(System.out::println);

    }

    //分页查询
    @Test
    public void findByPage() {
        //使用springboot PageRequest
        /*
        -page，第几页，从0开始，默认为第0页
        -size，每一页的大小，默认为20
        -sort，排序相关的信息，例如sort=firstname&sort=lastname,desc表示在按firstname正序排列基础上按lastname倒序排列
         */
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<Book> page = bookRepository.findAll(pageRequest);
        System.out.println("当前页数据 = " + page.getContent());
        System.out.println("总页数 = " + page.getTotalPages());
        System.out.println("总条数 = " + page.getTotalElements());
    }

    //条件分页
    @Test
    public void conditionPage() {
        /*
        elasticsearchRestTemplate.queryForList是查询一个列表，用的就是ElasticsearchRestTemplate的一个对象实例；
        NativeSearchQuery ：是springdata中的查询条件；
        NativeSearchQueryBuilder ：用于建造一个NativeSearchQuery查询对象；
        QueryBuilders ：设置查询条件，是ES中的类；
        SortBuilders ：设置排序条件；
        HighlightBuilder ：设置高亮显示；
         */
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //换成matchQuery可以搜索到分词搜不到的内容
        builder.withQuery(QueryBuilders.termQuery("bookname", "java"));
        //分页
        //添加高亮
        builder.withPageable(PageRequest.of(0, 2));
        builder.withHighlightFields(new HighlightBuilder.Field("bookname").preTags("<span style='color:red'>").postTags("</span>"));
        //huilder.withHighlighter高亮字段
        SearchHits<Book> searchHits = restTemplate.search(builder.build(), Book.class);
        System.out.println("总记录数" + searchHits.getTotalHits());


//遍历
        List<Book> list = new ArrayList<>();
        for (SearchHit<Book> searchHit : searchHits) {
            Book book = searchHit.getContent();
            List<String> highList = searchHit.getHighlightField("bookname");
           //最好加一个判断是否为0
            book.setBookname(highList.get(0));
            list.add(book);
        }
        System.out.println("list = " + list);
        //第二种写法
//    List<Book>list=
//        searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
//        System.out.println(list);
//    }

        /*

         */

    }
}


