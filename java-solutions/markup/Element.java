package markup;

interface Element {
    void toMarkdown(StringBuilder sb);
    void toBBCode(StringBuilder sb);
}
