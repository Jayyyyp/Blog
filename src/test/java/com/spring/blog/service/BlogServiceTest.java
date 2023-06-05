package com.spring.blog.service;

import com.spring.blog.entity.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

    @Test
    @Transactional
    public void findByIdTest(){
        // given : 조회할 번호인 2번 요소 변수에 저장, 예상되는 글쓴이, 본문정보 저장
        long blogId = 2;
        String writer = "2번유저";
        String blogContent = "2번본문";

        // when
        Blog blog = blogService.findById(blogId);

        // then
        assertEquals(blogId, blog.getBlogId());
        assertEquals(writer, blog.getWriter());
        assertEquals(blogContent, blog.getBlogContent());
    }

    @Test
    @Transactional
    public void deleteByIdTest(){
        // given
        long blogId = 2;

        // when
        blogService.deleteById(blogId);

        // then
        assertEquals(2, blogService.findAll().size());
        assertNull(blogService.findById(blogId));
    }

    @Test
    @Transactional
    public void saveTest(){
        // given
        String writer = "2번유저";
        String blogTitle = "2번제목";
        String blogContent = "2번본문";
        int blogId = 3;

        Blog blog = Blog.builder()
                .writer(writer)
                .blogTitle(blogTitle)
                .blogContent(blogContent)
                .build();

        // when
        blogService.save(blog);

        // then
        assertEquals(4, blogService.findAll().size());
        assertEquals(writer, blogService.findAll().get(blogId).getWriter());
        assertEquals(blogTitle, blogService.findAll().get(blogId).getBlogTitle());
        assertEquals(blogContent, blogService.findAll().get(blogId).getBlogContent());
    }

    @Test
    @Transactional
    public void updateTest(){
        // given
        String blogTitle = "수정된제목";
        String blogContent = "수정된본문";
        long blogId = 2;

        Blog blog = Blog.builder()
                .blogId(blogId)
                .blogTitle(blogTitle)
                .blogContent(blogContent)
                .build();

        // when
        blogService.update(blog);

        // then
        assertEquals(blogId, blogService.findById(blogId).getBlogId());
        assertEquals(blogTitle, blogService.findById(blogId).getBlogTitle());
        assertEquals(blogContent, blogService.findById(blogId).getBlogContent());
    }
}
