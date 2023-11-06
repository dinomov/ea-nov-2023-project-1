package com.ea.project.controller;


import com.ea.project.service.InformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/information")
public class InformationController {
    @Autowired
    private final InformationService informationService;


}
