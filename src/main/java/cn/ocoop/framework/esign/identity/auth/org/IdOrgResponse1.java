package cn.ocoop.framework.esign.identity.auth.org;

import lombok.Data;

import java.util.List;

@Data
public class IdOrgResponse1 {
    private int code;
    private String message;
    private RetData data;

    public boolean success() {
        return 0 == code;
    }

    @Data
    public static class RetData {
        private List<Data0> list;

        @Data
        public static class Data0 {
            private String bank;
            private String bankName;
            private String cnapsCode;
            private String province;
            private String city;
        }
    }
}
