package cn.ocoop.framework.esign.sign.template;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class SignTemplateRequest {
    private String name;
    private String templateId;
    private Map<String, String> simpleFormFields;
}
