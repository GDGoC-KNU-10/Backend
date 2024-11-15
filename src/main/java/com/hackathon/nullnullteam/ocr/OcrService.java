package com.hackathon.nullnullteam.ocr;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
@Service
public class OcrService {

    private String secretKey = "aXlVdkxtbHlHd2ppZ3pDaE9EVHVYTmlsaFhFemhSQUY=";
    private String ocrUrl = "https://ahkbcepidb.apigw.ntruss.com/custom/v1/35950/126a9919713e051ba28c70a4e4dc6d2d4df3a2d21801b5ffa7ab91cc5aa3886c/general\n";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<String> extractTextFromImage(MultipartFile file) {
        // 헤더 설정: X-OCR-SECRET 및 Content-Type 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("X-OCR-SECRET", secretKey);

        // 메시지 데이터 구성
        Map<String, Object> message = new HashMap<>();
        message.put("version", "V2"); // 권장값
        message.put("requestId", UUID.randomUUID().toString()); // UUID 생성
        message.put("timestamp", System.currentTimeMillis() / 1000); // 초 단위 타임스탬프
        message.put("lang", "ko"); // 요청 언어
        //message.put("enableTableDetection", true); // 테이블 인식 활성화

        // images 데이터 구성
        Map<String, Object> imageInfo = new HashMap<>();
        imageInfo.put("format", "jpg"); // 이미지 형식
        imageInfo.put("name", file.getOriginalFilename()); // 이미지 이름
        imageInfo.put("data", encodeFileToBase64(file)); // Base64 인코딩된 이미지 데이터

        List<Map<String, Object>> images = new ArrayList<>();
        images.add(imageInfo);
        message.put("images", images); // message에 images 정보 추가

        // 메시지 전체 데이터
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("message", message);

        // 파일을 multipart/form-data 형식으로 추가 (MultipartFile을 그대로 추가)
        map.add("file", file.getResource());

        // HttpEntity로 요청 메시지 구성
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);


        // RestTemplate 설정
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new org.springframework.http.converter.FormHttpMessageConverter());

        ResponseEntity<String> response = restTemplate.exchange(ocrUrl, HttpMethod.POST, requestEntity, String.class);
        JSONObject object = new JSONObject(response.getBody());
        List<String> text = extractInferText(object);

        StringBuilder sb = new StringBuilder();
        for (String t : text) {
            sb.append(t + " ");
        }
        System.out.println(sb.toString());
        return text;
    }

    private String encodeFileToBase64(MultipartFile file) {
        try {
            byte[] fileBytes = file.getBytes(); // 파일 내용을 바이트 배열로 읽기
            return Base64.getEncoder().encodeToString(fileBytes); // Base64 인코딩
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("파일을 Base64로 변환하는 중 오류 발생: " + e.getMessage());
        }
    }

    public static List<String> extractInferText(JSONObject response) {
        List<String> inferTexts = new ArrayList<>();

        // 'images' 배열을 가져옴
        JSONArray images = response.getJSONArray("images");

        for (int i = 0; i < images.length(); i++) {
            JSONObject image = images.getJSONObject(i);

            // 각 이미지에서 'fields' 배열을 가져옴
            JSONArray fields = image.getJSONArray("fields");

            for (int j = 0; j < fields.length(); j++) {
                JSONObject field = fields.getJSONObject(j);

                // 각 'field'에서 inferText 추출
                String inferText = field.getString("inferText");

                // 추출한 inferText를 리스트에 추가
                inferTexts.add(inferText);
            }
        }

        return inferTexts;
    }
}
