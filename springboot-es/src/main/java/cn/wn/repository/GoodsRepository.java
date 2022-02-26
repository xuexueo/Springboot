package cn.wn.repository;

import cn.wn.pojo.Book;
import cn.wn.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

//索引的操作对象，继承
public interface GoodsRepository extends ElasticsearchRepository<Goods,String> {
//自定义条件查询方法，这里使用的是命名查询，方法名以findBy开头，后面跟进的就是查询的属性
    List<Goods> findByName(String bookname);
}
