package com.imlinker.linker.news.presentation;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/news")
public class NewsController {

    @GetMapping("/test")
    public ResponseEntity<String> newsTest(){
        return ResponseEntity.ok("news api test");
    }
}
