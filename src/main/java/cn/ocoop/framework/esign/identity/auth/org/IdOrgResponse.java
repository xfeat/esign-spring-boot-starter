package cn.ocoop.framework.esign.identity.auth.org;

import lombok.Data;

@Data
public class IdOrgResponse {
    private int code;
    private String message;
    private RetData data;

    public boolean success() {
        return 0 == code;
    }

    @Data
    public static class RetData {
        private String verifyId;
    }
}
