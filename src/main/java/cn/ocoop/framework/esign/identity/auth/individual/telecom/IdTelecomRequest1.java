package cn.ocoop.framework.esign.identity.auth.individual.telecom;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdTelecomRequest1 {
    private String name;
    private String idNo;
    private String mobileNo;
}
