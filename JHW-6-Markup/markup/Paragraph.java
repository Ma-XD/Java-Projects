    package markup;
import java.util.List;

public class Paragraph extends AbstractText implements Marking{

    public Paragraph(String text) {
        super(text);
    }

    public Paragraph(List<AbstractText> list) {
        super(list);
    }


    public StringBuilder toMarkdown(StringBuilder str) {
        str = allList(str, MarkingMethod.TOMARKDOWN, "", "");
        return str;
    }

    public StringBuilder toBBCode(StringBuilder str) {
        str = allList(str, MarkingMethod.TOBBCODE, "", "");
        return str;
    }

    @Override
    public StringBuilder toHtml(StringBuilder str) {
        return allList(str, MarkingMethod.TOHTML, "<p>", "</p>");
    }
}
