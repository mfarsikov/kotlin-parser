package primitive;

public enum CodebaseElementType {
    Unknown (-1),
    Field (0),
    Method (1),
    Class (2),
    Package (3),
    File (4);
    private final int value;

    CodebaseElementType(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}

