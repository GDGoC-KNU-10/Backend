package com.hackathon.nullnullteam.ocr;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ocr")
public class OcrController {

    private final OcrService ocrService;

    @PostMapping("/extract-text")
    public ResponseEntity<String> extractText(@RequestParam("file") MultipartFile file) {
        String extractedText = ocrService.extractTextFromImage(file);
        if (extractedText != null) {
            return ResponseEntity.ok(extractedText);
        } else {
            return ResponseEntity.status(500).body("Failed to extract text.");
        }
    }
}
