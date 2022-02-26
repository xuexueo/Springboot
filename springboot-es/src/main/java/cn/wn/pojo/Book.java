package cn.wn.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

@Data
//@Documented注解标记的元素，Javadoc工具会将此注解标记元素的注解信息包含在javadoc中
@Document(indexName = "book")
public class Book {
    /*
    @Field(type=FieldType.Text, analyzer=“ik_max_word”) 表示该字段是一个文本，并作最大程度拆分，默认建立索引
    @Field(type=FieldType.Text,index=false) 表示该字段是一个文本，不建立索引
    @Field(type=FieldType.Date) 表示该字段是一个文本，日期类型，默认不建立索引
    @Field(type=FieldType.Long) 表示该字段是一个长整型，默认建立索引
    @Field(type=FieldType.Keyword) 表示该字段内容是一个文本并作为一个整体不可分，默认建立索引
    @Field(type=FieldType.Float) 表示该字段内容是一个浮点类型并作为一个整体不可分，默认建立索引,date 、float、long都是不能够被拆分的
    要使用 IK Analysis，需要在文档类里面，指定相应的分词器。我们在 EsBlog 的 tags 属性上，添加了searchAnalyzer = "ik_smart", analyzer = "ik_smart"的注解内容就可以了。
     */
    @Id
    private Long id;
    @Field(type= FieldType.Text,analyzer = "ik_max_word")
    private String bookname;
    @Field(type = FieldType.Keyword)
    private BigDecimal price;
    @Field(type = FieldType.Keyword)
    private String pic;
    @Field(type= FieldType.Text,analyzer = "ik_smart")
    private String bookdesc;
}
