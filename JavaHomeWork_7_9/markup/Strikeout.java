package markup;
import java.util.List;

public class Strikeout extends AbstractText implements Marking {

    public Strikeout(String text) {
        super(text);
    }

    public Strikeout(List<AbstractText> list) {
        super(list);
    }

    public StringBuilder toMarkdown(StringBuilder str) {
        return allList(str, MarkingMethod.TOMARKDOWN, "~", "~");
    }

    public StringBuilder toBBCode(StringBuilder str) {
        return allList(str, MarkingMethod.TOBBCODE, "[s]", "[/s]");
    }

    @Override
    public StringBuilder toHtml(StringBuilder str) {
        return allList(str, MarkingMethod.TOHTML, "<s>", "</s>");
    }
}