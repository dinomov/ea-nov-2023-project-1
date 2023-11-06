package com.ea.project.dto;

import com.ea.project.entity.Announcement;
import com.ea.project.entity.Information;
import com.ea.project.entity.Replay;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InformationDto {
    @JsonProperty
    private int id;
    @JsonProperty
    private LocalDateTime updateDateTime;
    @JsonProperty
    private String content;
    @JsonProperty
    private String author;
    @JsonProperty

    private String type;
    @JsonProperty

    //announcement
    private LocalDateTime endDateTime;
    @JsonProperty
    private String isUrgent;

    //updates
    @JsonProperty
    private List<Replay> replays;

    //news
    @JsonProperty
    private String title;

    public Information getInformation(ModelMapper modelMapper) {

        return modelMapper.map(this, Announcement.class);
    }
}
