package com.news.service;

import com.news.entity.News;
import com.news.entity.dto.NewsDto;

import java.util.List;

public interface NewsService {
    public NewsDto saveNews(NewsDto newsDto);
    public List<NewsDto> getAllNews();
    public NewsDto getNewsById(Long id);
    public void updateNews(long id, NewsDto newsDto);
    NewsDto updateNews(Long id, NewsDto newsDto);
    void deleteNews(Long id);
}
