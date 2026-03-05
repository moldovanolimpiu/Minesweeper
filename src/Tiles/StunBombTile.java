package Tiles;

import java.awt.*;

public class StunBombTile extends Tile {
    private Textures textures = new Textures();
    private Image stunMineImage = textures.getStunMineTexture();
    private int x;
    private int y;
    private boolean clicked = false;
    private boolean flagged = false;

    public StunBombTile(Image textureImage, int x, int y) {
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
    public boolean getClicked() {
        return clicked;
    }

    @Override
    public void setBombImage(){
        //this.textureImage = bombImage;
        setTextureImage(stunMineImage);
    }
}
