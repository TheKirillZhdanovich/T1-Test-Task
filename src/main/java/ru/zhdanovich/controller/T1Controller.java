package ru.zhdanovich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.zhdanovich.service.T1Service;

@RestController
@RequestMapping("${api.prefix}/v1")
public class T1Controller {
    @Autowired
    private T1Service service;

    @GetMapping("/get")
    public ResponseEntity<String> getResponse(@RequestParam("str") String str) {
        if (str.isEmpty()) {
            return ResponseEntity.badRequest().body("{str} param is empty");
        }

        return ResponseEntity.ok().body(service.parseString(str));
    }
}
