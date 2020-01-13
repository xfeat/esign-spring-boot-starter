package cn.ocoop.framework.esign.sign.signflow;

import lombok.Data;

@Data
public class SignflowResponse1 {

    private int code;
    private String message;

    public boolean success() {
        return 0 == code;
    }

    @Data
    public static class Signfield {
        private Integer actorIndentityType;
        private Long addTime;
        private Boolean assignedPosbean;
        private Boolean assignedSeal;
        private String authorizedAccountId;
        private Boolean autoExecute;
        private String executeFailedReason;
        private String fileId;
        private String flowId;
        private Integer order;
        private PosBean posBean;
        private String sealFileKey;
        private String sealId;
        private String sealType;
        private Integer signType;
        private String signerAccountId;
        private String signfieldId;
        private Integer status;
        private String statusDescription;

        @Data
        public static class PosBean {
            private String posPage;
            private Float posX;
            private Float posY;
            private Float width;
        }
    }
}
