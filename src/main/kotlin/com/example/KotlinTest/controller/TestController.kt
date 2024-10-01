package com.example.KotlinTest.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
 @RequestMapping("/")  //: 이 클래스의 모든 핸들러 메서드에 대한 기본 URL 경로를 지정합니다.⭐️이것을 해야 기본 경로가 설정되어 hello가 잘나오게 됨!
class TestController {

    //HTTP GET 요청이 /posts 경로로 들어오면 이 메서드가 처리합니다.
    @GetMapping("hello")
    fun getPosts(): String {
        println("aa");
        return "hello world!!"
    }
}