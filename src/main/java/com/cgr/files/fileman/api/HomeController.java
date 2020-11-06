package com.cgr.files.fileman.api;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HomeController {

    @Autowired
    private AmazonS3 s3;

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> home(@RequestParam(defaultValue = "World") String name) {
        Map<String, Object> map = new HashMap<>();

        map.put("msg", String.format("Hello %s!!! Here are the buckets: %s", name,
                s3.listBuckets().stream().map(Bucket::getName).collect(Collectors.joining("; "))));
        log.info("{}", map);
        return ResponseEntity.ok().body(map);
    }

}
