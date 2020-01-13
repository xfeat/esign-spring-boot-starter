package cn.ocoop.framework.esign;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@Data
@RefreshScope
@ConfigurationProperties(prefix = EsignProperties.PREFIX)
public class EsignProperties {
    public static final String PREFIX = "esign";
    private String appId = "5111584279";
    private String secret = "6f13a82f436dceac254c3b4aaf153694";
    private String apiPrefix = "https://smlopenapi.esign.cn";

    public String api(String url) {
        return apiPrefix + url;
    }
}
