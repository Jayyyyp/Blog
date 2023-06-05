package com.spring.blog.service;

import com.spring.blog.entity.Blog;
import com.spring.blog.repository.BlogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BlogServiceTest {

    @Autowired
    BlogService blogService;

    @Test
    @Transactional // 이 테스트의 결과가 디비 커밋을 하지 않음
    public void findAllTest(){
        // given : 없음

        // when : 전체 데이터 가져오기
        List<Blog> blogList = blogService.findAll();
        // then : 길이가 3일 것이다.
        assertEquals(3, blogList.size());
    }
}
