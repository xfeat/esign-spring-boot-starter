package cn.ocoop.framework.esign;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Collections;

public class EsignRestTemplateHelper {
    public static RestTemplate getTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(3000);
        httpRequestFactory.setConnectTimeout(3000);
        httpRequestFactory.setReadTimeout(5000);
        BufferingClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(httpRequestFactory);
        RestTemplate restTemplate = new RestTemplate(factory);
        for (HttpMessageConverter<?> httpMessageConverter : restTemplate.getMessageConverters()) {
            if (httpMessageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(Charset.forName("UTF-8"));
            }
            if (httpMessageConverter instanceof MappingJackson2HttpMessageConverter) {
                ((MappingJackson2HttpMessageConverter) httpMessageConverter).getObjectMapper()
                        .setSerializationInclusion(JsonInclude.Include.NON_NULL);
//                        .setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            }
        }
        restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));
        return restTemplate;
    }

    public static <T> T get(String url, Class<T> clzz, Object... uriVariables) {
        return exec(url, HttpMethod.GET, null, clzz, uriVariables);
    }

    public static <T> T post(String url, Object body, Class<T> clzz, Object... uriVariables) {
        return exec(url, HttpMethod.POST, body, clzz, uriVariables);
    }

    public static <T> T put(String url, Object body, Class<T> clzz, Object... uriVariables) {
        return exec(url, HttpMethod.PUT, body, clzz, uriVariables);
    }

    public static <T> T delete(String url, Class<T> clzz, Object... uriVariables) {
        return exec(url, HttpMethod.DELETE, null, clzz, uriVariables);
    }


    public static <T> T exec(String url, HttpMethod method, Object body, Class<T> clzz, Object... uriVariables) {
        HttpHeaders header = new HttpHeaders();
        header.add("X-Tsign-Open-App-Id", EsignAutoConfiguration.ctx.getBean(EsignProperties.class).getAppId());
        header.add("X-Tsign-Open-Token", EsignAutoConfiguration.ctx.getBean(AccessTokenService.class).get());
        HttpEntity<Object> httpEntity = new HttpEntity<>(body, header);
        return getTemplate().exchange(EsignAutoConfiguration.ctx.getBean(EsignProperties.class).api(url), method, httpEntity, clzz, uriVariables).getBody();
    }
}