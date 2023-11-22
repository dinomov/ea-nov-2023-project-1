package com.news.service;

import com.news.dto.NewsDto;

import java.util.List;

public interface NewsService {
    public NewsDto saveNews(NewsDto newsDto);
    public List<NewsDto> getAllNews();
    public NewsDto getNewsById(Integer id);

    public NewsDto updateNews(Integer id, NewsDto newsDto);

    public void deleteNews(Integer id);
}
