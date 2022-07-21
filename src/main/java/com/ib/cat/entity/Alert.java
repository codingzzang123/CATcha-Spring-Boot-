package com.ib.cat.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@DynamicInsert
public class Alert {

    @Id
    @SequenceGenerator(name="alert", sequenceName="SEQ_ALERT", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="alert")
    private Integer no;

    @Column(name="sub_name")
    private String subName; //action을 받는 사람

    @Column(name="pub_name")
    private String pubName; //좋아요,댓글 action을 취한 User

    private Integer bno;
    private Integer code;
    private Timestamp regdate;

//    @Column(name="reply_code") /* 나중에 추가 될 가능성 있음 */
//    private Integer replyCode;
}
