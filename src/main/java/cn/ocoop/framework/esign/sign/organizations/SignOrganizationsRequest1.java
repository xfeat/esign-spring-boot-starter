package cn.ocoop.framework.esign.sign.organizations;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignOrganizationsRequest1 {
    private String thirdPartyUserId;
    private String name;
    private String idType;
    private String idNumber;
}
