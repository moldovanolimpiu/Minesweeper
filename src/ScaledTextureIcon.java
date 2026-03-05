import javax.swing.*;
import java.awt.*;

public class ScaledTextureIcon implements Icon {
    private final ImageIcon textureIcon;
    private final JButton button;

    public ScaledTextureIcon(ImageIcon textureIcon, JButton button) {
        this.textureIcon = textureIcon;
        this.button = button;
    }


    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        int width = button.getWidth();
        int height = button.getHeight();
        Image scaledImage = textureIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        g.drawImage(scaledImage, 0, 0, width, height, null);
    }

    @Override
    public int getIconWidth() {
        return button.getWidth();
    }
    public int getIconHeight() {
        return button.getHeight();
    }
}
