import Tiles.BombWire;
import Tiles.NumberTile;
import Tiles.Tile;

import java.util.ArrayList;

public class MinesweeperModel {
    private static final String INITIAL_VALUE = "";
    private int height;
    private int width;
    private int tileSize;
    private int widthTileCalc;
    private int mines;
    private int startX;
    private int startY;

    public void setWidth(String width) {
        this.width = Integer.parseInt(width);
    }
    public void setHeight(String height) {
        this.height = Integer.parseInt(height);
    }
    public void setMines(String mines) {
        this.mines = Integer.parseInt(mines);
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getMines() {
        return mines;
    }
    public int getTileSize() {
        return tileSize;
    }
    public int getStartX() {
        return startX;
    }
    public int getStartY() {
        return startY;
    }


    public void calculateStartPositionAndSize(int widthIns, int heightIns) {
        // size 50 max width 26 max height 11
        // size 45 max width 29 max height 12
        // size 40 max width 33 max height 13
        // size 35 max width 38 max height 15
        // size 30 max width 45 max height 18
        // size 25 max width 53 max height 22
        // size 20 max width 66 max height 27
        // size 15 max width 88 max height 37


        if(widthIns<=26 || heightIns<=11){tileSize = 50;}
        if((widthIns<=29 && widthIns > 26) || (heightIns == 12)){tileSize = 45;}
        if((widthIns<=33 && widthIns > 29) || (heightIns == 13)){tileSize = 40;}
        if((widthIns<=38 && widthIns > 33) || (heightIns <=15 && heightIns >13)){tileSize = 35;}
        if((widthIns<=45 && widthIns > 38) || (heightIns <=18 && heightIns >15)){tileSize = 30;}
        if((widthIns<=53 && widthIns > 45) || (heightIns <=22 && heightIns >18)){tileSize = 25;}
        if((widthIns<=66 && widthIns > 53) || (heightIns <=27 && heightIns >22)){tileSize = 20;}
        if((widthIns<=88 && widthIns > 66) || (heightIns <=37 && heightIns >27)){tileSize = 15;}


        System.out.println(heightIns);
        System.out.println(widthIns);
        System.out.println(tileSize);
        startY=120;
        if(widthIns % 2==0){
            startX = 750 - (widthIns / 2)*tileSize;
        }
        else{
            startX = 750 - tileSize/2 - (widthIns/2)*tileSize;
        }

    }

    public void instaWinDev(Tile[][] mineFieldButtons){
        for (Tile[] mineFieldButton : mineFieldButtons) {
            for (Tile tile : mineFieldButton) {
                if (tile instanceof NumberTile) {
                    tile.setNumberImage();
                    ((NumberTile) tile).setClicked(true);


                }
                //mineFieldButtons[i][j].setEnabled(false);
            }
        }
    }

    public boolean checkForWireIndex(ArrayList<BombWire> bwArrayList, int index){
        for(BombWire b:bwArrayList){
            if(b.getIndex() == index){
                return true;
            }
        }
        return false;
    }
}
