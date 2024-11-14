package library;

public enum AvailabilityStatus {
    AVAILABLE("Available"),
    UNAVAILABLE("Unavailable");

    private final String displayName;

    AvailabilityStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static AvailabilityStatus fromString(String input) {
        switch (input.toLowerCase()) {
            case "a":
            case "available":
                return AVAILABLE;
            case "u":
            case "unavailable":
                return UNAVAILABLE;
            default:
                throw new IllegalArgumentException("Invalid availability status: " + input);
        }
    }
}
