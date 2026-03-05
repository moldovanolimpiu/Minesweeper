package Tiles;

import javax.swing.*;
import java.awt.*;
public class TexturedButton extends JButton {

    protected Image textureImage;
    public TexturedButton(Image textureImage) {
        this.textureImage = textureImage;

    }

    public void setTextureImage(Image newTextureImage) {
        this.textureImage=newTextureImage;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(textureImage != null) {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            g2d.drawImage(textureImage, 0, 0, width, height, null);

            g2d.dispose();
        }
    }
}
