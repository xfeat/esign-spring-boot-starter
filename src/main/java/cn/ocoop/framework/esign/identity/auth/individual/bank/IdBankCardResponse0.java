package cn.ocoop.framework.esign.identity.auth.individual.bank;

import lombok.Data;

@Data
public class IdBankCardResponse0 {
    private int code;
    private String message;

    public boolean success() {
        return 0 == code;
    }
}
