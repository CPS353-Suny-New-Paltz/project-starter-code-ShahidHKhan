package project.intercompute;

public class InterRequest {
	private final int number;

	public InterRequest(int number) {
        
		if (number < 0) {
            throw new IllegalArgumentException("number cannot be negative.");
        }
        this.number = number;
    }

	public int getNumber() {
        return number;
    }
}
