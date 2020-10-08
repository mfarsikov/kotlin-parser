package primitive;

public enum SourceCodeLanguage {
    // These enum int values must match in:
    // - IntelliJ Plugin -> SQLiteOutput#dbValueForSourceCodeLanguage
    // - C# analyzer -> CsStructure#SourceCodeSnippetLanguage
    // - JS Analyzer -> sqliteOutput#JAVASCRIPT_LANGUAGE_TYPE
    PlainText(-1),

    // Core, widely-used languages
    Java(0),
    CSharp(1),
    JavaScript(2),
    Cpp(3),
    Kotlin(4),
    XML(5),
    SQL(6),
    Markdown(7),
    HTML(8),
    Python(9),
    Scala(10),
    C(11),
    CWithClasses(12),
    ObjC(13),
    Rust(14),

    // Non-core, specialized languages
    Solidity(100);
    private int value;

    SourceCodeLanguage(int i) {
        value = i;
    }

    public int getValue() {
        return value;
    }
}
