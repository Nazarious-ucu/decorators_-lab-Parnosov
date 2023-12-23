package ua.edu.ucu.apps;

public class TimedDocument implements Document {
    private String path;

    @Override
    public String parse() {
        SmartDocument smartDocument = new SmartDocument(path);
        long startTime = System.currentTimeMillis();
        smartDocument.parse();
        return Long.toString(System.currentTimeMillis() - startTime);
    }
}
