package primitive;

import java.util.List;

/// <summary>
/// Information about classes/methods/fields within the user's program. This information is loaded in by the
/// <see cref="StaticAnalysisReader"/> whenever a codebase is loaded.
/// </summary>
public interface ICodebaseElementInfo {
    CodebaseElementName getName();

    /// <summary>
    /// All the children of this codebase element. This property imposes a canonical order in the set of children,
    /// and the children are expected to be shown in that order in the world.
    /// </summary>
    List<ICodebaseElementInfo> getChildren();

    List<CodeReferenceEndpoint> getReferencesToThis();

    List<CodeReferenceEndpoint> getReferencesFromThis();

    SourceCodeSnippet getSourceCode();
}