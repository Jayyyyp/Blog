package com.spring.blog.service;

import com.spring.blog.entity.Blog;

import java.util.List;

public interface BlogService {
    // 비즈니스 로직을 담당할 메서드를 "정의"만 하면 된다.
    // 전체 블로그 포스팅을 조회하는 메서드 findAll()을 선언하기
    List<Blog> findAll();
}
