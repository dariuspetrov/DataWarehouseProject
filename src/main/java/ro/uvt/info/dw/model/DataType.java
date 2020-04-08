package ro.uvt.info.dw.model;

public enum DataType {
    STRING(String.class),
    INTEGER(Integer.class),
    DECIMAL(Double.class),
    BOOLEAN(Boolean.class),
    TIMESTAMP(java.util.Date.class);

    private Class javaType;

    public static DataType valueOf(int ordinal) {
        for (DataType dt : values()) {
            if (dt.ordinal() == ordinal) {
                return dt;
            }
        }
        return null;
    }

    DataType(Class javaType) {
        this.javaType = javaType;
    }
}