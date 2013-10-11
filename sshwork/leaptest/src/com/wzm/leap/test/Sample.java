package com.wzm.leap.test;

import java.awt.Toolkit;
import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.DeviceList;

public class Sample {
    public static void main(String[] args) {
    	
        // Create a sample listener and controller
        SampleListener listener = new SampleListener();
        Controller controller = new Controller();

        // Have the sample listener receive events from the controller
        controller.addListener(listener);
        DeviceList dd = controller.devices();
        System.out.println(dd.get(0));

        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove the sample listener when done
        controller.removeListener(listener);
    }
}