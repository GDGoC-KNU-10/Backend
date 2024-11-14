package com.hackathon.nullnullteam.ocr;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OcrService {

    private String secretKey = "aXlVdkxtbHlHd2ppZ3pDaE9EVHVYTmlsaFhFemhSQUY=";

    private String secretId = "sadaq";

    private String ocrUrl = "https://ahkbcepidb.apigw.ntruss.com/custom/v1/35950/126a9919713e051ba28c70a4e4dc6d2d4df3a2d21801b5ffa7ab91cc5aa3886c/general\n";

    private final RestTemplate restTemplate = new RestTemplate();

    public String extractTextFromImage(MultipartFile imageFile) {
        try {
            // 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.set("X-OCR-SECRET", secretKey);

            // 파일을 바디로 추가
            HttpEntity<MultipartFile> fileEntity = new HttpEntity<>(imageFile, headers);

            // HTTP 요청 생성
            Map<String, Object> body = new HashMap<>();
            body.put("images", Collections.singletonList(fileEntity));
            body.put("lang", "ko"); // 언어 설정 (예: "ko" 또는 "en")

            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            // OCR API로 요청
            ResponseEntity<Map> response = restTemplate.exchange(
                ocrUrl,
                HttpMethod.POST,
                requestEntity,
                Map.class
            );

            // 응답 데이터에서 텍스트 추출
            Map<String, Object> result = response.getBody();
            if (result != null && result.containsKey("images")) {
                Map<String, Object> images = (Map<String, Object>) ((Map<String, Object>) result.get("images")).get(0);
                return (String) images.get("text"); // 반환할 텍스트
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
