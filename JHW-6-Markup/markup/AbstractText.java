package markup;
import java.util.List;

public abstract class AbstractText implements Marking {
    protected String text;
    protected List<AbstractText> list;
    protected enum MarkingMethod {
        TOBBCODE,
        TOMARKDOWN,
        TOHTML
    }

    protected AbstractText(String text) {
        this.text = text;
    }

    protected AbstractText(List<AbstractText> list) {
        this.list = list;
    }

    protected StringBuilder allList(StringBuilder str, MarkingMethod method, String firstSymbol, String lastSymbol) {
        str.append(firstSymbol);
        StringBuilder temp = new StringBuilder();
        if (list != null) {
            for(AbstractText a : list) {
                switch (method) {
                    case TOBBCODE:
                        str.append(a.toBBCode(temp) );
                        break;
                    case TOMARKDOWN:
                        str.append(a.toMarkdown(temp) );
                        break;
                    case TOHTML:
                        str.append(a.toHtml(temp));
                }
                temp = new StringBuilder();
            }
        }

        return str.append(lastSymbol);
    }
}
