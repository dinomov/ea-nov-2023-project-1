package com.news.dto;

import java.time.LocalDateTime;

public class AnnouncementDto {
    private int id;
    private String author;
    private String content;
    private LocalDateTime updateDateTime;
    private LocalDateTime endDateTime;
    private String isUrgent;
}
