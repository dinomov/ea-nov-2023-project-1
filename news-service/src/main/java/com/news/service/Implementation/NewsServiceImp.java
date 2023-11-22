package com.news.service;

import com.news.entity.News;
import com.news.dto.NewsDto;
import com.news.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImp implements NewsService {

    private final NewsRepository newsRepository;
    private final ModelMapper modelMapper;



    @Override
    public NewsDto saveNews(NewsDto newsDto) {
        News news = modelMapper.map(newsDto, News.class);
        News savedNews = newsRepository.save(news);
        return modelMapper.map(savedNews, NewsDto.class);
    }

    @Override
    public List<NewsDto> getAllNews() {
        List<NewsDto> newsDtoList = new ArrayList<>();
        newsRepository.findAll().forEach(n -> newsDtoList.add(modelMapper.map(n, NewsDto.class)));
        return newsDtoList;
    }

    @Override
    public NewsDto getNewsById(Long id) {
        News news = newsRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found with id " + id));
        return modelMapper.map(news, NewsDto.class);
    }

    @Override
    public void updateNews(long id, NewsDto newsDto) {

    }



    @Override
    public NewsDto updateNews(Long id, NewsDto newsDto) {
        if (!newsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found with id: " + id);
        }
        News news = modelMapper.map(newsDto, News.class);
        news.setId(id);
        News savedNews = newsRepository.save(news);
        return modelMapper.map(savedNews, NewsDto.class);
    }

    @Override
    public void deleteNews(Long id) {
        if (!newsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found with id " + id);
        }
        try {
            newsRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
