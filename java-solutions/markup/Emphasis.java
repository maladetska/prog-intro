package markup;

import java.util.List;

public class Emphasis extends AbstractElement implements Element {
    public Emphasis(List<Element> elements) {
        super(elements);
    }

    @Override
    protected String markMd() {
        return "*";
    }

    @Override
    protected String markBB() {
        return "i";
    }
}
