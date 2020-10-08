package primitive;

import java.util.ArrayList;
import java.util.List;

public class SourceCodeSnippet {
    String text;
    SourceCodeLanguage language;
    public List<CodeRangeWithReference> OutboundUsageLinks = new ArrayList<>();

    public SourceCodeSnippet(
            String text,
            SourceCodeLanguage language) {
        this.text = text;
        this.language = language;
    }

    public String Text() {
        return text != null && !text.trim().isEmpty()
                ? text
                : "";
    }
}