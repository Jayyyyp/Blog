package com.spring.blog.entity;

import lombok.*;

import java.sql.Date;

// 역직렬화(디비 -> 자바객체)가 가능하도록 blog 테이블 구조에 맞춰 멤버변수 선언
@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor@Builder // 빌더패턴 생성자 사용가능
public class Blog {
    private long blogId; // 숫자는 어지간하면 long형으로!
    private String writer;
    private String blogTitle;
    private String blogContent;
    private Date publishedAt;
    private Date updatedAt;
    private long blogCount;
}
