package mchti.model.sample;

public enum ZxGender {

    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    String name;

    private ZxGender(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
