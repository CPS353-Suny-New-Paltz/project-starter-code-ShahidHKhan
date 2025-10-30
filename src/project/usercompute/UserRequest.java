package project.usercompute;

public class UserRequest {
	private final int number;

	public UserRequest(int number) {
        // Validation: reject clearly invalid input values
        if (number < 0) {
            throw new IllegalArgumentException("number cannot be negative.");
        }
        this.number = number;
    }
	public int getNumber() {
        return number;
    }
}