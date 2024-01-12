package com.imlinker.coreapi.core.system;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorTriggerController {

    @GetMapping("/trigger")
    public void triggerError() {
        throw new RuntimeException("Error triggered");
    }
}
