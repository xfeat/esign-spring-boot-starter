package cn.ocoop.framework.esign.sign.signflow;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SignflowRequest1 {
    private List<Signfield> signfields;

    @Data
    @Builder
    public static class Signfield {
        private String fileId;
        private Integer order;
        private PosBean posBean;
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
