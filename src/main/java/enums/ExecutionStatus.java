package enums;

public enum ExecutionStatus {
    CONTINUE(false), EXIT(true);

    private final boolean isExit;

    ExecutionStatus(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return this.isExit;
    }
}
