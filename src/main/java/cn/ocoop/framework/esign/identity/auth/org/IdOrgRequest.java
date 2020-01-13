package cn.ocoop.framework.esign.identity.auth.org;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdOrgRequest {
    private String name;
    private String orgCode;
}
