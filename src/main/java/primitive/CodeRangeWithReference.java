package primitive;

public class CodeRangeWithReference {
    public int Start;
    public int End;
    public CodebaseElementName Reference;

    public CodeRangeWithReference(
            CodeRange codeRange,
            CodebaseElementName reference) {
        Start = codeRange.Start;
        End = codeRange.End;
        Reference = reference;
    }
}
