package net.useobjects.fragmenty;

/**
 * Created by vladek on 23.9.2015.
 */
public enum Selection {
    A ("kliknute tlacidlo A"),
    B ("kliknute tlacidlo B"),
    NOTHING ("nic nestlacene");

    private String text;

    Selection(String text) {
        this.text = text;
    }

    public String toInfoString() {
        return text;
    }
}
