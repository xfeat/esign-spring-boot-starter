package cn.ocoop.framework.esign.identity.auth.individual.bank;

import lombok.Data;

@Data
public class IdBankCardResponse1 {
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
