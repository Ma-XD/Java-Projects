package markup;

import java.util.List;

public class Code extends AbstractText implements Marking {
    public Code(String text) {
        super(text);
    }

    public Code(List<AbstractText> list) {
        super(list);
    }

    @Override
    public StringBuilder toMarkdown(StringBuilder str) {
        return null;
    }

    @Override
    public StringBuilder toBBCode(StringBuilder str) {
        return null;
    }

    @Override
    public StringBuilder toHtml(StringBuilder str) {
        return allList(str, MarkingMethod.TOHTML, "<code>", "</code>");
    }
}
