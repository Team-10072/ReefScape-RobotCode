package frc.robot;

import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.MjpegServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.util.PixelFormat;

public class VisionTesting {
    // Creates UsbCamera and MjpegServer [1] and connects them
    public UsbCamera usbCamera = new UsbCamera("USB Camera 0", 0);
    public MjpegServer mjpegServer1 = new MjpegServer("serve_USB Camera 0", 1181);
    
    // Creates the CvSink and connects it to the UsbCamera
    public CvSink cvSink = new CvSink("opencv_USB Camera 0");
    
    // Creates the CvSource and MjpegServer [2] and connects them
    public CvSource outputStream = new CvSource("Blur", PixelFormat.kMJPEG, 640, 480, 30);
    public MjpegServer mjpegServer2 = new MjpegServer("serve_Blur", 1182);

    public boolean setup() {
        try {
            mjpegServer1.setSource(usbCamera);
            cvSink.setSource(usbCamera);
            mjpegServer2.setSource(outputStream);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}