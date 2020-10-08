package primitive;

public enum CodeReferenceType {
    Undefined(-1),
    MethodCall(0),
    ClassInheritance(1),
    InterfaceImplementation(2),
    MethodOverride(3);

    private int value;

    CodeReferenceType(int i) {
        value = i;
    }

    public int getValue() { return value; }
}
