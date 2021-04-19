import javax.imageio.ImageIO;
import javax.swing.*;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Camera {

    private static String [] imgName;

    public static String Camera(String designation) throws InterruptedException, IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(new Dimension(640, 480));

        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);

        JFrame window = new JFrame("Test webcam panel");
        final JButton[] b = {new JButton("Click to take picture")};
        b[0].setBounds(50,100,100,70);
        LocalDateTime now = LocalDateTime.now();
        BufferedImage image = webcam.getImage();
        imgName = new String[] {designation + "-" + dtf.format(now) + ".png"};
        ImageIO.write(image, "PNG", new File("img_tmp/" + designation + "-" + dtf.format(now) + ".png"));

        b[0].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                // save image to PNG file
                    try {
                        ImageIO.write(image, "PNG", new File("img_tmp/" + designation + "-" + dtf.format(now) + ".png"));

                        b[0] = (JButton) e.getSource();
                        window.dispose();
                        webcam.close();
                        System.out.println("Frame Closed.");

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }

        });


        Box box = Box.createVerticalBox();
        box.add(b[0]);
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

        return imgName[0].toString();
    }
}
