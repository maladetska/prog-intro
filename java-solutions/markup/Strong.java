package markup;

import java.util.List;

public class Strong extends AbstractElement implements Element {
    public Strong(List<Element> elements) {
        super(elements);
    }

    @Override
    protected String markMd() {
        return "__";
    }

    @Override
    protected String markBB() {
        return "b";
    }
}
