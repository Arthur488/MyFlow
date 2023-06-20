package workflow.models;

public enum Priority {
    LOW("Низький", 5),
    MEDIUM_LOW("Середньо-низький", 4),
    MEDIUM("Середній", 3),
    MEDIUM_HIGH("Середньо-високий", 2),
    HIGH("Високий", 1);

    private final String ukrainianValue;
    private final int weight;

    Priority(String ukrainianValue, int weight) {
        this.ukrainianValue = ukrainianValue;
        this.weight = weight;
    }

    public String getValue() {
        return ukrainianValue;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getPriorityColor(Priority priority) {
        return switch (priority) {
            case LOW -> "green";
            case MEDIUM_LOW -> "blue";
            case MEDIUM -> "orange";
            case MEDIUM_HIGH -> "darkred";
            case HIGH -> "red";
            default -> "black";
        };
    }

}


