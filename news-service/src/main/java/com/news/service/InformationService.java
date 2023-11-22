package com.news.service;

import com.news.dto.InformationDto;

import java.util.List;

public interface InformationService {
    //add
    void create(InformationDto information);

    //del
    void delete(int id);

    //change
    void update(InformationDto information);

    //search
    List<InformationDto> findAll();

    InformationDto findById(int id);
}
