package Tiles;

import java.awt.*;

public class RustBombTile extends Tile {
    private Textures textures = new Textures();
    private Image rustMineImage = textures.getRustMineTexture();
    private int x;
    private int y;
    private boolean clicked = false;
    private boolean flagged = false;

    public RustBombTile(Image textureImage, int x, int y) {
        super(textureImage);
        this.x = x;
        this.y = y;
    }
    public void setFlagged(boolean flag) {
        flagged = flag;
    }
    public boolean isFlagged() {
        if(flagged) {
            return true;
        }
        return false;
    }

    public boolean isClicked() {
        if(clicked) {
            return true;
        }
        return false;
    }
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
    @Override
    public void setBombImage(){
        //this.textureImage = bombImage;
        setTextureImage(rustMineImage);
    }
}
