package whl.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity // 声明实体类
@Table(name = "cst_customer") // 配置实体和表的映射关系
public class Customer {

    @Id // 声明主键配置
    // 自增策略 strategy
    //      IDENTITY 自增：底层数据库必须支持自动增长 mysql
    //      SEQUENCE 序列：底层数据库必须支持序列 oracle
    //      TABLE：JPA提供的机制, 通过一张数据库表的形式完成主键自增, 也就是通过 JPA 实现主键自增
    // 这个机制会在执行完一条update语句后, hibernate会创建一张表用于保存下一次update时, 自增的主键
    //      AUTO：由程序自动选择主键生成策略
    @GeneratedValue(strategy = GenerationType.IDENTITY) //配置主键生成策略
    @Column(name = "cust_id") //属性与数据库表中的字段映射
    private Long custId;

    @Column(name = "cust_name")
    private String custName;

    @Column(name = "cust_level")
    private String custLevel;

    @Column(name = "cust_industry")
    private String custIndustry;

    @Column(name = "cust_phone")
    private String custPhone;

    @Column(name = "cust_address")
    private String custAddress;
}
