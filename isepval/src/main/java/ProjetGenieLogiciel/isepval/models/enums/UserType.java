package ProjetGenieLogiciel.isepval.models.enums;

public enum UserType {
    STUDENT("Student"),
    TEACHER("Teacher"),
    ADMIN("Admin");

    private final String displayName;

    UserType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
