import Tiles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MinesweeperView extends JFrame {
    private static final String INITIAL_VALUE = "";

    private JPanel mineFieldPanel;
    private Tile textureTest;
    private Textures textures = new Textures();
    private JButton resetButton;
    private boolean devMode = false;
    private boolean expMines = false;
    //main menu
    private JLabel titleLabel;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JButton customButton;
    private JButton actDeactDevModeButton;
    private JButton experimentalMinesButton;

    //minefield
    private Tile[][] mineFieldButtons;
    private JButton backToMenuButton;
    private JButton winButton;
    private JLabel backToMenuLabel;
    private JLabel winningMessage;
    private JLabel lossMessageEasy;
    private JLabel lossMessageMedium;
    private JLabel lossMessageHard;
    private JLabel lossMessageCustom;
    private JLabel winMessageEasy;
    private JLabel winMessageMedium;
    private JLabel winMessageHard;
    private JLabel winMessageCustom;
    private JLabel mineCounter;
    private JLabel flaggedCounter;
    private int height = 10;
    private int width = 10;
    private int startX;
    private int startY;
    private int tileSize;
    private int mineNumber;
    private int numbersClicked = 0;
    private ArrayList<Point> listTilesClicked;
    private ArrayList<Point> listPlacedFlags;

    //selection screen
    private JTextField heightTextField;
    private JTextField widthTextField;
    private JTextField mineNumberTextField;
    private JLabel heightLabel;
    private JLabel widthLabel;
    private JLabel mineNumberLabel;
    private JButton launchButton;

    //selection screen
    private JLabel widthWarningLabel;
    private JLabel heightWarningLabel;


    MinesweeperView() {
        initializePanel();
        initializeMainMenu();
        listTilesClicked = new ArrayList<>();
        listPlacedFlags = new ArrayList<>();

    }

    void makeFrameVisible() {this.setVisible(true);}

    void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }

    //LISTENER SECTION

    void addGridMouseListener(int i, int j, MouseAdapter gridtst){
        mineFieldButtons[i][j].addMouseListener(gridtst);
    }
    void addBackToMenuListener(ActionListener resettst){
        backToMenuButton.addActionListener(resettst);
    }

    void addEasyButtonListener(ActionListener easytst){
        easyButton.addActionListener(easytst);
    }
    void addMediumButtonListener(ActionListener mediumtst){
        mediumButton.addActionListener(mediumtst);
    }
    void addHardButtonListener(ActionListener hardtst){
        hardButton.addActionListener(hardtst);
    }
    void addCustomDifficultyButtonListener(ActionListener customtst){
        customButton.addActionListener(customtst);
    }
    void addLaunchButtonListener(ActionListener launchtst){
        launchButton.addActionListener(launchtst);
    }
    void addActDeactDevModeButtonListener(ActionListener actDeactDevModeButtontst){
        actDeactDevModeButton.addActionListener(actDeactDevModeButtontst);
    }

    void addActDeactExpMinesButtonListener(ActionListener actDeactExpMinesButtontst){
        experimentalMinesButton.addActionListener(actDeactExpMinesButtontst);
    }

    void addWinButtonListener(ActionListener wintst){
        winButton.addActionListener(wintst);
    }

    //SETTERS AND GETTERS
    public JPanel getMineFieldPanel() {return mineFieldPanel;}
    public int getMineFieldWidth() {return width;}
    public int getMineFieldHeight() {return height;}
    public Tile[][] getEntireMinefield(){return mineFieldButtons;}
    public void setWidthHeight(int newWidth, int newHeight) {
        width = newWidth;
        height = newHeight;

    }
    public void setStartPosition(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }
    public void setTileSize(int tileSize) {this.tileSize = tileSize;}
    public void setMinesNumber(int mineNumber) {this.mineNumber = mineNumber;}
    public int getMinesNumber() {return mineNumber;}
    public void setNumbersClicked(int numbersClicked) {this.numbersClicked = numbersClicked;}
    public int getNumbersClicked() {return numbersClicked;}
    public JLabel getWinningMessage() {return winningMessage;}
    public JLabel getLossMessageEasy(){return lossMessageEasy;}
    public JLabel getLossMessageMedium(){return lossMessageMedium;}
    public JLabel getLossMessageHard(){return lossMessageHard;}
    public JLabel getWinMessageEasy(){return winMessageEasy;}
    public JLabel getWinMessageMedium(){return winMessageMedium;}
    public JLabel getWinMessageHard(){return winMessageHard;}
    String getWidthText(){return widthTextField.getText();}
    String getHeightText(){return heightTextField.getText();}
    String getMineNumberText(){return mineNumberTextField.getText();}
    public JButton getActDeactDevModeButton() {return actDeactDevModeButton;}
    public JButton getActDeactExpMinesButton() {return experimentalMinesButton;}
    public boolean getDevMode() {return devMode;}
    public void setDevMode(boolean devMode) {this.devMode = devMode;}
    public JButton getWinButton() {return winButton;}
    public JLabel getFlaggedCounter() {return flaggedCounter;}
    public void setFlaggedCounter(int newVal) {flaggedCounter.setText(String.format("Flagged: %d", newVal));}
    public ArrayList<Point> getListTilesClicked() {return listTilesClicked;}
    public void clearTilesClicked() {listTilesClicked.clear();}
    public void addTilesClicked(int i, int j){
        listTilesClicked.add(new Point(i, j));
    }
    public void addTilesFlagged(int i, int j){
        listPlacedFlags.add(new Point(i,j));
    }
    public void clearTilesFlagged(){
        listPlacedFlags.clear();

    }
    public void printTilesClicked() {
        for (Point p : listTilesClicked) {
            System.out.println(p.getX() + " " + p.getY());
        }
    }
    public void setExpMines(boolean expMinesMode) {this.expMines = expMinesMode;}

    public ArrayList<Point> getListPlacedFlags(){ return listPlacedFlags;}
    public void clearPlacedFlags() {listPlacedFlags.clear();}
    



    public void reTileNumbers(){
        if(!listTilesClicked.isEmpty()){
            Random rand = new Random();
            int listLength = listTilesClicked.size();
            int randomInt;
            for (int i = 0; i < listLength/2; i++) {
                randomInt = rand.nextInt(listTilesClicked.size());
                Point p = listTilesClicked.remove(randomInt);
                mineFieldButtons[p.getX()][p.getY()].setTileTexture();
                mineFieldButtons[p.getX()][p.getY()].setClicked(false);
            }
        }
        if(!listPlacedFlags.isEmpty()){
            for (Point p : listPlacedFlags) {
                mineFieldButtons[p.getX()][p.getY()].setTileTexture();
                mineFieldButtons[p.getX()][p.getY()].setClicked(false);
                mineFieldButtons[p.getX()][p.getY()].setFlagged(false);
            }
        }
        //listTilesClicked.clear();
    }

    private boolean checkPoints(Point[] p, int size, int x, int y){
        for(int i=0;i<size;i++){
            if(p[i].getX() == x && p[i].getY() == y){
                return false;
            }
        }
        return true;
    }

    private void generateMines(Tile[][] mineFieldButtons, int nrOfMines){
        int x,y,i;
        Random rand = new Random();
        int nrStunMines;
        int nrRustMines;

        if(expMines){
            nrStunMines = 3;
            nrRustMines = 3;
        }else{
            nrStunMines = 0;
            nrRustMines = 0;
        }
        System.out.println(nrStunMines);
        int nrRegMines = nrOfMines - nrStunMines-nrRustMines;
        Point[] points = new Point[nrRegMines];
        for(i=0;i<nrRegMines;i++){
            do {
                x = rand.nextInt(height);
                y = rand.nextInt(width);
            }while(!checkPoints(points,i,x,y));
            points[i] = new Point(x,y);
        }
        for(i=0;i<nrRegMines;i++){
            mineFieldButtons[points[i].getX()][points[i].getY()]= new BombTile(textures.getTileTexture(),points[i].getX(),points[i].getY());
        }
        Point[] sm_points = new Point[nrStunMines];
        for(i=0;i<nrStunMines;i++){

            do{
                x = rand.nextInt(height);
                y = rand.nextInt(width);
            }while(!checkPoints(sm_points, i, x,y) || !checkPoints(points,points.length,x,y));
            sm_points[i] = new Point(x,y);
            //System.out.println(sm_points[i].getX()+" "+sm_points[i].getY());
        }
        for(i=0;i<nrStunMines;i++){
            mineFieldButtons[sm_points[i].getX()][sm_points[i].getY()] = new StunBombTile(textures.getTileTexture(),sm_points[i].getX(),sm_points[i].getY());
        }
        Point[] rm_points = new Point[nrRustMines];
        for(i=0;i<nrRustMines;i++){
            do{
                x = rand.nextInt(height);
                y = rand.nextInt(width);
            }while(!checkPoints(rm_points,i,x,y) || !checkPoints(sm_points,sm_points.length,x,y) ||!checkPoints(points,points.length,x,y));
            rm_points[i] = new Point(x,y);
        }
        for(i=0;i<nrRustMines;i++){
            mineFieldButtons[rm_points[i].getX()][rm_points[i].getY()] =  new RustBombTile(textures.getTileTexture(),rm_points[i].getX(),rm_points[i].getY());
        }
    }

    public void  generatePlayingField(Tile[][] mineFieldButtons){
        generateMines(mineFieldButtons, mineNumber);
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(!(mineFieldButtons[i][j] instanceof BombTile) && !(mineFieldButtons[i][j] instanceof  StunBombTile) &&
                !(mineFieldButtons[i][j] instanceof  RustBombTile)){
                    mineFieldButtons[i][j] = new NumberTile(textures.getTileTexture(),i,j);
                    mineFieldButtons[i][j].calculateNumTexture(mineFieldButtons);
                }

            }
        }

    }



    public void revealMineField(){
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(mineFieldButtons[i][j] instanceof NumberTile){
                    mineFieldButtons[i][j].setNumberImage();
                }else if(mineFieldButtons[i][j] instanceof BombTile){
                        mineFieldButtons[i][j].setBombImage();
                }
                else if(mineFieldButtons[i][j] instanceof StunBombTile){
                        mineFieldButtons[i][j].setBombImage();
                }
                else if(mineFieldButtons[i][j] instanceof RustBombTile){
                        mineFieldButtons[i][j].setBombImage();
                }
            }
        }
    }


    public void clearBlank(Tile[][] mineFieldButton,int i, int j, boolean[][] visited){
        if(i> height-1 || j>width-1||i<0||j<0){
            return;
        }
        if(visited[i][j]){
            return;
        }
        if(((NumberTile) mineFieldButton[i][j]).isFlagged() || ((NumberTile) mineFieldButton[i][j]).isClicked()){
            return;
        }
        visited[i][j]=true;

        if(mineFieldButton[i][j] instanceof NumberTile){
            if(((NumberTile) mineFieldButton[i][j]).getNumNeighbours()>0){
                mineFieldButton[i][j].setNumberImage();
                addTilesClicked(i,j);
                ((NumberTile) mineFieldButton[i][j]).setClicked(true);
                numbersClicked++;
                return;
            }
        }
        mineFieldButton[i][j].setNumberImage();
        ((NumberTile) mineFieldButton[i][j]).setClicked(true);
        numbersClicked++;

        clearBlank(mineFieldButton,i+1,j,visited);
        clearBlank(mineFieldButton,i,j+1,visited);
        clearBlank(mineFieldButton,i-1,j,visited);
        clearBlank(mineFieldButton,i,j-1,visited);

    }


    //menu generating functions
    public void cleanSlate(){
        mineFieldPanel.removeAll();
        mineFieldPanel.revalidate();
        mineFieldPanel.repaint();
    }

    private void initializePanel(){
        mineFieldPanel = new JPanel();
        mineFieldPanel.setLayout(null);



        this.setContentPane(mineFieldPanel);
        this.setSize(1500, 800);
        this.setTitle("Minesweeper");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void initializeMainMenu(){
        ImageIcon titleIcon = new ImageIcon("MSTextures\\titleImage.png");
        titleLabel = new JLabel(titleIcon);
        titleLabel.setBounds(300,30,900,80);
        easyButton = new JButton("Easy");
        easyButton.setForeground(Color.BLACK);
        easyButton.setBackground(Color.GREEN);
        easyButton.setBounds(600,180,300,70);
        mediumButton = new JButton("Medium");
        mediumButton.setBackground(Color.YELLOW);
        mediumButton.setBounds(600,270,300,70);
        hardButton = new JButton("Hard");
        hardButton.setBackground(Color.red);
        hardButton.setBounds(600,360,300,70);
        customButton = new JButton("Custom Difficulty");
        customButton.setBackground(Color.GRAY);
        customButton.setBounds(600,450,300,70);

        if(expMines){
            experimentalMinesButton = new JButton("Deactivate Experimental Mines");
            experimentalMinesButton.setBackground(Color.RED);
        }
        else{
            experimentalMinesButton = new JButton("Activate Experimental Mines");
            experimentalMinesButton.setBackground(Color.GREEN);
        }

        experimentalMinesButton.setBounds(600, 540, 300,50);
        if(devMode){
            actDeactDevModeButton = new JButton("Deactivate DevMode");
            actDeactDevModeButton.setBackground(Color.red);
        }
        else{
            actDeactDevModeButton = new JButton("Activate DevMode");
            actDeactDevModeButton.setBackground(Color.green);
        }
        actDeactDevModeButton.setBounds(600,600,300,50);

        mineFieldPanel.add(titleLabel);
        mineFieldPanel.add(easyButton);
        mineFieldPanel.add(mediumButton);
        mineFieldPanel.add(hardButton);
        mineFieldPanel.add(customButton);
        mineFieldPanel.add(actDeactDevModeButton);
        mineFieldPanel.add(experimentalMinesButton);
    }

    public void initializeSelectionScreen(){

        widthLabel = new JLabel("Field width");
        widthTextField = new JTextField(3);
        heightLabel = new JLabel("Field height");
        heightTextField = new JTextField(3);
        mineNumberLabel = new JLabel("Number of Mines");
        mineNumberTextField = new JTextField(3);
        launchButton = new JButton("Launch");
        backToMenuButton = new JButton("<- Back to Menu");
        widthWarningLabel = new JLabel("Widths above 88 may cause issues");
        heightWarningLabel = new JLabel("Heights above 37 may cause issues");

        widthLabel.setBounds(650,300,100,20);
        widthTextField.setBounds(760,300,50,20);
        heightLabel.setBounds(650,340,100,20);
        heightTextField.setBounds(760,340,50,20);
        mineNumberLabel.setBounds(650,380,100,20);
        mineNumberTextField.setBounds(760,380,50,20);
        launchButton.setBounds(640,420,180,50);
        backToMenuButton.setBounds(50,700,200,50);
        widthWarningLabel.setBounds(630,465,200,40);
        heightWarningLabel.setBounds(627,480,220,40);


        mineFieldPanel.add(widthLabel);
        mineFieldPanel.add(widthTextField);
        mineFieldPanel.add(heightLabel);
        mineFieldPanel.add(heightTextField);
        mineFieldPanel.add(mineNumberLabel);
        mineFieldPanel.add(mineNumberTextField);
        mineFieldPanel.add(launchButton);
        mineFieldPanel.add(backToMenuButton);
        mineFieldPanel.add(widthWarningLabel);
        mineFieldPanel.add(heightWarningLabel);
    }
    public void initializeMineField() {
        ImageIcon titleIcon = new ImageIcon("MSTextures\\titleImage.png");
        titleLabel = new JLabel(titleIcon);
        titleLabel.setBounds(300,30,900,80);
        mineFieldButtons = new Tile[1000][1000];
        generatePlayingField(mineFieldButtons);
        numbersClicked = 0;
        for(int i =0;i<height;i++){
            for(int j=0;j<width;j++){

                mineFieldButtons[i][j].setPreferredSize(new Dimension(tileSize,tileSize));
                mineFieldButtons[i][j].setBounds(startX+j*tileSize,startY+i*tileSize,mineFieldButtons[i][j].getPreferredSize().width,mineFieldButtons[i][j].getPreferredSize().height);
            }
        }
        lossMessageEasy = new JLabel("YOU         LOST!");
        lossMessageEasy.setForeground(Color.WHITE);
        lossMessageMedium = new JLabel("YOU          LOST!");
        lossMessageMedium.setForeground(Color.WHITE);
        lossMessageHard = new JLabel("YOU                                            LOST!");
        lossMessageHard.setForeground(Color.WHITE);

        winMessageEasy = new JLabel("YOU         WON!");
        winMessageEasy.setForeground(Color.WHITE);
        winMessageMedium = new JLabel("YOU          WON!");
        winMessageMedium.setForeground(Color.WHITE);
        winMessageHard = new JLabel("YOU                                            WON!");
        winMessageHard.setForeground(Color.WHITE);


        mineCounter = new JLabel(String.format("Mines: %d",mineNumber));
        flaggedCounter = new JLabel("Flagged: 0");
        resetButton = new JButton("reset");
        backToMenuButton = new JButton("<- Back to Menu");
        backToMenuLabel = new JLabel("WARNING: If you leave, your progress will not be saved");
        winningMessage = new JLabel("You Won");

        for(int i =0;i<height;i++){
            for(int j=0;j<width;j++){
                mineFieldPanel.add(mineFieldButtons[i][j]);
            }
        }
        mineFieldPanel.add(titleLabel);
        mineFieldPanel.add(backToMenuButton);
        mineFieldPanel.add(backToMenuLabel);
        mineFieldPanel.add(mineCounter);
        mineFieldPanel.add(flaggedCounter);
        //mineFieldPanel.add(winningMessage);
        backToMenuButton.setBounds(50,700,200,50);
        backToMenuLabel.setBounds(260,700,350,50);
        mineCounter.setBounds(70,45,200,50);
        mineCounter.setFont(new Font("Krungthep",Font.BOLD,30));
        flaggedCounter.setBounds(1230,45,200,50);
        flaggedCounter.setFont(new Font("Krungthep",Font.BOLD,30));
        //winningMessage.setBounds(600,700,300,50);

        if(devMode){
            winButton = new JButton("Win");
            winButton.setBackground(Color.red);
            mineFieldPanel.add(winButton);
            winButton.setBounds(1200,660,70,50);
        }




    }



    public Tile getTextureButton(){
        return textureTest;
    }

    public Tile getMineFieldButton(int i, int j){
        return mineFieldButtons[i][j];
    }


}
