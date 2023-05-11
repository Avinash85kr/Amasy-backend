package com.amasy.gtbackend.controllers;

import com.amasy.gtbackend.services.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BatchController {
    @Autowired
    private BatchService batchService;
}
