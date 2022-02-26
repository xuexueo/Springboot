package cn.wn.mapper;

import cn.wn.pojo.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
//从数据库查询数据，并输入控制台
public class test {
    @Autowired
    private BookMapper bookMapper;
    @Test
    public void test(){
        List<Book> list=bookMapper.findAll();
        list.forEach(System.out::println);

    }
}
