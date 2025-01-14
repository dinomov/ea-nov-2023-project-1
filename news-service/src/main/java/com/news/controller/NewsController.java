package com.news.controller;

import com.news.dto.NewsDto;
import com.news.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@AllArgsConstructor
public class NewsController {


    private final NewsService newsService;

    // Create a new news item
    @PostMapping
    public ResponseEntity<NewsDto> createNews(@RequestBody NewsDto newsDto) {
        NewsDto createdNews = newsService.saveNews(newsDto);
        return ResponseEntity.ok(createdNews);
    }

    // Get all news items
    @GetMapping
    public ResponseEntity<List<NewsDto>> getAllNews() {
        List<NewsDto> newsList = newsService.getAllNews();
        return ResponseEntity.ok(newsList);
    }

    // Get a single news item by ID
    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> getNewsById(@PathVariable Integer id) {
        NewsDto newsDto = newsService.getNewsById(id);
        return ResponseEntity.ok(newsDto);
    }

    // Update a news item
    @PutMapping("/{id}")
    public ResponseEntity<NewsDto> updateNews(@PathVariable Integer id, @RequestBody NewsDto newsDto) {
        NewsDto updatedNews = newsService.updateNews(id, newsDto);
        return ResponseEntity.ok(updatedNews);
    }

    // Delete a news item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Integer id) {
        newsService.deleteNews(id);
        return ResponseEntity.ok().build();
    }
}
