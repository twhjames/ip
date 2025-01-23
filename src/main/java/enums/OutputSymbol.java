package enums;

public enum OutputSymbol {
    CHECKMARK("✔"),
    WARNING("⚠️"),
    HELIX("🤖"),
    USER("👤"),
    CALENDAR("📅"),
    CLOCK("🕒"),
    BIN("🗑️"),
    CLIPBOARD("📋"),
    NOTE("📝"),
    WRENCH("🛠️"),
    HANDWAVE("👋"),
    SPARKLE("✨"),
    CHECK("✅"),
    CROSS("❌"),
    FOLDER("🗂️");

    private final String symbol;

    OutputSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }
}