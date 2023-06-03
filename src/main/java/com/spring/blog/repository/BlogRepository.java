package com.spring.blog.repository;

import com.spring.blog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogRepository  {

    // 사전에 정의해야 하는 메서드들
    // 테이블생성
    void createBlogTable(); // 메서드 호출시 CREATE TABLE 구문 실행

    // 더미 데이터 입력
    void insertTestData(); // 더미 데이터로 3개 row 데이터 입력하기

    // 테이블 삭제
    void dropBlogTable(); // 단위 테스트 종료후 DB 초기화를 위해 테이블 삭제


    // 전체 데이터 조회 기능 findAll() 선언
    // Blog 엔터티 하나가 포스팅 row 하나를 받을 수 있고
    // n개의 복수의 Blog 엔터티를 받아  List로 감싸기
    List<Blog> findAll();

    // 단일 행 조회기능 findById()
    Blog findById(long blogId);

    // 새 데이터 저장기능 save()
    // 저장시, writer, blog_title, blog_content 3개 파라미터를 요구함
    // 3개 파라미터는 Blog 엔터티의 멤버변수
    void save(Blog blog);

    // 데이터 삭제기능 deleteById()
    void deleteById(long blogId);

    // 데이터 수정기능
    // JPA에서는 .save()를 동일하게 쓰지만 분리할 방법이 없어 메서드명을 다르게 사용
    void update(Blog blog);
}
