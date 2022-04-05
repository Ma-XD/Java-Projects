package markup;

public class Text extends AbstractText implements Marking{

    public Text(String text) {
        super(text);
    }

    public StringBuilder toMarkdown(StringBuilder str) {
        return str.append(text);
    }

    public StringBuilder toBBCode(StringBuilder str) {
        return str.append(text);
    }

    @Override
    public StringBuilder toHtml(StringBuilder str) {
        return str.append(text);
    }
}
