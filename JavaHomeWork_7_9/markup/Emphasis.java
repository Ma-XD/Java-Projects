package markup;
import java.util.List;

public class Emphasis extends AbstractText implements Marking{

    public Emphasis(String text) {
        super(text);
    }

    public Emphasis(List<AbstractText> list) {
        super(list);
    }

    public StringBuilder toMarkdown(StringBuilder str) {
        return allList(str, MarkingMethod.TOMARKDOWN, "*", "*");
    }

    public StringBuilder toBBCode(StringBuilder str) {
        return allList(str, MarkingMethod.TOBBCODE, "[i]", "[/i]");
    }

    @Override
    public StringBuilder toHtml(StringBuilder str) {
        return allList(str, MarkingMethod.TOHTML, "<em>", "</em>");
    }
}
