package primitive;


public
class PrimitiveTypeName extends TypeName {
// The "short" names of primitive types are actually longer than the fully-qualified names, but it follows the
// general pattern: the "short" is the "human-friendly" representation of the name, whereas the fully-qualified
// name is the compiler-friendly version.

    public static PrimitiveTypeName Boolean = new PrimitiveTypeName("Z", "boolean");
    public static PrimitiveTypeName Int = new PrimitiveTypeName("I", "int");
    public static PrimitiveTypeName Float = new PrimitiveTypeName("F", "float");
    public static PrimitiveTypeName Void = new PrimitiveTypeName("V", "void");
    public static PrimitiveTypeName Byte = new PrimitiveTypeName("B", "byte");
    public static PrimitiveTypeName Char = new PrimitiveTypeName("C", "char");
    public static PrimitiveTypeName Short = new PrimitiveTypeName("S", "short");
    public static PrimitiveTypeName Long = new PrimitiveTypeName("J", "long");
    public static PrimitiveTypeName Double = new PrimitiveTypeName("D", "double");

    PrimitiveTypeName(String fullyQualified, String shortName) {
        setFullyQualified(fullyQualified);
        setShortName(shortName);
    }

    static PrimitiveTypeName ForPrimitiveTypeSignature(String signature) {
        switch (signature) {
            case "Z":
                return Boolean;
            case "B":
                return Byte;
            case "C":
                return Char;
            case "S":
                return Short;
            case "I":
                return Int;
            case "J":
                return Long;
            case "F":
                return Float;
            case "D":
                return Double;
            case "V":
                return Void;
            default:
                return null;
        }
    }
}
