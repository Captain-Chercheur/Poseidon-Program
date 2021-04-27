import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

public class barcode {
    public static String file_name;
    public static String barcode(char designation, String Placement) throws Exception {
        try {
            //Create the barcode bean
            Code39Bean bean = new Code39Bean();

            final int dpi = 150;

            //Configure the barcode generator
            bean.setModuleWidth(UnitConv.in2mm(2f / dpi)); //makes the narrow bar
            //width exactly one pixel
            bean.setWideFactor(3);
            bean.doQuietZone(false);
            String id = HTMLrequests.HTMLrequests("get_id").toString().replaceAll("(^\\[|\\]$)", "");
            id= id.toString().replaceAll("(^\\[|\\]$)", "");
            id = id.toString().replaceAll("(^\\[|\\]$)", "");
            int id_int = Integer.parseInt(id);
            id_int = id_int+1;
            System.out.println(id_int);
            file_name = designation + "-" + id_int + "-" + Placement;
            //Open output file
            File outputFile = new File("barcodes/" + file_name);
            OutputStream out = new FileOutputStream(outputFile);
            try {
                //Set up the canvas provider for monochrome JPEG output
                BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                        out, "image/jpeg", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

                //Generate the barcode

                bean.generateBarcode(canvas, file_name);
                
                //Signal end of generation
                canvas.finish();
            } finally {
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file_name;
    }
    public static String shelf_barcode(String designation, String Placement) throws Exception {
        try {
            //Create the barcode bean
            Code39Bean bean = new Code39Bean();

            final int dpi = 150;

            //Configure the barcode generator
            bean.setModuleWidth(UnitConv.in2mm(2f / dpi)); //makes the narrow bar
            //width exactly one pixel
            bean.setWideFactor(3);
            bean.doQuietZone(false);
            String id = HTMLrequests.HTMLrequests("get_shelf_id").toString().replaceAll("(^\\[|\\]$)", "");
            id= id.toString().replaceAll("(^\\[|\\]$)", "");
            id = id.toString().replaceAll("(^\\[|\\]$)", "");
            int id_int = Integer.parseInt(id);
            id_int = id_int+1;
            System.out.println(id_int);
            file_name = designation + "-" + id_int + "-" + Placement;
            //Open output file
            File outputFile = new File("shelf_barcodes/" + file_name);
            OutputStream out = new FileOutputStream(outputFile);
            try {
                //Set up the canvas provider for monochrome JPEG output
                BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                        out, "image/jpeg", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

                //Generate the barcode

                bean.generateBarcode(canvas, file_name);

                //Signal end of generation
                canvas.finish();
            } finally {
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file_name;
    }
}
