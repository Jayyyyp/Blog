package com.spring.blog.controller;

import com.spring.blog.entity.Blog;
import com.spring.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller // 빈 등록 기능, url 매핑 처리 기능을 함께 갖고있으므로,
            // 다른 어노테이션과 교환해서 사용할 수 없다.
@RequestMapping("/blog")
public class BlogController {

    // 컨트롤러 레이어는 서비스 레이어를 직접 호출한다.

    private BlogService blogService;
    @Autowired
    public BlogController(BlogService blogService){
        this.blogService = blogService;
    }

    // /blog/list 주소로 get 방식 접속했을때
    // 1. 서비스객체를 이용하여 게시글 전체를 얻어오기
    // 2. 얻어온 게시글을 .jsp로 보낼수 있게 적재
    // 3. .jsp에서 볼 수 있게 출력
    // 해당 파일의 이름은 blog/list.jsp
    @RequestMapping("/list")
    public String list(Model model){
        List<Blog> blogList = blogService.findAll();
        model.addAttribute("blogList", blogList);
        return "blog/list";
    }
}
