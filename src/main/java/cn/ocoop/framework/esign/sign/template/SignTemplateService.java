package cn.ocoop.framework.esign.sign.template;

import cn.ocoop.framework.esign.EsignRestTemplateHelper;
import org.springframework.stereotype.Service;

@Service
public class SignTemplateService {

    /**
     * 通过模板创建文件
     *
     * @param request
     * @return
     */
    public SignTemplateResponse create(SignTemplateRequest request) {
        return EsignRestTemplateHelper.post("/v1/files/createByTemplate", request, SignTemplateResponse.class);
    }

    /**
     * 查询模板详情
     *
     * @param templateId
     * @return
     */
    public SignTemplateResponse0 detail(String templateId) {
        return EsignRestTemplateHelper.get("/v1/docTemplates/{templateId}", SignTemplateResponse0.class, templateId);
    }

}
