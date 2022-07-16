package markup;

import java.util.List;

public class Strikeout extends AbstractElement implements Element {
    public Strikeout(List<Element> elements) {
        super(elements);
    }

    @Override
    protected String markMd() {
        return "~";
    }

    @Override
    protected String markBB() {
        return "s";
    }
}

