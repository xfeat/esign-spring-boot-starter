package cn.ocoop.framework.esign.sign.organizations;

import lombok.Data;

@Data
public class SignOrganizationsResponse1 {
    private int code;
    private String message;

    public boolean success() {
        return 0 == code;
    }

    public boolean notExist() {
        return 53000001 == code;
    }
}
