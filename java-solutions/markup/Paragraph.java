package markup;

import java.util.List;

public class Paragraph implements Element {
    private final List<Element> elements;

    public Paragraph(List<Element> elements) {
        this.elements = elements;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        for (Element curr : elements) {
            curr.toMarkdown(sb);
        }
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        for (Element curr : elements) {
            curr.toBBCode(sb);
        }
    }
}
