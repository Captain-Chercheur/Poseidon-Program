import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import static java.lang.Integer.parseInt;

public class quantity_management_scanner extends JFrame implements Runnable, ThreadFactory {

    private static final long serialVersionUID = 6441489157408381878L;

    private Executor executor = Executors.newSingleThreadExecutor(this);

    private Webcam webcam = null;
    private WebcamPanel panel = null;
    private JTextArea textarea = null;


    public quantity_management_scanner() throws IOException {
        super();

        setLayout(new FlowLayout());
        setTitle("Read Bar");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we){
                webcam.close();
            }
        });

        Dimension size = WebcamResolution.QVGA.getSize();

        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        textarea = new JTextArea();
        textarea.setEditable(false);
        textarea.setPreferredSize(size);

        add(panel);
        add(textarea);

        pack();
        setVisible(true);

        executor.execute(this);
    }
    String[] number1 = new String[10];
    String[] number2 = new String[10];
    @Override
    public void run() {

        boolean numberone = false;
        boolean numbertwo = false;
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {

                if ((image = webcam.getImage()) == null) {
                    continue;
                }

                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                try {
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException e) {
                    // fall thru, it means there is no QR code in image
                }
            }

            if (result != null) {

                if (!numberone) {
                    number1 = result.getText().split("-");
                    Toolkit.getDefaultToolkit().beep();
                    numberone = true;
                    System.out.println("number one: " + Arrays.toString(number1));
                } else if (!numbertwo){
                    number2 = result.getText().split("-");
                    Toolkit.getDefaultToolkit().beep();
                    numbertwo = true;
                    System.out.println("number two: " + Arrays.toString(number2));
                }
                if (numberone && numbertwo){

                    if (number1[0].length() == 2 && number2[0].length() < 2 ){
                        int quantity = parseInt(HTMLrequests.HTMLrequests("get_shelf_quantity/" + number1[2]).replaceAll("[\\[\\](){}]",""));
                        quantity += 1;
                        HTMLrequests.HTMLrequests("change_shelf_quantity/" + number1[2] + "/" + quantity);
                        if (parseInt(HTMLrequests.HTMLrequests("check_product_waiting/" + number2[1]).replaceAll("[\\[\\](){}]","")) == 1){
                            HTMLrequests.HTMLrequests("product_waiting/" + number2[1] + "/0");
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else if (number1[0].length() < 2 && number2[0].length() == 2 ){
                        int quantity = parseInt(HTMLrequests.HTMLrequests("get_shelf_quantity/" + number1[2]).replaceAll("[\\[\\](){}]",""));
                        quantity -= 1;
                        HTMLrequests.HTMLrequests("change_shelf_quantity/" + number1[2] + "/" + quantity);
                    }
                }


            /*    String detailsList = HTMLrequests.HTMLrequests("product_waiting/"+result.getText());
                String details = detailsList.replaceAll("(\"\\^\\[|\\{\\\\}\\\\]\\$\")", "");
                String[] aled = details.split(",");
                for (int i = 0; aled.length > i; i++) {
                    textarea.append("\n" + aled[i]);
                }*/
            }

        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "example-runner");
        t.setDaemon(true);
        return t;
    }

}