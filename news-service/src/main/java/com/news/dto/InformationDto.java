<<<<<<< HEAD
package com.news.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.news.entity.*;
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
        if(this.type.equals("news")){
            return modelMapper.map(this, News.class);
        }else if(this.type.equals("updates")){
            return modelMapper.map(this, Updates.class);
        }
        return modelMapper.map(this, Announcement.class);
    }
}
=======
package com.news.entity.dto;public class InformationDto {
}
>>>>>>> origin/news
