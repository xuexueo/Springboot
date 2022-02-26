package cn.wn.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "goods")
@Data
//用于链式编程
@Builder
//生成无参构造函数
@NoArgsConstructor
//生成全参数构造函数
@AllArgsConstructor
public class Goods {
    @Id
    private String id;
    @Field(type= FieldType.Text,analyzer = "ik_smart")
    private String name;
    //类型为keyword不粪池
    @Field(type=FieldType.Keyword)
    private String price;
    @Field(type=FieldType.Keyword)
    private String img;
}
