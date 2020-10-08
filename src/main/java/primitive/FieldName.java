package primitive;

public class FieldName extends CodebaseElementName {
// Suppose we have a field:
//
//   - Declared in "com.example.DeclaringClass"
//   - Named "fieldName"
//   - Has type of "java.lang.Object"
//
// The fully-qualified name would be:
//
// dir1/dir2/filename.ext|my.class.package.DeclaringClass;fieldName:java.lang.Object

    public CodebaseElementType CodebaseElementType() {
        return CodebaseElementType.Field;
    }

    public FieldName(String fullyQualified) {
        setFullyQualified(fullyQualified);
        setCodebaseElementType(CodebaseElementType.Field);
        String className = fullyQualified.substring(
                0,
                fullyQualified.indexOf(';'));
        setContainmentParent(new ClassName(className));
        Package = getContainmentParent().Package;

        // ...class;field:type -> field
        String fieldLongName = fullyQualified.substring(fullyQualified.indexOf(';') + 1);
        setShortName(fieldLongName);
        if (fieldLongName.indexOf(':') > -1) {
            setShortName(fieldLongName.substring(0, fieldLongName.indexOf(':')));
        }
    }
}