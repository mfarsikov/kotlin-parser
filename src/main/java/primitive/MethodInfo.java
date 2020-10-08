package primitive;


import java.util.ArrayList;
import java.util.List;

public class MethodInfo implements ICodebaseElementInfo {
    public MethodName MethodName;

    @Override
    public CodebaseElementName getName() {
        return MethodName;
    }

    @Override
    public SourceCodeSnippet getSourceCode() {
        return SourceCode;
    }

    @Override
    public List<ICodebaseElementInfo> getChildren() {
        return new ArrayList<>();
    }

    @Override
    public List<CodeReferenceEndpoint> getReferencesToThis() {
        return ReferencesToThis;
    }

    @Override
    public List<CodeReferenceEndpoint> getReferencesFromThis() {
        return ReferencesFromThis;
    }

    public AccessFlags accessFlags;

    /// <summary>
    /// The most concrete class owning this method
    /// </summary>
    public ClassName ParentClass;

    public Argument[] Arguments;
    public TypeName ReturnType;

    public FieldInfo Field;

    public List<CodeReferenceEndpoint> ReferencesToThis;
    public List<CodeReferenceEndpoint> ReferencesFromThis;

    SourceCodeSnippet SourceCode;

    public MethodInfo(
            MethodName methodName,
            AccessFlags accessFlags,
            ClassName parentClass,
            List<Argument> arguments,
            TypeName returnType,
            SourceCodeSnippet sourceCode) {
        MethodName = methodName;
        this.accessFlags = accessFlags;
        ParentClass = parentClass;

        Arguments = new Argument[arguments.size()];
        Arguments = arguments.toArray(Arguments);
        ReturnType = returnType;

        Field = null;
        ReferencesToThis = new ArrayList<>();
        ReferencesFromThis = new ArrayList<>();

        SourceCode = sourceCode;
    }
}
