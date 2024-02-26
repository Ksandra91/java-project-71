package hexlet.code;

public class Node {

    public String statusName;
    public Object oldValue;
    public Object newValue;

    Node(String statusname, Object oldvalue, Object newvalue) {
        statusName = statusname;
        oldValue = oldvalue;
        newValue = newvalue;
    }

}
