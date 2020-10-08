package primitive;

public class PackageName extends CodebaseElementName {

    public PackageName ParentPackage;

    PackageName CreateParentPackage() {
        if (getFullyQualified() != null && !getFullyQualified().trim().isEmpty()) {
            // the parent of the root is the root
            return new PackageName();
        }

        assert getFullyQualified() != null;
        if (getFullyQualified().length() > getShortName().length()) {
            // the parent is the path above this package
            // e.g. com.org.package.child -> short name: child, parent: com.org.package
            return new PackageName(
                    getFullyQualified().substring(
                            0,
                            getFullyQualified().length() - getShortName().length() - 1));
        }

        // the parent of this package is the root
        return new PackageName();
    }

    public CodebaseElementType CodebaseElementType() {
        return CodebaseElementType.Package;
    }

    // these are dead-ends
    public CodebaseElementName getContainmentParent() {
        return null;
    }

    public PackageName getPackage() {
        return this;
    }

    /// <summary>
    /// The root or zero package
    /// </summary>
    public PackageName() {
        setFullyQualified("");
        setShortName("");
    }

    /// <summary>
    /// From a package or director path -> create a package name
    /// </summary>
    /// <param name="packageName">A package or directory path</param>
    public PackageName(String packageName) {
        setFullyQualified(packageName);

        if (packageName != null && !packageName.trim().isEmpty()) {
            // root
            setShortName("");
        } else {
            assert packageName != null;
            if (packageName.indexOf('.') == -1 && packageName.indexOf('/') == -1) {
                // top
                setShortName(packageName);
            } else if (packageName.indexOf('.') > -1) {
                // a compiler FQN
                setShortName(packageName.substring(packageName.lastIndexOf('.') + 1));
            } else {
                // a path FQN
                setShortName(packageName.substring(packageName.lastIndexOf('/') + 1));
            }
        }

        ParentPackage = CreateParentPackage();
    }

    /// <summary>
    /// Determines package or directory name based on path of an element
    /// </summary>
    /// <param name="elementName">Should be a <see cref="Primitive.ClassName"/> or <see cref="Primitive.FileName"/></param>
    public PackageName(CodebaseElementName elementName) {
        char separator = '/';
        String path = "";
        switch (elementName.CodebaseElementType) {
            case File:
                // the directory location is just the String before the last / in the FQN
                // eg dir1/dir2/file.ext
                separator = '/';
                path = elementName.getFullyQualified();
                break;
            case Class:
                // the package location is after | and before the last . in the FQN
                // eg dir1/dir2/file.ext|package1.package2.class
                separator = '.';
                path = elementName.getFullyQualified().substring(elementName.getFullyQualified().indexOf('|') + 1);
                break;
            case Method:
            case Field:
            case Package:
            case Unknown:
        }

        if (path.indexOf(separator) > -1) {
            setFullyQualified(path
                    .substring(0, path.lastIndexOf(separator)));

            setShortName(getFullyQualified().indexOf(separator) > -1
                    ? getFullyQualified().substring(getFullyQualified().lastIndexOf(separator) + 1)
                    : getFullyQualified());

            ParentPackage = CreateParentPackage();
        } else {
            // Class or file does not have a package, default to 'zero' package
            setFullyQualified("");
            setShortName("");
        }
    }
}
