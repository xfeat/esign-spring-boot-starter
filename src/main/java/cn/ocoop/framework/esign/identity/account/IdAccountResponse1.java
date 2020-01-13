package cn.ocoop.framework.esign.identity.account;

import lombok.Data;

@Data
public class IdAccountResponse1 {

    private int code;
    private String message;
    private RetData data;

    public boolean success() {
        return 0 == code;
    }

    public boolean exist() {
        return 53000000 == code;
    }


    @Data
    public static class RetData {
        private String accountId;
        private String orgId;
    }
}
