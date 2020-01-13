package cn.ocoop.framework.esign.identity.account;

import cn.ocoop.framework.esign.EsignRestTemplateHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IdAccountService {

    /**
     * 创建e签宝个人账号
     *
     * @param factors
     * @return
     */
    public IdAccountResponse create(IdAccountRequest factors) {
        return EsignRestTemplateHelper.post(
                "/v1/accounts",
                factors, IdAccountResponse.class);
    }

    /**
     * 创建e签宝组织机构账号
     *
     * @param factors
     * @return
     */
    public IdAccountResponse1 createOrg(IdAccountRequest0 factors) {
        return EsignRestTemplateHelper.post(
                "/v1/organizations",
                factors, IdAccountResponse1.class);
    }

    /**
     * 查询认证信息
     *
     * @param flowId
     * @return
     */
    public IdAccountResponse0 getAuthInfo(String flowId) {
        return EsignRestTemplateHelper.get(
                "/v2/identity/auth/api/common/{flowId}/detail",
                IdAccountResponse0.class,
                flowId
        );
    }

}
