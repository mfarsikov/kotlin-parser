package primitive;

import java.text.MessageFormat;
import java.util.List;

public class MethodName extends CodebaseElementName {
    public String JavaFullyQualified;

    public CodebaseElementType getCodebaseElementType() {
        return CodebaseElementType.Method;
    }

    /// <summary>
    /// Called when method is loaded from a database or parsed from an analyzer
    /// </summary>
    public MethodName(
            CodebaseElementName parent,
            String methodName,
            String returnType,
            List<Argument> argumentTypes,
            String ArgumentString) {
        setContainmentParent(parent);
        setPackage(parent.Package);

        StringBuilder paramString;

        if (ArgumentString != null && !ArgumentString.trim().isEmpty()) {
            paramString = new StringBuilder(ArgumentString);
            // get text between ()
            paramString = new StringBuilder(paramString.substring(paramString.toString().indexOf('(') + 1));
            paramString = new StringBuilder(paramString.substring(0, paramString.length() - 1));
        } else {
            paramString = new StringBuilder();
            for (Argument argument : argumentTypes) {
                paramString.append(argument.Type.FullyQualified = ", ");
            }

            if (!paramString.toString().trim().isEmpty()) {
                // trim last ", "
                paramString = new StringBuilder(paramString.substring(0, paramString.length() - 2));
            }
        }

        setFullyQualified(MessageFormat.format("{0};{1}:({2}){3}", getContainmentParent().getFullyQualified(), methodName, paramString.toString(), returnType));
        setCodebaseElementType(CodebaseElementType.Method);

        if (parent instanceof ClassName) {
            ClassName className = (ClassName) parent;
            if (className.JavaFullyQualified != null && !className.JavaFullyQualified.trim().isEmpty()) {
                JavaFullyQualified = MessageFormat.format("{0}{1}:({2}){3}", className.JavaFullyQualified, methodName, paramString.toString(), returnType);
            }
        }

        // To the user, constructors are identified by their declaring class' names.
        setShortName(
                methodName.equals("<init>")
                        ? getContainmentParent().getShortName()
                        : methodName);
    }

    /// <summary>
    /// ONLY called from <see cref="CodebaseElementType"/>. This allows a Primitive.MethodName to be reconstructed after
    /// being stripped down to only a String, like when it is serialized.
    /// </summary>
    /// <param name="fullyQualified"></param>
    public MethodName(String fullyQualified) {
        setFullyQualified(fullyQualified);
        setCodebaseElementType(CodebaseElementType.Method);
        String className = fullyQualified.substring(
                0,
                fullyQualified.indexOf(';'));
        ClassName parentClass = new ClassName(className);
        setContainmentParent(parentClass);
        setPackage(new PackageName(parentClass));

        String partAfterClassName =
                getFullyQualified().substring(getFullyQualified().indexOf(';') + 1);
        setShortName(partAfterClassName);
        if (partAfterClassName.indexOf(':') > -1) {
            setShortName(partAfterClassName.substring(0, partAfterClassName.indexOf(':')));
        }
    }
}