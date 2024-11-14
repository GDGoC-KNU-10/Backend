package com.hackathon.nullnullteam.member.infrastructure.apicaller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.nullnullteam.global.exception.AuthenticationException;
import com.hackathon.nullnullteam.global.property.KakaoProperties;
import com.hackathon.nullnullteam.member.infrastructure.apicaller.dto.TokenInfoResponse;
import com.hackathon.nullnullteam.member.infrastructure.apicaller.dto.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class MemberApiCaller {

    private final ObjectMapper objectMapper;
    private final KakaoProperties kakaoProperties;
    private final RestClient restClient;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    public String createCodeUrl() {
        String authUrl = "https://kauth.kakao.com/oauth/authorize";

        String url = UriComponentsBuilder.fromHttpUrl(authUrl)
                .queryParam("client_id", "27053b55aed58a9387699a86941543cd")
                .queryParam("redirect_uri", redirectUri)
                .queryParam("response_type", "code")
                .queryParam("scope", "account_email,name,gender,birthyear")
                .toUriString();

        return url;
    }

    public TokenInfoResponse getAccessToken(String code) {
        String tokenUrl = "https://kauth.kakao.com/oauth/token";
        LinkedMultiValueMap<String, String> body = createAccessBody(code);

        try {
            return restClient.post()
                    .uri(URI.create(tokenUrl))
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(body)
                    .exchange((request, response) -> {
                        if (response.getStatusCode().isSameCodeAs(HttpStatus.OK)) {
                            return objectMapper.readValue(response.getBody(), TokenInfoResponse.class);
                        }
                        throw new AuthenticationException("유효하지 않은 인가코드입니다.");
                    });
        } catch (ResourceAccessException e) {
            throw new AuthenticationException("네트워크 환경이 불안정합니다.");
        }
    }

    public LinkedMultiValueMap<String, String> createAccessBody(String code) {
        var body = new LinkedMultiValueMap<String, String>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "27053b55aed58a9387699a86941543cd");
        body.add("redirect_url", redirectUri);
        body.add("code", code);
        return body;
    }

    public UserInfoResponse extractUserInfo(String accessToken) {
        String userInfoUrl = "https://kapi.kakao.com/v2/user/me";

        return restClient.get()
                .uri(URI.create(userInfoUrl))
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .toEntity(UserInfoResponse.class)
                .getBody();
    }
}
