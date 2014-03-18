package html;

public interface Visitor<of, value> {

    public value toString(of log);

}
