package cn.ocoop.framework.esign.identity.auth.individual.telecom;

import lombok.Data;

@Data
public class IdTelecomResponse0 {
    private int code;
    private String message;

    public boolean success() {
        return 0 == code;
    }
}
