package primitive;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClassInfo implements ICodebaseElementInfo {
    @Override
    public CodebaseElementName getName() {
        return className;
    }

    @Override
    public SourceCodeSnippet getSourceCode()
    {
        return SourceCode;
    }

    public ClassName className;

    public AccessFlags accessFlags;

    public List<ClassInfo> InnerClasses() {
        return getChildren().stream()
                .filter(x -> x instanceof ClassInfo)
                .map(x -> (ClassInfo) x)
                .collect(Collectors.toList());
    }

    public Boolean IsTestClass;

    public List<MethodInfo> Methods() {
        return getChildren().stream()
                .filter(x -> x instanceof MethodInfo)
                .map(x -> (MethodInfo) x)
                .collect(Collectors.toList());
    }

    public List<FieldInfo> Fields() {
        return getChildren().stream()
                .filter(x -> x instanceof FieldInfo)
                .map(x -> (FieldInfo) x)
                .collect(Collectors.toList());
    }

    List<ICodebaseElementInfo> children;

    @Override
    public List<ICodebaseElementInfo> getChildren() {
        return children;
    }

    @Override
    public List<CodeReferenceEndpoint> getReferencesToThis() {
        return ReferencesToThis;
    }

    @Override
    public List<CodeReferenceEndpoint> getReferencesFromThis() {
        return ReferencesFromThis;
    }

    List<CodeReferenceEndpoint> ReferencesToThis;
    List<CodeReferenceEndpoint> ReferencesFromThis;
    SourceCodeSnippet SourceCode;

    public ClassInfo(
            ClassName className,
            List<MethodInfo> methods,
            List<FieldInfo> fields,
            AccessFlags accessFlags,
            List<ClassInfo> innerClasses,
            SourceCodeSnippet headerSource,
            Boolean isTestClass) {
        this.className = className;
        this.accessFlags = accessFlags;

        children = new ArrayList<>();
        children.addAll(fields);
        children.addAll(methods);
        children.addAll(innerClasses);

        ReferencesToThis = new ArrayList<>();
        ReferencesFromThis = new ArrayList<>();
        SourceCode = headerSource;
        IsTestClass = isTestClass;
    }
}
