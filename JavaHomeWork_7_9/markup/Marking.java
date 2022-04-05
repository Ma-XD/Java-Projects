package markup;

public interface Marking {
    StringBuilder toMarkdown(StringBuilder str);
    StringBuilder toBBCode(StringBuilder str);
    StringBuilder toHtml(StringBuilder str);
}
