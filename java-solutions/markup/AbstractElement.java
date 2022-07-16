package markup;

import java.util.List;

public abstract class AbstractElement implements Element {
    private final List<Element> elements;

    public AbstractElement(List<Element> elements) {
        this.elements = elements;
    }

    protected abstract String markMd();
    protected abstract String markBB();

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(markMd());
        for (Element curr : elements) {
            curr.toMarkdown(sb);
        }
        sb.append(markMd());
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append("[").append(markBB()).append("]");
        for (Element curr : elements) {
            curr.toBBCode(sb);
        }
        sb.append("[/").append(markBB()).append("]");
    }
}
