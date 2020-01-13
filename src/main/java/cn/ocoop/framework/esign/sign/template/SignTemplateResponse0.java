package cn.ocoop.framework.esign.sign.template;

import lombok.Data;

import java.util.List;

@Data
public class SignTemplateResponse0 {
    private int code;
    private String message;
    private RetData data;

    public boolean success() {
        return 0 == code;
    }

    @Data
    public static class RetData {
        private String templateId;
        private String templateName;
        private String downloadUrl;
        private Long fileSize;
        private Long createTime;
        private Long updateTime;
        private List<StructComponents> structComponents;

        @Data
        public static class StructComponents {
            private String id;
            private String key;
            private Integer type;
            private Context context;

            @Data
            public static class Context {
                private String label;
                private Boolean required;
                private String limit;
                private Style style;
                private Pos pos;

                @Data
                public static class Style {
                    private Float width;
                    private Float height;
                    private Integer font;
                    private Float fontSize;
                    private String textColor;
                }

                @Data
                public static class Pos {
                    private Integer page;
                    private Float x;
                    private Float y;
                }
            }

        }
    }

}
