package markup;

import java.util.List;

public class Heading extends AbstractText{
    private final int level;

    protected Heading(int level, String text) {
        super(text);
        this.level = level;
    }

    public Heading(int level, List<AbstractText> list) {
        super(list);
        this.level = level;
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
        return allList(str, MarkingMethod.TOHTML, "<h" + level + ">", "</h" + level + ">");
    }
}
