package Tiles;

import javax.swing.*;
import java.awt.*;

public class Textures {
    private Image tileTexture = new ImageIcon("MSTextures\\blocktexture.jpg").getImage();
    private Image mineTexture = new ImageIcon("MSTextures\\minetexture.png").getImage();
    private Image flagTexture = new ImageIcon("MSTextures\\flagpole.jpg").getImage();
    private Image nr0texture = new ImageIcon("MSTextures\\nr0texture.jpg").getImage();
    private Image nr1texture = new ImageIcon("MSTextures\\nr1texture.png").getImage();
    private Image nr2texture = new ImageIcon("MSTextures\\nr2texture.png").getImage();
    private Image nr3texture = new ImageIcon("MSTextures\\nr3texture.png").getImage();
    private Image nr4texture = new ImageIcon("MSTextures\\nr4texture.png").getImage();
    private Image nr5texture = new ImageIcon("MSTextures\\nr5texture.png").getImage();
    private Image nr6texture = new ImageIcon("MSTextures\\nr6texture.png").getImage();
    private Image nr7texture = new ImageIcon("MSTextures\\nr7texture.png").getImage();
    private Image nr8texture = new ImageIcon("MSTextures\\nr8texture.png").getImage();
    private Image titleImage = new ImageIcon("MSTextures\\titleImage.png").getImage();
    private Image stunMineTexture = new ImageIcon("MSTextures\\stunminetexture.png").getImage();
    private Image rustMineTexture = new ImageIcon("MSTextures\\rustminetexture.png").getImage();
    private Image bombWireTextureFull = new ImageIcon("MSTextures\\bombwirefull.png").getImage();
    private Image bombWireTextureCut = new ImageIcon("MSTextures\\bombwirecut.png").getImage();

    public Image getTileTexture() {return tileTexture;}

    public Image getMineTexture() {return mineTexture;}

    public Image getFlagTexture() {return flagTexture;}

    public Image getTitleImage() {return titleImage;}

    public Image getStunMineTexture() {return stunMineTexture;}

    public Image getRustMineTexture() {return rustMineTexture;}

    public Image getBombWireTextureFull(){return bombWireTextureFull;}

    public Image getBombWireTextureCut(){return bombWireTextureCut;}

    public Image getNr0texture() {return nr0texture;}
    public Image getNr1Texture() {return nr1texture;}
    public Image getNr2Texture() {return nr2texture;}
    public Image getNr3Texture() {return nr3texture;}
    public Image getNr4Texture() {return nr4texture;}
    public Image getNr5Texture() {return nr5texture;}
    public Image getNr6Texture() {return nr6texture;}
    public Image getNr7Texture() {return nr7texture;}
    public Image getNr8Texture() {return nr8texture;}


}
