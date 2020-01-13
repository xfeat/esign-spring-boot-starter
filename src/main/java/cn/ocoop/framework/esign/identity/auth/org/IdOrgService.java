package cn.ocoop.framework.esign.identity.auth.org;

import cn.ocoop.framework.esign.EsignRestTemplateHelper;
import org.springframework.stereotype.Service;

@Service
public class IdOrgService {

    /**
     * 企业2要素信息比对
     *
     * @param factors
     * @return
     */
    public IdOrgResponse verify(IdOrgRequest factors) {
        return EsignRestTemplateHelper.post(
                "/v2/identity/verify/organization/enterprise/base",
                factors, IdOrgResponse.class);
    }

    /**
     * 企业3要素信息比对
     *
     * @param factors
     * @return
     */
    public IdOrgResponse verify3(IdOrgRequest4 factors) {
        return EsignRestTemplateHelper.post(
                "/v2/identity/verify/organization/enterprise/bureau3Factors",
                factors, IdOrgResponse.class);
    }

    /**
     * 企业4要素信息比对
     *
     * @param factors
     * @return
     */
    public IdOrgResponse verify4(IdOrgRequest0 factors) {
        return EsignRestTemplateHelper.post(
                "/v2/identity/verify/organization/enterprise/bureau4Factors",
                factors, IdOrgResponse.class);
    }

    /**
     * 发起企业实名认证4要素校验
     *
     * @param factors
     * @return
     */
    public IdOrgResponse0 startVerify4(String accountId, IdOrgRequest1 factors) {
        return EsignRestTemplateHelper.post(
                "/v2/identity/auth/api/organization/enterprise/{accountId}/fourFactors",
                factors, IdOrgResponse0.class, accountId);
    }

    /**
     * 发起企业实名认证3要素校验
     *
     * @param factors
     * @return
     */
    public IdOrgResponse0 startVerify3(String accountId, IdOrgRequest1 factors) {
        return EsignRestTemplateHelper.post(
                "/v2/identity/auth/api/organization/{accountId}/threeFactors",
                factors, IdOrgResponse0.class, accountId);
    }

    /**
     * 查询打款银行信息
     *
     * @param flowId
     * @param keyWord
     * @return
     */
    public IdOrgResponse1 subbranch(String flowId, String keyWord) {
        return EsignRestTemplateHelper.get(
                "/v2/identity/auth/pub/organization/{flowId}/subbranch?keyWord={keyWord}",
                IdOrgResponse1.class, flowId, keyWord);
    }


    /**
     * 发起随机金额打款认证
     *
     * @param flowId
     * @param request
     * @return
     */
    public IdOrgResponse2 transferRandomAmount(String flowId, IdOrgRequest2 request) {
        return EsignRestTemplateHelper.put(
                "/v2/identity/auth/pub/organization/{flowId}/transferRandomAmount",
                request,
                IdOrgResponse2.class, flowId);
    }


    /**
     * 随机打款金额校验
     *
     * @param flowId
     * @param request
     * @return
     */
    public IdOrgResponse2 verifyRandomAmount(String flowId, IdOrgRequest3 request) {
        return EsignRestTemplateHelper.put(
                "/v2/identity/auth/pub/organization/{flowId}/verifyRandomAmount",
                request,
                IdOrgResponse2.class, flowId);
    }
}
