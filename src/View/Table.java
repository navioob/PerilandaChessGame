package View;

import Controller.ChessController;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains the main view of the the GUI for Perilanda Chess game.
 *
 * @author Seng Weng Hoong
 * @author Low Seh Hong
 * @author Kuan Wei Ben
 * @author Boo Ee Kein Ivan
 */


public class Table {

    public static Panelling panel;
    public static ArrayList<ImageIcon> icon;
    public BoardPanel p = new BoardPanel();
    public StatePanel statePanel;
    private final static Dimension BOARD_PANEL_DIMENSION = new Dimension(600, 600);
    private final static Dimension BOARD_PANEL_DIMENSION2 = new Dimension(135, 600);
    public static ChessController cc;

    /**
     * The constructor construct by getting ChessController object and Panelling object
     *
     * @author Kuan Wei Ben
     * @param cc an instance of ChessController
     * @param panel an instance of Panelling
     */
    public Table(ChessController cc, Panelling panel) {
        this.panel = panel;
        this.cc = cc;
        statePanel = new StatePanel(cc);
    }

    /**
     * Prints "Game Over!" message box
     *
     * @author Kuan Wei Ben
     *
     */
    public static void infoBox(String infoMessage)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "Game Over! ", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Prints "File Saved!" message box
     *
     * @author Kuan Wei Ben
     *
     */
    public static void saveBox(String infoMessage)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "File Saved! ", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This class initializes BoardPanel that shows the view of ChessBoard
     *
     * @author Kuan Wei Ben
     * @author Boo Ee Kein Ivan
     */

//Setting the Chess Panels
    public class BoardPanel extends JPanel{
        BoardPanel(){
            super(new GridLayout(7,7,5,5));
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            setBackground(Color.BLACK);
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();

            //Getting the JLabels that are meant for ChessPieces from Panelling and adds them into the Jpanel
            for (int i = 0; i <49; i++){
                add(Panelling.labels.get(i));
            }
        }

    }

    /**
     * This class is a panel that shows the state of a game
     *
     * @author	   Low Seh Hong
     */
    public class StatePanel extends JPanel{

        private JButton newGameButton;
        private JButton loadGameButton;
        private JButton saveGameButton;
        public JLabel playerTurn;
        public ChessController controls;

        public StatePanel(ChessController controls)
        {
            newGameButton = new JButton();
            loadGameButton = new JButton();
            saveGameButton = new JButton();
            playerTurn = new JLabel();
            this.controls = controls;
            intitialize(controls);
            setPreferredSize(BOARD_PANEL_DIMENSION2);
        }

        /**
         * To associate the instance of the class ChessController to this view class
         *
         * @author	   Low Seh Hong
         * @param      controls an instance of the class ChessController
         *
         */
        public void setChessController(ChessController controls){
            this.controls = controls;
        }

        /**
         * Initialize the StatePanel
         *
         * @author	   Low Seh Hong
         * @param      cc an instance of the class ChessController
         *
         */
        private void intitialize(ChessController cc) {
            setLayout(new GridLayout(4, 1));
            JButton newGameButton = new JButton("New Game");
            newGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //To call newGame function
                    cc.newGame();
                }
            });
            add(newGameButton);

            JButton saveGameButton = new JButton("Save Game");
            saveGameButton.addActionListener(new ActionListener() {
                //To call saveGame function
                @Override
                public void actionPerformed(ActionEvent e) {
                    String path = "src\\GameFile\\";
                    String game = "Game";
                    String countGameFile = "CountGame.txt";
                    try {
                        File file = new File("src\\GameFile\\CountGame.txt");
                        boolean exists = file.exists();
                        if(!exists){
                            file = new File("GameFile\\CountGame.txt");
                            path = "GameFile\\";
                        }

                        Scanner sf = new Scanner(file);
                        Integer numGameSaved = sf.nextInt();
                        Integer recordCount = numGameSaved +1;
                        sf.close();
                        String totalGameSaved = numGameSaved.toString();
                        String text = ".txt";
                        String saveFileName = path + game + totalGameSaved + text;
                        String gameName = game + totalGameSaved +text;
                        File saveFile = new File(saveFileName);
                        controls.saveGame(saveFile,gameName);

                        file.delete();

                        String writeToNewFile = path + countGameFile;
                        FileWriter fw = new FileWriter(writeToNewFile);
                        fw.write(String.valueOf(recordCount));

                        fw.close();
                    }

                    catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            });
            add(saveGameButton);

            JButton loadGameButton = new JButton("Load Game");
            loadGameButton.addActionListener(new ActionListener() {
                //To call load game function
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fl = new JFileChooser(new File("src\\GameFile"));
                    File file =new File("src\\GameFile");
                    boolean exists = file.exists();
                    if(!exists){
                        fl = new JFileChooser(new File("GameFile"));
                    }


                    FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                    fl.setFileFilter(filter);
                    fl.setDialogTitle("Choose a game to load");
                    fl.setDialogType(JFileChooser.OPEN_DIALOG);
                    int returnLoadFileValue = fl.showOpenDialog(null);
                    if (returnLoadFileValue == JFileChooser.APPROVE_OPTION) {
                        try {
                            File loadFileName = fl.getSelectedFile();


                            //Call loadGame function from StateController.java
                            controls.loadGame(loadFileName);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
            add(loadGameButton);


            add(Panelling.playerTurn);
        }
    }

    public static class MyMouseListener implements MouseListener{
        //contains the index of the JLabel to be able to pass it to the ChessBoard
        int labelNumber;

        public MyMouseListener(int labelNumber){
            this.labelNumber = labelNumber;
        }
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            //Passing the index to ChessPressed function
            cc.chessPressed(labelNumber);
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }





}
