package cn.ocoop.framework.esign.sign.account;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignAccountRequest {
    private String thirdPartyUserId;
    private String name;
    private String idType;
    private String idNumber;
    private String mobile;
    private String email;
}
