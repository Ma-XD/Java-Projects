package markup;
import java.util.List;

public class Strong extends AbstractText  implements Marking{

    public Strong(String text) {
        super(text);
    }

    public Strong(List<AbstractText> list) {
        super(list);
    }

    public StringBuilder toMarkdown(StringBuilder str) {
        return allList(str, MarkingMethod.TOMARKDOWN, "__", "__");
    }

    public StringBuilder toBBCode(StringBuilder str) {
        return allList(str, MarkingMethod.TOBBCODE, "[b]", "[/b]");
    }

    @Override
    public StringBuilder toHtml(StringBuilder str) {
        return allList(str, MarkingMethod.TOHTML, "<strong>", "</strong>");
    }
}
