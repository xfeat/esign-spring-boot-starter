package cn.ocoop.framework.esign.identity.account;

import lombok.Data;

@Data
public class IdAccountResponse0 {
    private int code;
    private String message;
    private RetData data;

    public boolean success() {
        return 0 == code;
    }

    @Data
    public static class RetData {
        private String flowId;
        private String status;
        private String objectType;
        private Long startTime;
        private Long endTime;
        private String failReason;
        private String authType;
        private OrganInfo organInfo;
        private IndivInfo indivInfo;
    }

    @Data
    public static class OrganInfo {
        private String accountId;
        private String name;
        private String certNo;
        private String certType;
        private String legalRepName;
        private String legalRepNationality;
        private String legalRepCertNo;
        private String legalRepCertType;
    }

    @Data
    public static class IndivInfo {
        private String accountId;
        private String name;
        private String certNo;
        private String certType;
        private String nationality;
        private String mobileNo;
        private String bankCardNo;
        private String facePhotoUrl;

    }
}
