package cn.ocoop.framework.esign.sign.signflow;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignflowRequest {
    private Boolean autoArchive;
    private String businessScene;
    private ConfigInfo configInfo;
    private Long contractValidity;
    private Integer contractRemind;
    private Long signValidity;
    private String initiatorAccountId;
    private String initiatorAuthorizedAccountId;

    @Data
    @Builder
    public static class ConfigInfo {
        private String noticeDeveloperUrl;
        private String noticeType;
        private String redirectUrl;
        private String signPlatform;
    }
}
