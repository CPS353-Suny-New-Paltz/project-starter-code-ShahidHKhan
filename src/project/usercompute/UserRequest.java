package project.usercompute;

public class UserRequest {
    private final byte[] bytes;

    public UserRequest(byte[] bytes) {
        // Validate parameter
        if (bytes == null) {
            throw new IllegalArgumentException("bytes array cannot be null.");
        }
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }
}