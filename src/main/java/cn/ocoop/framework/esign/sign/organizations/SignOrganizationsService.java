package cn.ocoop.framework.esign.sign.organizations;

import cn.ocoop.framework.esign.EsignRestTemplateHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SignOrganizationsService {

    /**
     * 机构账号创建
     *
     * @param request
     * @return
     */
    public SignOrganizationsResponse create(SignOrganizationsRequest request) {
        return EsignRestTemplateHelper.post(
                "/v1/organizations/createByThirdPartyUserId",
                request, SignOrganizationsResponse.class);
    }

    /**
     * 机构账号修改 按照账号ID修改
     *
     * @param request
     * @return
     */
    public SignOrganizationsResponse0 update(String orgId, SignOrganizationsRequest0 request) {
        return EsignRestTemplateHelper.put(
                "/v1/organizations/{orgId}",
                request, SignOrganizationsResponse0.class, orgId);
    }

    /**
     * 机构账号修改 按照第三方机构ID修改
     *
     * @param request
     * @return
     */
    public SignOrganizationsResponse0 updateByThirdId(String orgId, SignOrganizationsRequest1 request) {
        return EsignRestTemplateHelper.put(
                "/v1/organizations/{orgId}",
                request, SignOrganizationsResponse0.class, orgId);
    }

    /**
     * 查询机构账号 按照账号ID查询
     *
     * @param orgId
     * @return
     */
    public SignOrganizationsResponse0 get(String orgId) {
        return EsignRestTemplateHelper.get(
                "/v1/organizations/{orgId}",
                SignOrganizationsResponse0.class, orgId);
    }

    /**
     * 查询机构账号 按照第三方用户ID查询
     *
     * @param thirdPartyUserId
     * @return
     */
    public SignOrganizationsResponse0 getByThirdId(String thirdPartyUserId) {
        return EsignRestTemplateHelper.get(
                "/v1/organizations/getByThirdId?thirdPartyUserId={thirdPartyUserId}",
                SignOrganizationsResponse0.class, thirdPartyUserId);
    }

    /**
     * 注销机构账号 按照账号ID注销
     *
     * @param orgId
     * @return
     */
    public SignOrganizationsResponse1 delete(String orgId) {
        return EsignRestTemplateHelper.delete(
                "/v1/organizations/{orgId}",
                SignOrganizationsResponse1.class, orgId);
    }

    /**
     * 注销机构账号 按照账号ID注销
     *
     * @param thirdPartyUserId
     * @return
     */
    public SignOrganizationsResponse1 deleteByThirdId(String thirdPartyUserId) {
        return EsignRestTemplateHelper.delete(
                "/v1/organizations/deleteByThirdId?thirdPartyUserId={thirdPartyUserId}",
                SignOrganizationsResponse1.class, thirdPartyUserId);
    }
}
