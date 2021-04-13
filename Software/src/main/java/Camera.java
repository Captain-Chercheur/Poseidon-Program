import javax.imageio.ImageIO;
import javax.swing.*;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import javafx.event.ActionEvent;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Camera {
    public static void Camera(String designation) throws InterruptedException, IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(new Dimension(640, 480));

        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);


        JButton b=new JButton("Click to take picture");
        b.setBounds(50,100,100,70);
        b.addActionListener(new ActionListener(){
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                int i = 0;
                LocalDateTime now = LocalDateTime.now();
                BufferedImage image = webcam.getImage();
                // save image to PNG file
                try {
                    ImageIO.write(image, "PNG", new File(designation+"-"+dtf.format(now)+".png"));
                    i++;
                    String[] imgName = new String[]{designation + "-" + dtf.format(now) + ".png"};
                    System.out.println(imgName[0].toString());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
     /*   JButton removePhoto1=new JButton("REMOVE");
        removePhoto1.setBounds(150,200,100,70);
        removePhoto1.addActionListener(new ActionListener(){
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             *//*
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                int i = 0;
                LocalDateTime now = LocalDateTime.now();
                BufferedImage image = webcam.getImage();
                // save image to PNG file
                try {
                    ImageIO.write(image, "PNG", new File(designation+"-"+dtf.format(now)+".png"));
                    i++;
                    String[] imgName = new String[]{designation + "-" + dtf.format(now) + ".png"};
                    System.out.print(imgName);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });*/


        JFrame window = new JFrame("Test webcam panel");
        Box box = Box.createVerticalBox();
        box.add(b);
        box.add(panel);
        window.add(box);
        window.setResizable(true);
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                webcam.close();
            }
        });
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.pack();
        window.setVisible(true);

    }
}
