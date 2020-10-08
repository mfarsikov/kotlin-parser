package primitive;

public abstract class TypeName extends CodebaseElementName {
    public CodebaseElementType getCodebaseElementType() {
        return CodebaseElementType.Unknown;
    }

    public CodebaseElementName getContainmentParent() {
        return null;
    }

    public PackageName getPackage() {
        return new PackageName();
    }

    public static TypeName For(String signature) {
        if (signature.startsWith("[")) {
            return new ArrayTypeName(signature);
        }

        PrimitiveTypeName primitiveTypeName =
                PrimitiveTypeName.ForPrimitiveTypeSignature(signature);
        if (primitiveTypeName != null) {
            return primitiveTypeName;
        }

        return new ClassName(signature);
    }
}