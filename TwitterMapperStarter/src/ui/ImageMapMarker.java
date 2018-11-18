package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageMapMarker extends MapMarkerCircle {
    private BufferedImage image;
    private String imageUrl;
    private String twitterText;

    public static final double defaultMarkerSize = 15.0;

    public ImageMapMarker(Layer layer, Coordinate coord, Color color, String imageUrl, String twitterText) {
        super(layer, null, coord, defaultMarkerSize, STYLE.FIXED, getDefaultStyle());
        this.imageUrl = imageUrl;
        this.twitterText = twitterText;
        setImageFromUrl();
        setColor(Color.BLACK);
        setBackColor(color);
    }

    public String getToolTipText() {
        if (this.image == null) return this.twitterText;
        return "<html><img src=\"" + this.imageUrl + "\" />" + this.twitterText + "</html>";
    }

    private void setImageFromUrl() {
        try {
            URL url = new URL(this.imageUrl);
            this.image = ImageIO.read(url);
        } catch (IOException e) {
            this.image = null;
        }
    }

    @Override
    public void paint(Graphics g, Point position, int radius) {
        if (image == null) {
            super.paint(g, position, radius);
            return;
        }
        int size = radius * 2;
        g.drawImage(this.image, position.x - radius, position.y - radius, size, size, null);
        this.paintText(g, position);
    }

}
