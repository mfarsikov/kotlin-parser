package primitive;

public class ClassName extends TypeName {
    public String JavaFullyQualified;

    public CodebaseElementType CodebaseElementType() {
        return CodebaseElementType.Class;
    }

    public CodebaseElementName getContainmentParent() {
        return IsOuterClass
                ? ContainmentFile
                : ParentClass;
    }

    public FileName ContainmentFile;

    public PackageName getPackage() {
        return new PackageName(ContainmentFile);
    }

    public PackageName getCompilerPackage() {
        return new PackageName(this);
    }

    public Boolean IsOuterClass;
    public ClassName ParentClass;

    // note: fullyQualified must look like:
    // dir1/dir2/filename.ext|my.class.package.OuterClass$InnerClass1$InnerClass2
    public ClassName(String fullyQualified) {
        setFullyQualified(fullyQualified);
        setCodebaseElementType(CodebaseElementType.Class);
        String packageAndClass = fullyQualified;
        if (fullyQualified.indexOf('|') > -1) {
            packageAndClass = fullyQualified.substring(fullyQualified.indexOf('|') + 1);
        }

        String className = packageAndClass;
        if (packageAndClass.indexOf('.') > -1) {
            // my.class.package.class1 => class1
            className = packageAndClass.substring(packageAndClass.lastIndexOf('.') + 1);
        }

        // only required to make the Java runtime trace match
        JavaFullyQualified = "L" + packageAndClass + ";";
        if (fullyQualified.indexOf('|') > -1 && !fullyQualified.startsWith("|")) {
            ContainmentFile = new FileName(
                    fullyQualified.substring(0, fullyQualified.indexOf('|')));
        }

        if (className.indexOf('$') > -1) {
            IsOuterClass = false;
            String[] innerClassSplit = className.split("\\$");
            setShortName(innerClassSplit[innerClassSplit.length - 1]);

            ParentClass =
                    new ClassName(
                            getFullyQualified().substring(
                                    0,
                                    // remove $ and name of inner
                                    getFullyQualified().lastIndexOf('$')));
        } else {
            IsOuterClass = true;
            setShortName(className);
        }
    }
}