package Tiles;

import java.awt.*;


public class BombTile extends Tile {
    private Textures textures = new Textures();
    private Image bombImage = textures.getMineTexture();
    private int x;
    private int y;
    private boolean flagged = false;

    public BombTile(Image textureImage, int x, int y) {
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

    @Override
    public void setBombImage(){
        //this.textureImage = bombImage;
        setTextureImage(bombImage);
    }
}
