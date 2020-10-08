package primitive;

import java.text.MessageFormat;

public class ArrayTypeName extends TypeName {

    TypeName ComponentType;

    public ArrayTypeName(String signature) {
        ComponentType = For(signature.substring(1));
        setFullyQualified(signature);

        // Include a U+200A HAIR SPACE in order to ensure, no matter what font is used to render this name, the
        // braces don't join together visually.
        setShortName(MessageFormat.format("{0}[\u200A]", ComponentType.getShortName()));
    }
}
