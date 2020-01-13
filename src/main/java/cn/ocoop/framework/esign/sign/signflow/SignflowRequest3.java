package cn.ocoop.framework.esign.sign.signflow;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SignflowRequest3 {

    private List<SignflowRequest3.Signfield> signfields;

    @Data
    @Builder
    public static class Signfield {
        private String fileId;
        private String authorizedAccountId;
        private Integer order;
        private SignflowRequest3.Signfield.PosBean posBean;
        private String sealId;
        private Integer signType;
        private Integer thirdOrderNo;

        @Data
        @Builder
        public static class PosBean {
            private String posPage;
            private Float posX;
            private Float posY;
            private Float width;
            private Boolean addSignTime;
        }
    }
}
