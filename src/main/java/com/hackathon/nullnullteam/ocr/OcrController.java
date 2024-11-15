package com.hackathon.nullnullteam.ocr;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
    public List<String> extractText(@RequestParam("file") MultipartFile file) {
        List<String> extractedText = ocrService.extractTextFromImage(file);
        return extractedText;
    }
}
