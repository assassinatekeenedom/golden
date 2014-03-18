package html;

public interface Node {

    public void commit();

    public void setAttributes(Object attribute);

    public void appendContent(Object content);
}
