package draw;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ImageUtil {
    public static void writeImage(String file, BufferedImage i) throws IOException {
        ImageWriter writer = ImageIO.getImageWritersByMIMEType("image/png").next();
        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_DEFAULT);

        try (ImageOutputStream os = new FileImageOutputStream(new File(file))) {
            writer.setOutput(os);
            writer.write(null, new IIOImage(i, null, null), param);
        }
    }

    public static void showImage(final BufferedImage image) {
        JDialog dialog = new JDialog((JFrame) null, "Image", true);
        dialog.getContentPane().setSize(new Dimension(image.getWidth(), image.getHeight()));
        dialog.getContentPane().add(new JLabel(new ImageIcon(image)));
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.dispose();
    }

    public static BufferedImage createImage(int width, int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public static Color toGrayScale(final Color color) {
        float luminosity = (0.21f * color.getRed() + 0.72f * color.getGreen() + 0.07f * color.getBlue()) / 256;
        return new Color(luminosity, luminosity, luminosity);
    }
}
