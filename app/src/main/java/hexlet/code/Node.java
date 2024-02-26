package hexlet.code;

public class Node {

    private String statusName;
    private Object oldValue;
    private Object newValue;

    Node(String statusname, Object oldvalue, Object newvalue) {
        statusName = statusname;
        oldValue = oldvalue;
        newValue = newvalue;
    }

    public String getStatusName() {
        return statusName;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
