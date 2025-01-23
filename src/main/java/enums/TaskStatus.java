package enums;

public enum TaskStatus {
    PENDING(false),
    COMPLETED(true);

    private final boolean isDone;

    TaskStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean isDone() {
        return this.isDone;
    }
}
