package pl.edu.agh.fis.commons;

/**
 * Created by wemstar on 2016-06-26.
 */
public enum HeaderType {
    DOCUMENT(0), TEMPLATE(1), APPLICATION(2);

    private int value;

    private HeaderType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static HeaderType getHederFromInt(int val) {
        switch (val) {
            case 0:
                return DOCUMENT;
            case 1:
                return TEMPLATE;
            case 2:
                return APPLICATION;
        }
        return DOCUMENT;
    }
}
