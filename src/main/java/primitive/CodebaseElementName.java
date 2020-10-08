package primitive;

/// <summary>
/// Everything in this file is essentially a wrapper for a String. There are various different naming schemes for
/// all the different aspects of code elements that we need to use, and this is for the purpose of unifying them.
/// </summary>
public abstract class CodebaseElementName {
    /// <summary>
    /// <para>The full, unique value that identifies this name. Any two name objects with the same fully qualified
    /// String representation are equivalent.</para>
    ///
    /// <para>The fully qualified name, being unique across a codebase, is suitable for serialization purposes.</para>
    /// </summary>
    String FullyQualified = "";

    public String getFullyQualified() {
        return FullyQualified;
    }

    public void setFullyQualified(String value) {
        FullyQualified = value;
    }

    /// <summary>
    /// A human-readable representation of the name. Examples are unqualified class names and method names without
    /// their declaring class.
    /// </summary>
    String ShortName = "";

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String value) {
        ShortName = value;
    }

    /// <summary>
    /// The parent element in the containment hierarchy. In particular, packages don't "contain" one another, even
    /// though they are linked in their own hierarchy.
    /// </summary>
    /// <remarks>
    /// May be <c>null</c> if there is no containing element.
    /// </remarks>
    CodebaseElementName ContainmentParent = null;

    public CodebaseElementName getContainmentParent() {
        return ContainmentParent;
    }

    public void setContainmentParent(CodebaseElementName value) {
        ContainmentParent = value;
    }

    /// <summary>
    /// An element is also considered to contain itself.
    /// </summary>
    public Boolean IsContainedIn(CodebaseElementName container) {
        if (this == container) return true;

        return ContainmentParent != null &&
                ContainmentParent.IsContainedIn(container);
    }

    PackageName Package = null;

    public PackageName getPackage() {
        return Package;
    }

    public void setPackage(PackageName value) {
        Package = value;
    }

    CodebaseElementType CodebaseElementType = null;

    public CodebaseElementType getCodebaseElementType() {
        return CodebaseElementType;
    }

    public void setCodebaseElementType(CodebaseElementType value) {
        CodebaseElementType = value;
    }
}