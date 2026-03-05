package Tiles;

import java.awt.*;

public class NumberTile extends Tile {
    private Textures textures = new Textures();
    private Image numTexture;
    private int numNeighbours;
    private int x;
    private int y;
    private boolean clicked = false;
    private boolean flagged = false;

    public NumberTile(Image textureImage, int x, int y) {
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
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public boolean isClicked() {
        if(clicked) {
            return true;
        }
        return false;
    }
    public void setNumNeighbours(int numNeighbours) {
        this.numNeighbours = numNeighbours;
    }
    public int getNumNeighbours() {
        return numNeighbours;
    }
    @Override
    public void calculateNumTexture(Tile[][] mineFieldButtons){
        int neighbNumber = 0;
        int[] dx = { 0, 1, 1, 1, 0,-1,-1,-1};
        int[] dy = {-1,-1, 0, 1, 1, 1, 0,-1};

        for(int i =0;i<8;i++){
            int px = x + dx[i];
            int py = y + dy[i];
            if(px>=0 && py>=0 && px<mineFieldButtons.length && py<mineFieldButtons[0].length && (mineFieldButtons[px][py] instanceof BombTile ||
                    mineFieldButtons[px][py] instanceof StunBombTile || mineFieldButtons[px][py] instanceof RustBombTile)){
                neighbNumber++;
            }
        }
        this.numNeighbours = neighbNumber;
        if(neighbNumber == 0){numTexture = textures.getNr0texture();}
        else if(neighbNumber == 1){numTexture = textures.getNr1Texture();}
        else if(neighbNumber == 2){numTexture = textures.getNr2Texture();}
        else if(neighbNumber == 3){numTexture = textures.getNr3Texture();}
        else if(neighbNumber == 4){numTexture = textures.getNr4Texture();}
        else if(neighbNumber == 5){numTexture = textures.getNr5Texture();}
        else if(neighbNumber == 6){numTexture = textures.getNr6Texture();}
        else if(neighbNumber == 7){numTexture = textures.getNr7Texture();}
        else if(neighbNumber == 8){numTexture = textures.getNr8Texture();}
    }

    @Override
    public void setNumberImage(){
        setTextureImage(numTexture);
    }
    @Override
    public void setTileTexture(){
        setTextureImage(textures.getTileTexture());
    }

}
