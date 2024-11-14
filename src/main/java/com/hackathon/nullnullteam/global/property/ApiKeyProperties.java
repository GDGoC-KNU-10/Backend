package com.hackathon.nullnullteam.global.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("apikey")
public record ApiKeyProperties(
        String secretkey,
        String vitaminInfoUrl
) {
}
