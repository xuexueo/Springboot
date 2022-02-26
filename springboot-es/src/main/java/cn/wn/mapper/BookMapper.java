package cn.wn.mapper;

import cn.wn.pojo.Book;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper {
    @Select("select * from book")
    List<Book> findAll();
}
