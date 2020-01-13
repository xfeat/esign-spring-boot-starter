package cn.ocoop.framework.esign.sign.organizations;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignOrganizationsRequest0 {
    private String name;
    private String idType;
    private String idNumber;
    private String orgLegalIdNumber;
    private String orgLegalName;
}
