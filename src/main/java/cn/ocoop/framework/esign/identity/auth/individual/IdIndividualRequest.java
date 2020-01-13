package cn.ocoop.framework.esign.identity.auth.individual;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdIndividualRequest {
    private String idNo;
    private String name;
}
