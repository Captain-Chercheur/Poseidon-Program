import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class imageProcess {

    public static void imageProcess(String designation, String state, String shipBrand,String shipModel, String shipYear, String color, String weight, String stockPlacement, String fileName) throws IOException {



// load source images
        BufferedImage image = ImageIO.read(new File("img_static/whitebackground.png"));
        BufferedImage overlay = ImageIO.read(new File(fileName));

// create the new image, canvas size is the max. of both image sizes
        int w = Math.max(image.getWidth(), overlay.getWidth());
        int h = Math.max(image.getHeight(), overlay.getHeight());
        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

// paint both images, preserving the alpha channels
        Graphics g = combined.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.drawImage(overlay, 0, 0, null);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
        g.drawString(designation, 0,130);
        g.drawString(state, 0,150);
        g.drawString(shipBrand + " " + shipModel + " " + shipYear, 0,170);
        g.drawString(color + "   -   " + weight + "   -   " + stockPlacement, 0,190);



// Save as new image
        ImageIO.write(combined, "PNG", new File(fileName + "-combined.png"));
    }


}