package html;

import java.util.List;

public interface Visitor<of> {

    public List<of> getVisitors();

    public void toString(of log);

}
