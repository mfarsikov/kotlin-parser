package primitive;


import java.util.ArrayList;
import java.util.List;

public class FieldInfo implements ICodebaseElementInfo {
    //the most concrete class owning this field
    public ClassName ParentClass;

    public TypeName FieldType;
    public FieldName FieldName;

    @Override
    public CodebaseElementName getName() {
        return FieldName;
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

    @Override
    public SourceCodeSnippet getSourceCode() {
        return SourceCode;
    }

    public AccessFlags AccessFlags;

    public List<MethodInfo> Methods;
    List<CodeReferenceEndpoint> ReferencesToThis;
    List<CodeReferenceEndpoint> ReferencesFromThis;

    SourceCodeSnippet SourceCode;

    public FieldInfo(
            FieldName fieldName,
            ClassName parentClass,
            AccessFlags accessFlags,
            SourceCodeSnippet sourceCode) {
        ParentClass = parentClass;
        FieldName = fieldName;
        FieldType = TypeName.For(FieldName.getFullyQualified().split(":")[1]);
        AccessFlags = accessFlags;
        Methods = new ArrayList<>();
        ReferencesToThis = new ArrayList<>();
        ReferencesFromThis = new ArrayList<>();
        SourceCode = sourceCode;
    }
}
