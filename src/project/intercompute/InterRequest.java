package project.intercompute;

public class InterRequest {
    private final byte[] bytes;

    public InterRequest(byte[] bytes) {
        
        if (bytes == null) {
            throw new IllegalArgumentException("bytes array cannot be null.");
        }
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
