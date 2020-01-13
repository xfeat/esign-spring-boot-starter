package cn.ocoop.framework.esign.sign.signflow;

import lombok.Data;

@Data
public class SignflowResponse5 {
    private int code;
    private String message;
    private SignflowResponse5.RetData data;

    public boolean success() {
        return 0 == code;
    }

    @Data
    public static class RetData {
        private String appId;
        private Boolean autoArchive;
        private String businessScene;
        private ConfigInfo configInfo;
        private Long contractValidity;
        private Integer contractRemind;
        private String flowId;
        private String flowStartTime;
        private String flowEndTime;
        private Integer flowStatus;
        private String flowDesc;
        private String initiatorAccountId;
        private String initiatorAuthorizedAccountId;
        private Long signValidity;

        @Data
        public static class ConfigInfo {
            private String noticeDeveloperUrl;
            private String noticeType;
            private String redirectUrl;
            private String signPlatform;
        }
    }

}
