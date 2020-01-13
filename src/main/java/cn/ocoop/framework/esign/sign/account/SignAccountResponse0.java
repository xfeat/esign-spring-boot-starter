package cn.ocoop.framework.esign.sign.account;

import lombok.Data;

@Data
public class SignAccountResponse0 {
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
        private String thirdPartyUserId;
        private String name;
        private String idType;
        private String idNumber;
        private String mobile;
        private String email;
    }

}
