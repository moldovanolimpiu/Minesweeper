package Tiles;

import java.awt.*;

public class BombWire extends Tile{
    private Textures textures = new Textures();
    private Image bombWireImage = textures.getBombWireTextureFull();
    private int index;


    public BombWire(Image textureImage, int index) {
        super(textureImage);
        this.index = index;

    }

    @Override
    public void setBombImage(){
        setTextureImage(textures.getBombWireTextureCut());
    }

    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }


}
