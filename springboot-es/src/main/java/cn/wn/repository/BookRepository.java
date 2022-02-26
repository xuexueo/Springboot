package cn.wn.repository;

import cn.wn.pojo.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

//索引的操作对象，继承
//里面提供了分页的方法，添加删除的方法
public interface BookRepository extends ElasticsearchRepository<Book,Long> {
//自定义条件查询方法，这里使用的是命名查询，方法名以findBy开头，后面跟进的就是查询的属性
    List<Book> findByBookname(String bookname);
}
