package cn.ocoop.framework.esign.identity.account;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdAccountRequest0 {
    private String creator;
    private String idNumber;
    private String idType;
    private String name;
    private String orgLegalName;
    private String orgLegalIdNumber;
}
