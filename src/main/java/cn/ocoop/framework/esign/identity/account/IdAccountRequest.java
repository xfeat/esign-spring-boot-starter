package cn.ocoop.framework.esign.identity.account;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdAccountRequest {
    private String cardNo;
    private String email;
    private String idNumber;
    private String idType;
    private String mobile;
    private String name;
}
