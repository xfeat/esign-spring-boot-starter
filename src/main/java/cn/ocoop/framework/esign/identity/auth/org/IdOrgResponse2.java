package cn.ocoop.framework.esign.identity.auth.org;

import lombok.Data;

@Data
public class IdOrgResponse2 {
    private int code;
    private String message;

    public boolean success() {
        return 0 == code;
    }

}
