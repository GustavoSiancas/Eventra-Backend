package com.example.demo.controller.data;

import com.example.demo.controller.data.response.GeneralDataResponse;
import com.example.demo.service.ExtraDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/data")
public class DataController {
    @Autowired
    private ExtraDataService extraDataService;

    @GetMapping("/{id}")
    public ResponseEntity<GeneralDataResponse> getData(@PathVariable Long id) {
        return ResponseEntity.ok(extraDataService.getAllExtraDatabyId(id));
    }
}
