package cn.ocoop.framework.esign.identity.auth.individual.telecom;

import lombok.Data;

@Data
public class IdTelecomResponse {
    private int code;
    private String message;
    private RetData data;

    public boolean success() {
        return 0 == code;
    }

    @Data
    public static class RetData {
        private String flowId;
    }
}
