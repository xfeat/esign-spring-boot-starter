package cn.ocoop.framework.esign.identity.auth.org;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdOrgRequest2 {
    private String bank;
    private String province;
    private String city;
    private String subbranch;
    private String cardNo;
    private String cnapsCode;
    private String contextId;
    private String notifyUrl;
}
