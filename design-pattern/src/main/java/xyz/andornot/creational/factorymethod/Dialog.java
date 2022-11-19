package xyz.andornot.creational.factorymethod;

public abstract class Dialog {
    public void renderWindow() {
        var button = createButton();
        button.render();
    }

    public abstract Button createButton();
}
