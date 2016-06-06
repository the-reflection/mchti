package org.reflection.model.security;

public enum AuthGender {

    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    String name;

    private AuthGender(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
