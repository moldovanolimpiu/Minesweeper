import Tiles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class MinesweeperController {
    private MinesweeperModel model;
    private MinesweeperView view;
    private int height = 10;
    private int width = 10;
    private int flagged = 0;
    private int difficulty;
    private boolean mineFieldEnabled=true;
    private boolean devMode = false;
    private boolean expMines = false;
    private final Textures textures = new Textures();
    private Point[] pointsClicked;


    MinesweeperController(MinesweeperModel model, MinesweeperView view) {
        this.model = model;
        this.view = view;
        addListenerToEasy();
        addListenerToMedium();
        addListenerToHard();
        addListenerToCustomDifficulty();
        addListenerToActDeactDevMode();
        addListenerToActDeactExpMines();
        /*
        addListToRestart();
        for(int i = 0;i < height; i++){
            for(int j = 0;j < width;j++){
                addListMouseListener(i, j);
            }
        }

         */
    }

    public void setWidthHeight(int newWidth, int newHeight) {
        width = newWidth;
        height = newHeight;
        view.setWidthHeight(newWidth, newHeight);
    }
    public void start(){
        view.makeFrameVisible();
    }


    public void addListenerToActDeactDevMode(){
        view.addActDeactDevModeButtonListener(e->{
            devMode = !devMode;
            view.setDevMode(devMode);
            view.getActDeactDevModeButton().setBackground(devMode ? Color.red : Color.green);
            view.getActDeactDevModeButton().setText(devMode ? "Deactivate DevMode" : "Activate DevMode");
        });
    }

    public void addListenerToActDeactExpMines(){
        view.addActDeactExpMinesButtonListener(e->{
            expMines = !expMines;
            view.setExpMines(expMines);
            view.getActDeactExpMinesButton().setBackground(expMines ? Color.red : Color.green);
            view.getActDeactExpMinesButton().setText(expMines ? "Deactivate Experimental Mines" : "Activate Experimental Mines");
        });
    }

    public void addListenerToWinButton(){
        view.addWinButtonListener(e->{
            model.instaWinDev(view.getEntireMinefield());
            winningLogic();
        });
    }

    public void addListenerToBackToMenu(){
        view.addBackToMenuListener(e->{
            view.cleanSlate();
            view.initializeMainMenu();
            view.clearTilesClicked();
            view.getMineFieldPanel().setBackground(Color.WHITE);
            addListenerToEasy();
            addListenerToMedium();
            addListenerToHard();
            addListenerToCustomDifficulty();
            addListenerToActDeactDevMode();
            addListenerToActDeactExpMines();
            mineFieldEnabled=true;
        });
    }

    public void addListenerToEasy(){
        view.addEasyButtonListener(e->{
            view.cleanSlate();

            difficulty = 0;
            flagged = 0;
            setWidthHeight(8,8);
            view.setStartPosition(600,150);
            view.setTileSize(40);
            view.setMinesNumber(10);
            view.initializeMineField();
            //view.addUIComponentsOnFrame();
            for(int i = 0;i < height; i++){
                for(int j = 0;j < width;j++){
                    addListMouseListener(i, j);
                }
            }
            addListenerToBackToMenu();
            if(devMode){
                addListenerToWinButton();
            }
        });
    }
    public void addListenerToMedium(){
        view.addMediumButtonListener(e->{
           view.cleanSlate();
            difficulty = 1;
            flagged = 0;
            setWidthHeight(14,15);
            view.setStartPosition(500,150);
            view.setTileSize(30);
            view.setMinesNumber(40);
            view.initializeMineField();
            //view.addUIComponentsOnFrame();
            for(int i = 0;i < height; i++){
                for(int j = 0;j < width;j++){
                    addListMouseListener(i, j);
                }
            }
            addListenerToBackToMenu();
            if(devMode){
                addListenerToWinButton();
            }
        });
    }
    public void addListenerToHard(){
        view.addHardButtonListener(e->{
            view.cleanSlate();
            difficulty = 2;
            flagged = 0;
            setWidthHeight(30,16);
            view.setStartPosition(300,150);
            view.setTileSize(30);
            view.setMinesNumber(99);
            view.initializeMineField();
            //view.addUIComponentsOnFrame();
            for(int i = 0;i < height; i++){
                for(int j = 0;j < width;j++){
                    addListMouseListener(i, j);
                }
            }
            addListenerToBackToMenu();
            if(devMode){
                addListenerToWinButton();
            }
        });
    }

    public void addListenerToLaunchButton(){
        view.addLaunchButtonListener(e->{
            String widthString = "";
            String heightString = "";
            String mineNumberString = "";
            try{

                widthString = view.getWidthText();
                heightString = view.getHeightText();
                mineNumberString = view.getMineNumberText();
                if(Integer.parseInt(mineNumberString)>(Integer.parseInt(widthString)*Integer.parseInt(heightString)-1)){
                    view.cleanSlate();
                    view.initializeSelectionScreen();
                    view.showError("Too many mines!");
                    addListenerToLaunchButton();
                    addListenerToBackToMenu();
                }
                else {
                    view.cleanSlate();
                    difficulty = 3;
                    setWidthHeight(Integer.parseInt(widthString), Integer.parseInt(heightString));
                    model.calculateStartPositionAndSize(Integer.parseInt(widthString), Integer.parseInt(heightString));
                    view.setMinesNumber(Integer.parseInt(mineNumberString));
                    view.setTileSize(model.getTileSize());
                    view.setStartPosition(model.getStartX(), model.getStartY());//75 120
                    view.initializeMineField();

                    for (int i = 0; i < height; i++) {
                        for (int j = 0; j < width; j++) {
                            addListMouseListener(i, j);
                        }
                    }
                    addListenerToBackToMenu();
                    if (devMode) {
                        addListenerToWinButton();
                    }
                }
            }
            catch(NumberFormatException ex){

                view.cleanSlate();
                view.initializeSelectionScreen();
                view.showError("Error, bad operand");
                addListenerToLaunchButton();
                addListenerToBackToMenu();


            }
        });
    }

    public void addListenerToCustomDifficulty(){
        view.addCustomDifficultyButtonListener(e->{
           view.cleanSlate();
           view.initializeSelectionScreen();
           addListenerToLaunchButton();
           addListenerToBackToMenu();
        });
    }

    private void winningLogic(){
        mineFieldEnabled=false;
        view.getMineFieldPanel().setBackground(Color.green);
        if(difficulty==0){
            view.getMineFieldPanel().add(view.getWinMessageEasy());
            view.getWinMessageEasy().setBounds(150,200,1400,180);
            view.getWinMessageEasy().setFont(new Font("Krungthep",Font.BOLD,170));
        }
        else if(difficulty==1){
            view.getMineFieldPanel().add(view.getWinMessageMedium());
            view.getWinMessageMedium().setBounds(110,200,1400,180);
            view.getWinMessageMedium().setFont(new Font("Krungthep",Font.BOLD,170));
        }
        else if(difficulty==2){
            view.getMineFieldPanel().add(view.getWinMessageHard());
            view.getWinMessageHard().setBounds(50,300,1500,180);
            view.getWinMessageHard().setFont(new Font("Krungthep",Font.BOLD,80));
        }
        if(devMode){
            view.getWinButton().setEnabled(false);
        }
    }
    public void addListMouseListener(int i, int j){
        view.addGridMouseListener(i,j,new MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(!mineFieldEnabled){
                    return;
                }
                if(evt.getButton() == MouseEvent.BUTTON1){
                    if(view.getMineFieldButton(i,j) instanceof BombTile && !((BombTile) view.getMineFieldButton(i,j)).isFlagged()){
                        //view.getMineFieldButton(i,j).setBombImage();

                        view.revealMineField();
                        view.getMineFieldPanel().setBackground(Color.red);
                        mineFieldEnabled=false;
                        if(devMode){
                            view.getWinButton().setEnabled(false);
                        }

                        if(difficulty == 0) {
                            view.getMineFieldPanel().add(view.getLossMessageEasy());
                            view.getLossMessageEasy().setBounds(150, 200, 1400, 180);
                            view.getLossMessageEasy().setFont(new Font("Krungthep", Font.BOLD, 170));
                        }
                        else if(difficulty==1){
                                view.getMineFieldPanel().add(view.getLossMessageMedium());
                                view.getLossMessageMedium().setBounds(110, 200, 1400, 180);
                                view.getLossMessageMedium().setFont(new Font("Krungthep", Font.BOLD, 170));
                        } else if(difficulty==2){
                                    view.getMineFieldPanel().add(view.getLossMessageHard());
                                    view.getLossMessageHard().setBounds(50, 300, 1500, 180);
                                    view.getLossMessageHard().setFont(new Font("Krungthep", Font.BOLD, 80));
                        }
                    }
                    if(view.getMineFieldButton(i,j) instanceof StunBombTile && !((StunBombTile) view.getMineFieldButton(i,j)).isFlagged()){
                        view.getMineFieldButton(i,j).setBombImage();
                        System.out.println("Stunned");
                        view.reTileNumbers();
                        view.getMineFieldButton(i,j).setClicked(true);
                        flagged = 0;
                        view.setFlaggedCounter(0);
                        System.out.println(((StunBombTile) view.getMineFieldButton(i,j)).getClicked());
                    }
                    if(view.getMineFieldButton(i,j) instanceof RustBombTile && !((RustBombTile) view.getMineFieldButton(i,j)).isFlagged()){
                        view.getMineFieldButton(i,j).setBombImage();
                        System.out.println("Rust mine triggered");
                        view.getMineFieldButton(i,j).setClicked(true);
                        openRustMiniGame();
                    }
                    if(view.getMineFieldButton(i,j) instanceof NumberTile) {

                        if (!((NumberTile) view.getMineFieldButton(i, j)).isFlagged()) {
                            if(!((NumberTile) view.getMineFieldButton(i,j)).isClicked()){
                                if(((NumberTile) view.getMineFieldButton(i,j)).getNumNeighbours()==0){
                                    boolean[][] visited = new boolean[height][width];

                                    view.clearBlank(view.getEntireMinefield(),i,j,visited);
                                    //((Tiles.NumberTile) view.getMineFieldButton(i, j)).setClicked(true);
                                    System.out.println(view.getNumbersClicked());
                                }else {
                                    view.addTilesClicked(i,j);
                                    view.printTilesClicked();
                                    view.setNumbersClicked(view.getNumbersClicked() + 1);
                                    view.getMineFieldButton(i, j).setNumberImage();
                                    ((NumberTile) view.getMineFieldButton(i, j)).setClicked(true);
                                    System.out.println(view.getNumbersClicked());

                                    if (view.getNumbersClicked() == (height * width) - view.getMinesNumber()) {
                                        winningLogic();
                                    }
                                }
                            }


                        }
                    }
                }
                else if(evt.getButton() == MouseEvent.BUTTON3){

                    if(view.getMineFieldButton(i,j) instanceof NumberTile && !((NumberTile) view.getMineFieldButton(i,j)).isClicked()){
                        if(!((NumberTile) view.getMineFieldButton(i,j)).isFlagged()) {
                            view.getMineFieldButton(i, j).setTextureImage(textures.getFlagTexture());
                            ((NumberTile) view.getMineFieldButton(i, j)).setFlagged(true);
                            view.addTilesFlagged(i,j);
                            flagged++;
                            view.setFlaggedCounter(flagged);
                        }
                        else {
                            if (((NumberTile) view.getMineFieldButton(i, j)).isFlagged()) {

                                ((NumberTile) view.getMineFieldButton(i, j)).setTextureImage(textures.getTileTexture());
                                ((NumberTile) view.getMineFieldButton(i, j)).setFlagged(false);
                                flagged--;
                                view.setFlaggedCounter(flagged);
                            }
                        }
                    }

                    if(view.getMineFieldButton(i,j) instanceof BombTile){
                        if(!((BombTile) view.getMineFieldButton(i,j)).isFlagged()) {
                            view.getMineFieldButton(i, j).setTextureImage(textures.getFlagTexture());
                            ((BombTile) view.getMineFieldButton(i, j)).setFlagged(true);
                            flagged++;
                            view.setFlaggedCounter(flagged);
                        }
                        else if (((BombTile) view.getMineFieldButton(i, j)).isFlagged()) {
                                System.out.println("Ajunge aici");
                                ((BombTile) view.getMineFieldButton(i, j)).setTextureImage(textures.getTileTexture());
                                ((BombTile) view.getMineFieldButton(i, j)).setFlagged(false);
                                flagged--;
                                view.setFlaggedCounter(flagged);
                        }
                    }
                    if(view.getMineFieldButton(i,j) instanceof StunBombTile){
                        if(!((StunBombTile) view.getMineFieldButton(i,j)).isFlagged() && !((StunBombTile) view.getMineFieldButton(i,j)).isClicked()){
                            view.getMineFieldButton(i, j).setTextureImage(textures.getFlagTexture());
                            ((StunBombTile) view.getMineFieldButton(i,j)).setFlagged(true);
                            flagged++;
                            view.setFlaggedCounter(flagged);
                        }
                        else if(((StunBombTile) view.getMineFieldButton(i,j)).isFlagged()){
                            ((StunBombTile) view.getMineFieldButton(i, j)).setTextureImage(textures.getTileTexture());
                            ((StunBombTile) view.getMineFieldButton(i, j)).setFlagged(false);
                            flagged--;
                            view.setFlaggedCounter(flagged);
                        }
                    }
                    if(view.getMineFieldButton(i,j) instanceof RustBombTile){
                        if(!((RustBombTile) view.getMineFieldButton(i,j)).isFlagged() && !((RustBombTile) view.getMineFieldButton(i,j)).isClicked()){
                            view.getMineFieldButton(i, j).setTextureImage(textures.getFlagTexture());
                            ((RustBombTile) view.getMineFieldButton(i,j)).setFlagged(true);
                            flagged++;
                            view.setFlaggedCounter(flagged);
                        }
                        else if(((RustBombTile) view.getMineFieldButton(i,j)).isFlagged()){
                            ((RustBombTile) view.getMineFieldButton(i,j)).setTextureImage(textures.getTileTexture());
                            ((RustBombTile) view.getMineFieldButton(i,j)).setFlagged(false);
                            flagged--;
                            view.setFlaggedCounter(flagged);
                        }
                    }

                }
            }


        });
    }

    public void openRustMiniGame(){
        JDialog lockedDialog = new JDialog((JFrame) null, "Locked Tab", true);
        lockedDialog.setSize(500, 500);
        lockedDialog.setLocationRelativeTo(null);
        Random rand = new Random();
        AtomicBoolean timerStop = new AtomicBoolean(false);
        int randomInt;

        lockedDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        JButton closeButton = new JButton("Close Normally");
        JLabel timerLabel = new JLabel("00:10");
        timerLabel.setFont(new Font("Krungthep",Font.BOLD,30));
        timerLabel.setForeground(Color.RED);

        ArrayList<BombWire> bombWireArrayList = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            do {
                randomInt = rand.nextInt(4);
            } while (this.model.checkForWireIndex(bombWireArrayList, randomInt));
            bombWireArrayList.add(new BombWire(textures.getBombWireTextureFull(),randomInt));
            System.out.println(randomInt);
        }


        int xwire=100;
        AtomicInteger orderCounter = new AtomicInteger();
        for(BombWire b: bombWireArrayList){
            b.setBounds(xwire,270,70,70);
            JLabel numberLabel = new JLabel();
            numberLabel.setBounds(xwire+30,240,10,10);
            numberLabel.setText((b.getIndex()+1)+"");
            xwire += 70;
            b.addActionListener(bev -> {
                b.setBombImage();
                if(b.getIndex() == orderCounter.get()){
                    orderCounter.getAndIncrement();
                    if(orderCounter.get() == 4){
                        System.out.println("You defused the bomb");
                        lockedDialog.dispose();
                        timerStop.set(true);
                    }
                }
                else{
                    System.out.println("Mine exploded");
                    view.revealMineField();
                    mineFieldEnabled=false;
                    lockedDialog.dispose();
                    view.getMineFieldPanel().setBackground(Color.red);
                }
            });
            lockedDialog.add(b);
            lockedDialog.add(numberLabel);
        }



        AtomicInteger timesec = new AtomicInteger(10);
        closeButton.addActionListener(ev -> lockedDialog.dispose());
        lockedDialog.setLayout(null);
        closeButton.setBounds(100,400,200,40);
        timerLabel.setBounds(210,60,100,40);

        if(devMode){
            lockedDialog.add(closeButton);
        }

        lockedDialog.add(timerLabel);
        Timer bombTimer = new Timer(1000, e->{
            timesec.getAndDecrement();
            timerLabel.setText("00:0" + timesec);
            if(timerStop.get()){
                ((Timer) e.getSource()).stop();
            }
            if(timesec.get()==0){
                ((Timer) e.getSource()).stop();
                view.revealMineField();
                mineFieldEnabled=false;
                lockedDialog.dispose();
                view.getMineFieldPanel().setBackground(Color.red);
            }

        });


        bombTimer.start();
        lockedDialog.setVisible(true);
    }
}


