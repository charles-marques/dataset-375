
package novoda.lib.rest.net;

import java.util.LinkedList;

public class Sath implements SathVisitor {

    private final String sath;

    private LinkedList<String> pathStack;

    private LinkedList<String> arrayStack;

    Callback callback;

    int arrayIndex;

    private boolean inVisitState = false;

    public Sath(String sath) {
        if (sath.startsWith("/"))
            throw new SathFormatException("Streamer path should not start with '/'");
        this.sath = sath;
        this.arrayStack = new LinkedList<String>();
    }

    @Override
    public void visitStartElement(String elementName) {
        String pop = pathStack.removeFirst();
        if (arrayIndex > 0) {
            arrayStack.add(pop);
        }
        inVisitState = (elementName.equalsIgnoreCase(pop));
    }

    @Override
    public void visitEndElement() {
        inVisitState = true;
    }

    @Override
    public void visitStartArray(int index) {
        String current = pathStack.peek();
        if (current.charAt(0) == '[' && current.charAt(1) == '#') {
            arrayIndex = index;
            pathStack.removeFirst();
        }
    }

    @Override
    public void visitEndArray() {
    }

    @Override
    public void start() {
        pathStack = new LinkedList<String>();
        for (String path : sath.split("/")) {
            pathStack.add(path);
        }
    }

    @Override
    public void end() {
        pathStack.clear();
    }

    @Override
    public void addCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public String toString() {
        return "" + pathStack;
    }

    @Override
    public void visitElement(Object element) {
        if (inVisitState && pathStack.isEmpty()) {
            callback.onHit(this, element);
        }
    }
}
