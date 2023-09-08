package xyz.andornot.vss.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("vss")
public class VectorSimilaritySearchController {

    @PutMapping("load")
    public ResponseEntity<String> load() {

        return ResponseEntity.ok("load success");
    }

    @GetMapping("search")
    public ResponseEntity<?> search() {
        return null;
    }
}
