package apijson.framework;

public interface HttpSession {
    public String getId();
    public Object getAttribute(String name);
    public void setAttribute(String name, Object value);
    public void invalidate();
}
