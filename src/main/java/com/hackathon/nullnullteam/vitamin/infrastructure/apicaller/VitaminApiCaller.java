package com.hackathon.nullnullteam.vitamin.infrastructure.apicaller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.nullnullteam.global.property.ApiKeyProperties;
import com.hackathon.nullnullteam.vitamin.infrastructure.apicaller.dto.VitaminInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

@Component
@RequiredArgsConstructor
public class VitaminApiCaller {
    private final ObjectMapper objectMapper;
    private final RestClient restClient;
    private final ApiKeyProperties apiKeyProperties;

    public VitaminInfoResponse getVitaminInfo(String name) {
        try {
            // URI 구성
            String baseUrl = apiKeyProperties.vitaminInfoUrl();
            System.out.println("VitaminUrl = " + baseUrl);
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                    .queryParam("Prduct", URLEncoder.encode(name, "UTF-8"))
                    .queryParam("pageNo", "1")
                    .queryParam("numOfRows", "15")
                    .queryParam("serviceKey", apiKeyProperties.secretkey())
                    .queryParam("type", "json");

            // URL에서 + 기호를 %2B로 변환
            String encodedUrl = builder.build().toString();
            encodedUrl = encodedUrl.replaceAll("\\+", "%2B");
            URI finalUri = new URI(encodedUrl);

            // RestClient를 사용한 HTTP 요청 및 응답 처리
            String response = restClient.get()
                    .uri(finalUri)
                    .retrieve()
                    .body(String.class);

            if (response == null) {
                throw new RuntimeException("응답이 없습니다.");
            }

            return objectMapper.readValue(response, VitaminInfoResponse.class);

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("인코딩 오류 발생", e);
        } catch (URISyntaxException e) {
            throw new RuntimeException("URI 구문 오류 발생", e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON 파싱 오류 발생", e);
        } catch (RestClientException e) {
            throw new RuntimeException("API 호출 오류 발생: " + e.getMessage(), e);
        }
    }
}
