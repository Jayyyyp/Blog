package com.spring.blog.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // @Mapper로 적재된 마이바티스 인터페이스 인식을 위한 어노테이션
public class ConnectionTestRepositoryTest {
    @Autowired
    ConnectionTestRepository connectionTestRepository;

    @Test
    public void getNowTest(){
        System.out.println("얻어온 현재 시간 : " + connectionTestRepository.getNow());
    }
}
