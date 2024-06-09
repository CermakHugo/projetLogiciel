package ProjetGenieLogiciel.isepval.models.enums;

public enum Mark {
    NOTEVALUATED("Not Evaluated"),
    NOTACQUIRED("Not Acquired"),
    FAR("Far"),
    CLOSE("Close"),
    VERYCLOSE("Very Close"),

    ACQUIRED("Acquired"),

    PERFECT("Perfect");

    private final String displayName;

    Mark(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
