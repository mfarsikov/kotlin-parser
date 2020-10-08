package primitive;

/// <summary>
/// One end of a code reference edge. This allows the flexibility of representing the full edge using two endpoints.
/// </summary>
public class CodeReferenceEndpoint {
    public CodeReferenceType
            Type;
    public CodebaseElementName
            Endpoint;

    /// <summary>
    /// Only outbound references have a Primitive.CodeRange. All outbound references should have a code range even if the
    /// range is unknown (-1), but inbound references have no range at all (null).
    /// </summary>
    public CodeReferenceEndpoint(
            CodeReferenceType type,
            CodebaseElementName endpoint) {
        Type = type;
        Endpoint = endpoint;
    }
}
