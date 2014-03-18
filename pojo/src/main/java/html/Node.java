package html;

public interface Node<category, of, content> {

    public content commit();

    public of setAttributes(Object attribute);

    public category appendContent(Object content);
}
