package project.usercompute;

public class ComputeRequest {
    private final DataSource source;
    private final String outputPath;

    public ComputeRequest(DataSource source, String outputPath) {
        if (source == null) {
            throw new IllegalArgumentException("source cannot be null");
        }
        this.source = source;
        this.outputPath = outputPath;
    }

    public DataSource getSource() {
        return source;
    }

    public String getOutputPath() {
        return outputPath;
    }
}

