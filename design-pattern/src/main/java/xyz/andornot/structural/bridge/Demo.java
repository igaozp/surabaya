package xyz.andornot.structural.bridge;

import xyz.andornot.structural.bridge.devices.Device;
import xyz.andornot.structural.bridge.devices.Radio;
import xyz.andornot.structural.bridge.devices.Tv;
import xyz.andornot.structural.bridge.remotes.AdvancedRemote;
import xyz.andornot.structural.bridge.remotes.BasicRemote;

public class Demo {
    public static void main(String[] args) {
        testDevice(new Tv());
        testDevice(new Radio());
    }

    public static void testDevice(Device device) {
        System.out.println("Tests with basic remote");
        var basicRemote = new BasicRemote(device);
        basicRemote.power();
        device.printStatus();

        System.out.println("Tests with advanced remote.");
        var advancedRemote = new AdvancedRemote(device);
        advancedRemote.power();
        advancedRemote.mute();
        device.printStatus();
    }
}
