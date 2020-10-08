package primitive;

public class FileName extends CodebaseElementName {

    public CodebaseElementName getContainmentParent() {
        return getPackage();
    }

    public PackageName getPackage() {
        return new PackageName(this);
    }

    public CodebaseElementType CodebaseElementType() {
        return CodebaseElementType.File;
    }

    public FileName(String path) {
        setFullyQualified(path);
        setCodebaseElementType(CodebaseElementType.File);

        setShortName(path.indexOf('/') > -1
                ? path.substring(path.lastIndexOf('/') + 1)
                : path);
    }
}
