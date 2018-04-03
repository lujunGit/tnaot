package com.mongcent.tnaot.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static HttpHeaders getHeadersWithClientCredentials(String clientId, String secret) {
        String plainClientCredentials = clientId + ":" + secret;
        String base64ClientCredentials = new String(
                Base64.encodeBase64(plainClientCredentials.getBytes()));

        HttpHeaders headers = getHeaders();
        headers.add("Authorization", "Basic " + base64ClientCredentials);
        return headers;
    }

    public static HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }

    /**
     * @param getTokenUrl 获取token地址
     */
    public static String getToken(RestTemplate restTemplate, String getTokenUrl, String clientId, String
            secret) {
        Map<String, Object> responseBody = null;
        try {
            HttpEntity<String> request = new HttpEntity<String>(getHeadersWithClientCredentials(clientId, secret));
            responseBody = (Map<String, Object>) restTemplate
                    .exchange(getTokenUrl, HttpMethod.POST, request, Object.class).getBody();
            logger.info("response : " + responseBody);
        } catch (Exception e) {
            logger.error("获取token请求 : " + getTokenUrl + "出错！");
            e.printStackTrace();
        }
        return StringUtils.isEmpty(responseBody) ? null : responseBody.get("access_token").toString();
    }
}
