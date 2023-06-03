package com.spring.blog.repository;

import com.spring.blog.entity.Blog;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // DROP 테이블 시 필요한 어노테이션
public class BlogRepositoryTest {
    @Autowired
    BlogRepository blogRepository;

    @BeforeEach // 각 테스트 전에 공통적으로 실행할 코드 저장하는 곳
    public void setBlogTable(){
        blogRepository.createBlogTable(); // blog 테이블 생성
        blogRepository.insertTestData(); // 생성된 blog 테이블에 더미데이터 3개 입력
    }
    @Test
    @DisplayName("전체행 얻어오고, 그 중 1번 인덱스 행만 추출하여 번호를 확인")
    public void findAllTest(){
        // given 2번 요소 조회를 위한 fixture
        int blogId = 1; // 자바 자료구조 인덱스는 0번부터

        // when : DB에 있는 모든 데이터를 자바 엔터티로 역직렬화
        List<Blog> blogList = blogRepository.findAll();

        // then : 더미데이터가 3개이므로, 3개일 것이라 단언하기
        assertEquals(3, blogList.size());
        // 2번째 객체의 아이디 번호는 2번일 것이다.
        assertEquals(2, blogList.get(blogId).getBlogId());
    }

    @Test
    @DisplayName("2번글 조회시, 제목과 글쓴이, 번호가 단언대로 받아와지는지 확인")
    public void findByIdTest(){
        // given : 조회할 db기준 2번 id를 변수로 저장
        int blogId = 2;
        // when : 레포지토리에서 단일행 Blog를 얻어와 저장
        Blog blog = blogRepository.findById(blogId);
        // then : 해당 객체의 writer 멤버변수는 "2번유저"이고, blogTitle은 "2번제목"이고 blogId는 2이다.
        assertEquals("2번유저",blog.getWriter());
        assertEquals("2번제목", blog.getBlogTitle());
        assertEquals(blogId, blog.getBlogId());
    }

    @Test
    @DisplayName("4번째 행 데이터 저장후, 행 저장여부 및 전달 데이터 저장 여부 확인")
    public void save(){
        // given : 저장을 위한 Blog entity 생성 및 writer, blogTitle, blogContent
        // 에 해당하는 fixture setter
        String writer = "추가할작가명";
        String blogTitle = "추가할제목명";
        String blogContnet = "추가할본문";

//        Blog blog = new Blog();
//        int blogId = 3; // 4번째 요소 조회
//        blog.setWriter(writer);
//        blog.setBlogTitle(blogTitle);
//        blog.setBlogContent(blogContnet);

        // blog 객체 생성코드를 빌더패턴으로 리팩토링(순서 상관없음)
        Blog blog = Blog.builder()
                .writer(writer)
                .blogTitle(blogTitle)
                .blogContent(blogContnet)
                .build();
        int blogId = 3;

        // when : save() 메서드 호출하고 findAll로 전체 데이터 가져오기
        blogRepository.save(blog);
        List<Blog> blogList = blogRepository.findAll();

        // then : 전체 데이터 개수가 4개인지
        // 방금 INSERT한 데이터의 writer, blogTitle, blogContent가 입력한대로 들어갔는지 단언문
        assertEquals(4, blogList.size());
        assertEquals(writer, blogList.get(blogId).getWriter());
        assertEquals(blogTitle, blogList.get(blogId).getBlogTitle());
        assertEquals(blogContnet, blogList.get(blogId).getBlogContent());
    }

    @Test
    @DisplayName("2번 삭제 후 전체 목록 가져왔을때, 남은 행수 2개, 삭제한 번호 재조회시, null")
    public void deleteByIdTest(){
        // given : 삭제할 자료의 번호
        long blogId = 2;

        // when : 삭제로직 실행 후, findAll(), findById()로 전체 행, 개별행 가져오기
        blogRepository.deleteById(blogId);

        // then : 전체 행 2개, 개별행은 null임을 확인
        assertEquals(2, blogRepository.findAll().size());
        assertNull(blogRepository.findById(blogId));

    }

    @Test
    @DisplayName("2번글의 제목을 '수정한제목'으로, 본문도 '수정한본문'으로 수정 후 확인하기")
    public void updateTest(){
        // given :
        //         1. 2번글 원본 데이터를 얻어온 후, blogTitle, blogContent 내용만 수정하여 다시 update()
        //         2. Blog 객체를 생성하여 blogId와 blogTitle, blogContent 내용만 setter로 주입하여 update()

        // 1번
        long blogId = 2;
        String blogTitle = "수정한제목";
        String blogContent = "수정한본문";

        Blog blog = blogRepository.findById(blogId);
        blog.setBlogTitle(blogTitle);
        blog.setBlogContent(blogContent);

        // when

        // then
    }

    @AfterEach // 각 단위테스트 끝난 후에 실행할 구문을 작성
    public void dropBlogTable(){
        blogRepository.dropBlogTable();; // blog 테이블 지우기
    }

}
