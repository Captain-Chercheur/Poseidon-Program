import javax.imageio.ImageIO;
import javax.swing.*;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import javafx.event.ActionEvent;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Camera {
    public static void Camera() throws InterruptedException, IOException {

        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(new Dimension(640, 480));

        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);
        JButton b=new JButton("Click Here");
        b.setBounds(50,100,100,70);
        b.addActionListener(new ActionListener(){
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                BufferedImage image = webcam.getImage();
                // save image to PNG file
                try {
                    ImageIO.write(image, "PNG", new File("test.png"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });


        JFrame window = new JFrame("Test webcam panel");
        Box box = Box.createVerticalBox();
        box.add(b);
        box.add(panel);
        window.add(box);
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }
}
