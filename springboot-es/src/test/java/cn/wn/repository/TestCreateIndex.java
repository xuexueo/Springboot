package cn.wn.repository;

import cn.wn.mapper.BookMapper;
import cn.wn.pojo.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestCreateIndex {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;

//将数据库查询的数据存入es
    @Test
    public void test(){
        List<Book> list=bookMapper.findAll();
        bookRepository.saveAll(list);

    }
}


