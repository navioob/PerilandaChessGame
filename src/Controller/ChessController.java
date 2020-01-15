package Controller;
import View.Table;

import Model.Chess.*;
import Model.MoveDirection;
import Model.Piece;
import Model.PieceColor;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This class is a singleton controller that controls the view and model of the game
 *
 * @author Seng Weng Hoong
 * @author Low Seh Hong
 * @author Kuan Wei Ben
 * @author Boo Ee Kein Ivan
 */


public class ChessController {
    public static ArrayList<Piece> board;
    public static Table view;
    private static ChessController controls = new ChessController();
    public static ArrayList<ImageIcon> icons = new ArrayList<ImageIcon>();
    private Integer step;
    private Integer greenStep;
    private Integer orangeStep;
    private ArrayList<Integer> finalMoves;
    private Integer source;
    private Piece currentPiece;
    private Integer destination;
    private Integer swapGreen;
    private Integer swapOrange;

    /**
     * Constructor: To initialize the state of the game
     * @author Boo Ee Kein Ivan
     *
     */
    public ChessController() {
        board = new ArrayList<Piece>(Collections.nCopies(49, null));
        this.step = 0;
        this.orangeStep = 0;
        this.greenStep = 0;
        this.finalMoves = new ArrayList<Integer>();
        this.source = null;
        this.currentPiece = null;
        this.destination = null;
        this.swapGreen = null;
        this.swapOrange = null;
    }

    /**
     * Returns the PieceType of the current turn
     * @author Seng Weng Hoong
     * @return PieceType
     *
     */
    public PieceColor getTurn()
    {
        if(step % 2 == 0)
        {
            return PieceColor.GREEN;
        }
        else
        {
            return PieceColor.ORANGE;
        }
    }

    /**
     * Returns the current Step of PieceColor Green
     * @author Seng Weng Hoong
     * @return greenStep
     *
     */
    public Integer getGreenStep()
    {
        return greenStep;
    }

    /**
     * Returns the current Step of PieceColor Orange
     * @author Seng Weng Hoong
     * @return orangeStep
     *
     */
    public Integer getOrangeStep()
    {
        return orangeStep;
    }

    /**
     * Returns the current Step of PieceColor Orange
     * @author Seng Weng Hoong
     * @param step
     *
     */
    public void setStep(Integer step)
    {
        this.step = step;
    }

    /**
     * Set the step for green to swap Tercel and Excel
     * @author Boo Ee Kein Ivan
     * @param swapGreen
     *
     */
    public void setSwapGreen(Integer swapGreen){
        this.swapGreen = swapGreen;
    }

    /**
     * Set the step for orange to swap Tercel and Excel
     * @author Boo Ee Kein Ivan
     * @param swapOrange
     *
     */
    public void setSwapOrange(Integer swapOrange){
        this.swapOrange = swapOrange;
    }

    /**
     * Add steps to the particular pieceColor
     * @author Seng Weng Hoong
     * @param pieceColor
     *
     */
    public void addStep(PieceColor pieceColor)
    {
        if(pieceColor == PieceColor.GREEN)
        {
            this.greenStep++;
        }
        else
        {
            this.orangeStep++;
        }
        this.step++;
    }

    /**
     * Taking attackingPos and determine their available final moves
     * @author Seng Weng Hoong
     * @param attackingPos
     *
     */
    public void setFinalMoves(ArrayList<Integer> attackingPos)
    {
        this.finalMoves = attackingPos;
    }

    /**
     * Reset the constructed values
     * @author Boo Ee Kein Ivan
     */
    public void resetState()
    {
        controls.source = null;
        controls.finalMoves.clear();
        controls.currentPiece = null;
        controls.destination = null;
    }

    /**
     * Set the instance of the ChessController
     * @author Boo Ee Kein Ivan
     */
    public void setClass(ChessController cc){
        controls = cc;
    }

    /**
     * Set the instance of the View
     * @author Boo Ee Kein Ivan
     */
    public void setView(Table view){
        this.view = view;
    }

    /**
     * To initialize the pieces of chess into the board
     * @author Boo Ee Kein Ivan
     */
    public void initialize() {
        board = new ArrayList<Piece>(Collections.nCopies(49, null));
        setIcons();

        board.set(0, new Tercel(0, PieceColor.ORANGE));
        board.set(1, new Excel(1, PieceColor.ORANGE));
        board.set(2, new Trident(2, PieceColor.ORANGE));
        board.set(3, new Chief(3, PieceColor.ORANGE));
        board.set(4, new Trident(4, PieceColor.ORANGE));
        board.set(5, new Excel(5, PieceColor.ORANGE));
        board.set(6, new Tercel(6, PieceColor.ORANGE));
        board.set(7, new Advancer(7, PieceColor.ORANGE));
        board.set(8, new Advancer(8, PieceColor.ORANGE));
        board.set(9, new Advancer(9, PieceColor.ORANGE));
        board.set(10, new Advancer(10, PieceColor.ORANGE));
        board.set(11, new Advancer(11, PieceColor.ORANGE));
        board.set(12, new Advancer(12, PieceColor.ORANGE));
        board.set(13, new Advancer(13, PieceColor.ORANGE));

        board.set(35, new Advancer(35, PieceColor.GREEN));
        board.set(36, new Advancer(36, PieceColor.GREEN));
        board.set(37, new Advancer(37, PieceColor.GREEN));
        board.set(38, new Advancer(38, PieceColor.GREEN));
        board.set(39, new Advancer(39, PieceColor.GREEN));
        board.set(40, new Advancer(40, PieceColor.GREEN));
        board.set(41, new Advancer(41, PieceColor.GREEN));
        board.set(42, new Tercel(42, PieceColor.GREEN));
        board.set(43, new Excel(43, PieceColor.GREEN));
        board.set(44, new Trident(44, PieceColor.GREEN));
        board.set(45, new Chief(45, PieceColor.GREEN));
        board.set(46, new Trident(46, PieceColor.GREEN));
        board.set(47, new Excel(57, PieceColor.GREEN));
        board.set(48, new Tercel(48, PieceColor.GREEN));
    }

    /**
     * To check if the moves that the Piece want to advance to is on the board
     * @author Seng Weng Hoong
     * @param distance increment of the movement
     * @param index source index
     * @param line number of line across
     * @return true if index is on board
     */
    public boolean isOnBoard(Integer index, Integer distance, Integer line) {
        Integer engageMove = index + distance;

        if (engageMove < 0 || engageMove > 48) {
            return false;
        } else {
            if (Math.abs(engageMove / 7 - index / 7) != line) {
                return false;
            }
        }

        return true;
    }

    /**
     * To get PieceType Chief's valid moves for checking
     * @author Seng Weng Hoong
     * @param index
     * @return indexes of valid movements
     */
    public ArrayList<Integer> ChiefValidMoves(Integer index) {
        ArrayList<Integer> pos = new ArrayList<Integer>();

        //up and down
        if (isOnBoard(index, -7, 1)) {
            if(board.get(index - 7) != null){
                if(board.get(index - 7).getPieceColor() != getTurn()) {
                    pos.add(index - 7);
                }
            }
            else
            {pos.add(index - 7);}
        }
        if (isOnBoard(index, +7, 1)) {
            if(board.get(index + 7) != null){
                if(board.get(index + 7).getPieceColor() != getTurn()) {
                    pos.add(index + 7);
                }
            }
            else
            {pos.add(index + 7);}
        }
        //left and right
        if (isOnBoard(index, +1, 0)) {
            if(board.get(index + 1) != null){
                if(board.get(index + 1).getPieceColor() != getTurn()) {
                    pos.add(index + 1);
                }
            }
            else
            {pos.add(index + 1);}
        }
        if (isOnBoard(index, -1, 0)) {
            if(board.get(index - 1) != null){
                if(board.get(index - 1).getPieceColor() != getTurn()) {
                    pos.add(index - 1);
                }
            }
            else
            {pos.add(index - 1);}
        }
        //diagonally
        if (isOnBoard(index, -6, 1)) {
            if(board.get(index -6) != null){
                if(board.get(index -6).getPieceColor() != getTurn()) {
                    pos.add(index -6);
                }
            }
            else
            {pos.add(index -6);}
        }
        if (isOnBoard(index, -8, 1)) {
            if(board.get(index -8) != null){
                if(board.get(index -8).getPieceColor() != getTurn()) {
                    pos.add(index -8);
                }
            }
            else
            {pos.add(index -8);}
        }
        if (isOnBoard(index, +6, 1)) {
            if(board.get(index +6) != null){
                if(board.get(index +6).getPieceColor() != getTurn()) {
                    pos.add(index +6);
                }
            }
            else
            {pos.add(index +6);}
        }
        if (isOnBoard(index, +8, 1)) {
            if(board.get(index +8) != null){
                if(board.get(index +8).getPieceColor() != getTurn()) {
                    pos.add(index +8);
                }
            }
            else
            {pos.add(index +8);}
        }

        return pos;
    }

    /**
     * To get PieceType Advancer's valid moves for checking
     * @author Seng Weng Hoong
     * @param index
     * @return indexes of valid movements
     */
    public ArrayList<Integer> AdvancerValidMoves(Integer index) {
        ArrayList<Integer> pos = new ArrayList<Integer>();
        if (board.get(index).getPieceColor() == PieceColor.GREEN||board.get(index).getPieceColor() == PieceColor.ORANGE) {
            if (index <= 6) {
                board.get(index).setMoveDirection(MoveDirection.BACKWARD);
            } else if (index >= 42) {
                board.get(index).setMoveDirection(MoveDirection.FORWARD);
            }
        }

        if ((board.get(index).getMoveDirection() == MoveDirection.FORWARD && board.get(index).getPieceColor() == PieceColor.GREEN) || (board.get(index).getMoveDirection() == MoveDirection.FORWARD && board.get(index).getPieceColor() == PieceColor.ORANGE)) {
            for (int i = 1; i < 3; i++) {
                if (isOnBoard(index, -7 * i, i)) {
                    if(board.get(index - 7*i) != null && board.get(index - 7*i).getPieceColor() == getTurn()) {
                        break;
                    }
                    if(board.get(index - 7*i) != null && board.get(index - 7*i).getPieceColor() != getTurn()) {
                        pos.add(index - 7*i);
                        break;
                    }
                    else {
                        pos.add(index - 7*i);
                    }
                }
            }
        } else if ((board.get(index).getMoveDirection() == MoveDirection.BACKWARD && board.get(index).getPieceColor() == PieceColor.GREEN) || (board.get(index).getMoveDirection() == MoveDirection.BACKWARD && board.get(index).getPieceColor() == PieceColor.ORANGE)) {
            for (int i = 1; i < 3; i++) {
                if (isOnBoard(index, 7 * i, i)) {
                    if(board.get(index + 7*i) != null && board.get(index + 7*i).getPieceColor() == getTurn()) {
                        break;
                    }
                    if(board.get(index + 7*i) != null && board.get(index + 7*i).getPieceColor() != getTurn()) {
                        pos.add(index + 7*i);
                        break;
                    }
                    else {
                        pos.add(index + 7*i);
                    }
                }
            }
        }

        return pos;
    }

    /**
     * To get PieceType Trident's valid moves for checking
     * @author Seng Weng Hoong
     * @param index
     * @return indexes of valid movements
     */
    public ArrayList<Integer> TridentValidMoves(Integer index){
        ArrayList<Integer> pos = new ArrayList<Integer>();
        if (board.get(index).getPieceColor() == PieceColor.GREEN||board.get(index).getPieceColor() == PieceColor.ORANGE) {
            if (index <= 6) {
                board.get(index).setMoveDirection(MoveDirection.BACKWARD);
            } else if (index >= 42) {
                board.get(index).setMoveDirection(MoveDirection.FORWARD);
            }
        }


        if ((board.get(index).getMoveDirection() == MoveDirection.FORWARD && board.get(index).getPieceColor() == PieceColor.GREEN) || (board.get(index).getMoveDirection() == MoveDirection.FORWARD && board.get(index).getPieceColor() == PieceColor.ORANGE)) {
            if (isOnBoard(index, -7 ,1)) {
                if(board.get(index - 7) != null){
                    if(board.get(index - 7).getPieceColor() != getTurn()) {
                        pos.add(index - 7);
                    }
                }
                else
                {pos.add(index - 7);}
            }

        } else if ((board.get(index).getMoveDirection() == MoveDirection.BACKWARD && board.get(index).getPieceColor() == PieceColor.GREEN) || (board.get(index).getMoveDirection() == MoveDirection.BACKWARD && board.get(index).getPieceColor() == PieceColor.ORANGE)) {

            if (isOnBoard(index, 7, 1)) {
                if(board.get(index + 7) != null){
                    if(board.get(index + 7).getPieceColor() != getTurn()) {
                        pos.add(index + 7);
                    }
                }
                else
                {pos.add(index + 7);}
            }
        }

        for(int i = 1; i <= 7; i++){
            if(isOnBoard(index,i,0)){
                if(board.get(index +i) != null && board.get(index +i).getPieceColor() == getTurn()) {
                    break;
                }
                if(board.get(index +i) != null && board.get(index +i).getPieceColor() != getTurn()) {
                    pos.add(index +i);
                    break;
                }
                else {
                    pos.add(index +i);
                }
            }
        }

        for(int i = -1; i >= -7; i--){
            if(isOnBoard(index,i,0)){
                if(board.get(index +i) != null && board.get(index +i).getPieceColor() == getTurn()) {
                    break;
                }
                if(board.get(index +i) != null && board.get(index +i).getPieceColor() != getTurn()) {
                    pos.add(index +i);
                    break;
                }
                else {
                    pos.add(index +i);
                }
            }
        }

        return pos;
    }

    /**
     * To get PieceType Excel's valid moves for checking
     * @author Seng Weng Hoong
     * @param index
     * @return indexes of valid movements
     */
    public ArrayList<Integer>ExcelValidMoves(Integer index){
        ArrayList<Integer>pos = new ArrayList<Integer>();
        //forward-right
        for (int i = 1; i < 7; i++) {
            if (isOnBoard(index, -6 * i, i)) {
                if(board.get(index -6*i) != null && board.get(index -6*i).getPieceColor() == getTurn()) {
                    break;
                }
                if(board.get(index -6*i) != null && board.get(index -6*i).getPieceColor() != getTurn()) {
                    pos.add(index -6*i);
                    break;
                }
                else {
                    pos.add(index -6*i);
                }
            }
        }

        //forward-left
        for (int i = 1; i < 7; i++) {
            if (isOnBoard(index, -8 * i, i)) {
                if(board.get(index -8*i) != null && board.get(index -8*i).getPieceColor() == getTurn()) {
                    break;
                }
                if(board.get(index -8*i) != null && board.get(index -8*i).getPieceColor() != getTurn()) {
                    pos.add(index -8*i);
                    break;
                }
                else {
                    pos.add(index -8*i);
                }
            }
        }

        //backwards-right
        for (int i = 1; i < 7; i++) {
            if (isOnBoard(index, 8 * i, i)) {
                if(board.get(index +8*i) != null && board.get(index +8*i).getPieceColor() == getTurn()) {
                    break;
                }
                if(board.get(index +8*i) != null && board.get(index +8*i).getPieceColor() != getTurn()) {
                    pos.add(index +8*i);
                    break;
                }
                else {
                    pos.add(index +8*i);
                }
            }
        }

        //backwards-left
        for (int i = 1; i < 7; i++) {
            if (isOnBoard(index, 6 * i, i)) {
                if(board.get(index +6*i) != null && board.get(index +6*i).getPieceColor() == getTurn()) {
                    break;
                }
                if(board.get(index +6*i) != null && board.get(index +6*i).getPieceColor() != getTurn()) {
                    pos.add(index +6*i);
                    break;
                }
                else {
                    pos.add(index +6*i);
                }
            }
        }

        return pos;
    }

    /**
     * To get PieceType Tercel's valid moves for checking
     * @author Seng Weng Hoong
     * @param index
     * @return indexes of valid movements
     */
    public ArrayList<Integer>TercelValidMoves(Integer index){
        ArrayList<Integer>pos = new ArrayList<Integer>();


        //right
        for(int i = 1; i <= 7; i++){
            if(isOnBoard(index,i,0)){
                if(board.get(index +i) != null && board.get(index +i).getPieceColor() == getTurn()) {
                    break;
                }
                if(board.get(index +i) != null && board.get(index +i).getPieceColor() != getTurn()) {
                    pos.add(index +i);
                    break;
                }
                else {
                    pos.add(index +i);
                }
            }
        }
        //left
        for(int i = -1; i >= -7; i--){
            if(isOnBoard(index,i,0)){
                if(board.get(index +i) != null && board.get(index +i).getPieceColor() == getTurn()) {
                    break;
                }
                if(board.get(index +i) != null && board.get(index +i).getPieceColor() != getTurn()) {
                    pos.add(index +i);
                    break;
                }
                else {
                    pos.add(index +i);
                }
            }
        }

        //forward
        for (int i = 1; i < 7; i++) {
            if (isOnBoard(index, -7 * i, i)) {
                if(board.get(index -7 * i) != null && board.get(index -7 * i).getPieceColor() == getTurn()) {
                    break;
                }
                if(board.get(index -7 * i) != null && board.get(index -7 * i).getPieceColor() != getTurn()) {
                    pos.add(index -7 * i);
                    break;
                }
                else {
                    pos.add(index -7 * i);
                }
            }
        }

        //backwards
        for (int i = 1; i < 7; i++) {
            if (isOnBoard(index, 7 * i, i)) {
                if(board.get(index +7 * i) != null && board.get(index +7 * i).getPieceColor() == getTurn()) {
                    break;
                }
                if(board.get(index +7 * i) != null && board.get(index +7 * i).getPieceColor() != getTurn()) {
                    pos.add(index +7 * i);
                    break;
                }
                else {
                    pos.add(index +7 * i);
                }
            }
        }

        return pos;
    }

    /**
     * To check if Green should swap pieces
     * @author Seng Weng Hoong
     * @return true if green excels and tercels should be swapped
     */
    public boolean shouldSwapGreen()
    {
        if(getGreenStep() > 0)
        {
            if(getGreenStep() % 3 == 0 )
            {
                return this.swapGreen % 3 != 0;
            }
        }
        else
        {
            return false;
        }
        return false;
    }

    /**
     * To check if Orange should swap pieces
     * @author Seng Weng Hoong
     * @return true if orange excels and tercels should be swapped
     */
    public boolean shouldSwapOrange()
    {
        if(getOrangeStep() > 0)
        {
            if(getOrangeStep() % 3 == 0)
            {
                return this.swapOrange % 3 != 0;
            }
        }
        else
        {
            return false;
        }
        return false;
    }

    /**
     * Method to swap Green excels and tercels
     * @author Seng Weng Hoong
     */
    public void swapGreen(){
        if(getGreenStep() % 3 == 0 ) {
            for(int i=0; i<board.size(); i++)
            {
                if(board.get(i) != null)
                {
                    if(board.get(i).getPieceType() == PieceType.EXCEL && board.get(i).getPieceColor() == PieceColor.GREEN)
                    {
                        board.set(i, new Tercel(i, PieceColor.GREEN));
                    }
                    else if(board.get(i).getPieceType() == PieceType.TERCEL && board.get(i).getPieceColor() == PieceColor.GREEN)
                    {
                        board.set(i, new Excel(i, PieceColor.GREEN));
                    }
                }
            }
        }
    }

    /**
     * Method to swap Orange excels and tercels
     * @author Seng Weng Hoong
     */
    public void swapOrange()
    {
        if(getOrangeStep() % 3 == 0){
            for(int i=0; i<board.size(); i++)
            {
                if(board.get(i) != null)
                {
                    if(board.get(i).getPieceType() == PieceType.EXCEL && board.get(i).getPieceColor() == PieceColor.ORANGE)
                    {
                        board.set(i, new Tercel(i, PieceColor.ORANGE));
                    }
                    else if(board.get(i).getPieceType() == PieceType.TERCEL && board.get(i).getPieceColor() == PieceColor.ORANGE)
                    {
                        board.set(i, new Excel(i, PieceColor.ORANGE));
                    }
                }
            }
        }
    }

    /**
     * To refresh the panel after each prompt
     * @author Boo Ee Kein Ivan
     */
    public void refreshPanel(){
        view.p.removeAll();

        for(int i=0; i<49; i++)
        {
            if(controls.board.get(i) == null) {
                view.panel.labels.get(i).setIcon(null);
            } else if(controls.board.get(i).getPieceColor() == PieceColor.GREEN)
            {
                if(controls.board.get(i).getPieceType() == PieceType.ADVANCER)
                {
                    if(controls.board.get(i).getMoveDirection() == MoveDirection.FORWARD) {
                        view.panel.labels.get(i).setIcon(icons.get(9));
                    }
                    else
                    {
                        view.panel.labels.get(i).setIcon(new ImageIcon(rotateIcon(imageToBufferedImage(controls.icons.get(9)), -Math.PI/1)));
                    }
                }
                else if(controls.board.get(i).getPieceType() == PieceType.CHIEF)
                {
                    view.panel.labels.get(i).setIcon(icons.get(8));
                }
                else if(controls.board.get(i).getPieceType() == PieceType.EXCEL)
                {
                    view.panel.labels.get(i).setIcon(icons.get(5));
                }
                else if(controls.board.get(i).getPieceType() == PieceType.TERCEL)
                {
                    view.panel.labels.get(i).setIcon(icons.get(6));
                }
                else if(controls.board.get(i).getPieceType() == PieceType.TRIDENT)
                {
                    if(controls.board.get(i).getMoveDirection() == MoveDirection.FORWARD) {
                        view.panel.labels.get(i).setIcon(icons.get(7));
                    }
                    else
                    {
                        view.panel.labels.get(i).setIcon(new ImageIcon(rotateIcon(imageToBufferedImage(controls.icons.get(7)), -Math.PI/1)));
                    }
                }
            }
            else if(controls.board.get(i).getPieceColor() == PieceColor.ORANGE)
            {
                if(controls.board.get(i).getPieceType() == PieceType.ADVANCER)
                {
                    if(controls.board.get(i).getMoveDirection() == MoveDirection.FORWARD) {
                        view.panel.labels.get(i).setIcon(icons.get(4));
                    }
                    else
                    {
                        view.panel.labels.get(i).setIcon(new ImageIcon(rotateIcon(imageToBufferedImage(controls.icons.get(4)), -Math.PI/1)));
                    }
                }
                else if(controls.board.get(i).getPieceType() == PieceType.CHIEF)
                {
                    view.panel.labels.get(i).setIcon(icons.get(3));
                }
                else if(controls.board.get(i).getPieceType() == PieceType.EXCEL)
                {
                    view.panel.labels.get(i).setIcon(icons.get(0));
                }
                else if(controls.board.get(i).getPieceType() == PieceType.TERCEL)
                {
                    view.panel.labels.get(i).setIcon(icons.get(1));
                }
                else if(controls.board.get(i).getPieceType() == PieceType.TRIDENT)
                {
                    view.panel.labels.get(i).setIcon(icons.get(2));
                }
            }
        }
        for(JLabel label:view.panel.labels){
            view.p.add(label);
        }
        view.p.repaint();
        view.p.revalidate();

    }

    /**
     * To show the highlights of valid moves of selected piece
     * @author Boo Ee Kein Ivan
     * @param finalMoves ArrayList containing valid moves
     */
    public void showValidPanel(ArrayList<Integer> finalMoves){

        view.panel.labels.get(controls.board.indexOf(controls.currentPiece)).setBackground(Color.GRAY);

        for(int i=0; i < finalMoves.size(); i++){
            view.panel.labels.get(finalMoves.get(i)).setBackground(Color.LIGHT_GRAY);
        }

    }

    /**
     * To reset highlights back to normal panel background color
     * @author Boo Ee Kein Ivan
     */
    public void resetValidPanel(){
        for(int i=0; i<49; i++){
            view.panel.labels.get(i).setBackground(Color.WHITE);
        }
    }

    /**
     * Change the label of Player Turn after each turn
     * @author Boo Ee Kein Ivan
     */
    public void changePlayerTurn () {
        if (controls.getTurn() == PieceColor.GREEN) {
            controls.view.panel.playerTurn.setText("Green turn!");
            controls.view.panel.playerTurn.setForeground(Color.GREEN.darker());

        } else if (controls.getTurn() == PieceColor.ORANGE) {
            controls.view.panel.playerTurn.setText("Orange turn!");
            controls.view.panel.playerTurn.setForeground(Color.ORANGE.darker());
        }
        controls.view.p.revalidate();
    }

    /**
     * Events of pressing chess panels on ChessBoard
     * @author Boo Ee Kein Ivan
     * @author Seng Weng Hoong
     * @param index the index of clicked cell
     */
    public void chessPressed(Integer index) {
        if (controls.source == null) {
            controls.source = index;
            controls.currentPiece = controls.board.get(index);
            System.out.println("Works1");

            if (controls.currentPiece == null) {
                resetValidPanel();
                resetState();
                System.out.println("Works2");
            } else {
                if (controls.currentPiece.getPieceColor() != getTurn()) {
                    resetState();
                } else if (controls.currentPiece.getPieceType() == PieceType.EXCEL) {
                    resetValidPanel();
                    setFinalMoves(controls.ExcelValidMoves(index));
                    showValidPanel(finalMoves);
                    refreshPanel();

                } else if (controls.currentPiece.getPieceType() == PieceType.TRIDENT) {
                    resetValidPanel();
                    setFinalMoves(controls.TridentValidMoves(index));
                    showValidPanel(finalMoves);
                    refreshPanel();

                } else if (controls.currentPiece.getPieceType() == PieceType.TERCEL) {
                    resetValidPanel();
                    setFinalMoves(controls.TercelValidMoves(index));
                    showValidPanel(finalMoves);
                    refreshPanel();

                } else if (controls.currentPiece.getPieceType() == PieceType.CHIEF) {
                    resetValidPanel();
                    setFinalMoves(controls.ChiefValidMoves(index));
                    showValidPanel(finalMoves);
                    refreshPanel();

                } else if (controls.currentPiece.getPieceType() == PieceType.ADVANCER) {
                    resetValidPanel();
                    setFinalMoves(controls.AdvancerValidMoves(index));
                    showValidPanel(finalMoves);
                    refreshPanel();

                }

            }
        } else {
            controls.destination = index;
            System.out.println("Works3");

            if (controls.finalMoves.contains(controls.destination)) {
                resetValidPanel();
                controls.board.set(controls.source, null);
                controls.board.set(controls.destination, controls.currentPiece);
                controls.addStep(controls.currentPiece.getPieceColor());
                System.out.print("green: "+getGreenStep());
                System.out.print("orange: "+getOrangeStep());
                System.out.print("total: "+step);
                if(shouldSwapGreen())
                {
                    swapGreen();
                }
                else if(shouldSwapOrange())
                {
                    swapOrange();
                }
                this.swapGreen = this.greenStep;
                this.swapOrange = this.orangeStep;
                resetState();


                if(isWin() == PieceColor.GREEN)
                {
                    System.out.println(" Green Win");
                    Table.infoBox("Congratulations, Green Wins!");

                    controls.newGame();
                    controls.rotateBoard();

                }
                else if(isWin() == PieceColor.ORANGE)
                {
                    System.out.println("Orange Win");
                    Table.infoBox("Congratulations, Orange Wins!");
                    controls.newGame();
                    controls.rotateBoard();
                }
                if(controls.getTurn() == PieceColor.ORANGE){
                    controls.reverseIcon();
                }else{
                    controls.setIcons();
                }
                changePlayerTurn();
                rotateBoard();
                refreshPanel();
            }
            else{
                resetState();
            }

        }
    }

    /**
     * Get Icons of chess Pieces from file
     * @author Boo Ee Kein Ivan
     * @author Seng Weng Hoong
     * @return ArrayList of ImageIcon containing icons of pieces
     */
    public ArrayList<ImageIcon> getIcons(){
        ArrayList<ImageIcon> icon = new ArrayList<ImageIcon>();


        ImageIcon image1 = new ImageIcon(getClass().getResource("/View/ImageIcons/Excel-O.png"));
        ImageIcon image2 = new ImageIcon(getClass().getResource("/View/ImageIcons/Tercel-O.png"));
        ImageIcon image3 = new ImageIcon(getClass().getResource("/View/ImageIcons/Trident-O.png"));
        ImageIcon image4 = new ImageIcon(getClass().getResource("/View/ImageIcons/Chief-O.png"));
        ImageIcon image5 = new ImageIcon(getClass().getResource("/View/ImageIcons/Advancer-O.png"));
        ImageIcon image6 = new ImageIcon(getClass().getResource("/View/ImageIcons/Excel-G.png"));
        ImageIcon image7 = new ImageIcon(getClass().getResource("/View/ImageIcons/Tercel-G.png"));
        ImageIcon image8 = new ImageIcon(getClass().getResource("/View/ImageIcons/Trident-G.png"));
        ImageIcon image9 = new ImageIcon(getClass().getResource("/View/ImageIcons/Chief-G.png"));
        ImageIcon image10 = new ImageIcon(getClass().getResource("/View/ImageIcons/Advancer-G.png"));

        icon.add(image1);
        icon.add(image2);
        icon.add(image3);
        icon.add(image4);
        icon.add(image5);
        icon.add(image6);
        icon.add(image7);
        icon.add(image8);
        icon.add(image9);
        icon.add(image10);

        return icon;
    }

    /**
     * set Icons of chess Pieces from file
     * @author Boo Ee Kein Ivan
     * @author Seng Weng Hoong
     */
    public void setIcons(){
        this.icons = getIcons();
    }

    /**
     * Rotation of the board
     * @author Boo Ee Kein Ivan
     * @author Seng Weng Hoong
     */
    public void rotateBoard(){
        Collections.reverse(controls.board);
    }


    /**
     * Condition to check if the player have won
     * @author Boo Ee Kein Ivan
     * @author Seng Weng Hoong
     * @return PieceColor.ORANGE if orange wins, PieceColor.GREEN if green wins
     */
    public PieceColor isWin()
    {
        int green = 0;
        int orange = 0;
        for(int i=0; i<controls.board.size(); i++)
        {
            if(controls.board.get(i) != null){
                if(controls.board.get(i).getPieceColor() == PieceColor.GREEN)
                {
                    green++;
                    if(controls.board.get(i).getPieceType() != PieceType.CHIEF)
                    {
                        green--;
                    }
                    else if(controls.board.get(i).getPieceType() == PieceType.CHIEF)
                    {
                        green++;
                    }
                }
                else if(controls.board.get(i).getPieceColor() == PieceColor.ORANGE)
                {
                    orange++;
                    if(controls.board.get(i).getPieceType() != PieceType.CHIEF)
                    {
                        orange--;
                    }
                    else if(controls.board.get(i).getPieceType() == PieceType.CHIEF)
                    {
                        orange++;
                    }
                }
            }
        }

        if(green == 0)
        {
            return PieceColor.ORANGE;
        }
        else if(orange == 0)
        {
            return PieceColor.GREEN;
        }
        else
            return null;
    }

    /**
     * To initialize a new game
     *
     * @author	   Low Seh Hong
     *
     */
    public void newGame() {
        controls.initialize();
        controls.refreshPanel();
        controls.resetState();
        controls.setStep(0);
        controls.greenStep = 0;
        controls.orangeStep = 0;
        controls.swapGreen = null;
        controls.swapOrange = null;
        controls.changePlayerTurn();
        controls.resetValidPanel();


    }

    /**
     * To save a game file
     *
     * @author	   Low Seh Hong
     * @param      fileName the path and the name of the file
     * @param      gameName the name of the file
     */
    public void saveGame(File fileName, String gameName) {

        try {
            FileWriter fw = new FileWriter(fileName);
            fw.write(step + "\n");
            fw.write(greenStep + "\n");
            fw.write(orangeStep + "\n");
            fw.write(swapGreen + "\n");
            fw.write(swapOrange + "\n");

            for (Integer i = 0; i < controls.board.size(); i++) {
                if (controls.board.get(i) != null) {
                    fw.write(i +"\n");
                    fw.write(controls.board.get(i).getIndex() + "\n");
                    fw.write(controls.board.get(i).getPieceColor() + "\n");
                    fw.write(controls.board.get(i).getPieceType() + "\n");
                    fw.write(controls.board.get(i).getMoveDirection() + "\n");
                } else
                    continue;
            }
            fw.flush();
            fw.close();
            Table.saveBox("Game is successfully saved as " + gameName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * To load a game
     *
     * @author	   Low Seh Hong
     * @param      fileName the path and the name of the file
     */
    public void loadGame(File fileName) {
        Integer temp1;
        Integer temp2;
        String temp3;
        String temp4;
        String temp5;

        try {
            Scanner sf = new Scanner(fileName);
            controls.setStep(sf.nextInt());
            greenStep = sf.nextInt();
            orangeStep = sf.nextInt();
            controls.setSwapGreen(sf.nextInt());
            controls.setSwapOrange(sf.nextInt());
            controls.board = new ArrayList<Piece>(Collections.nCopies(49, null));
            while (sf.hasNext()) {

                temp1 = sf.nextInt();
                temp2 = sf.nextInt();
                temp3 = sf.next();
                temp4 = sf.next();
                temp5 = sf.next();


                if (PieceType.valueOf(temp4) == PieceType.CHIEF) {
                    controls.board.set(temp1, new Chief(temp2, PieceColor.valueOf(temp3)));

                } else if (PieceType.valueOf(temp4) == PieceType.TRIDENT) {
                    controls.board.set(temp1, new Trident(temp2, PieceColor.valueOf(temp3)));
                    controls.board.get(temp1).setMoveDirection(MoveDirection.valueOf(temp5));

                } else if (PieceType.valueOf(temp4) == PieceType.EXCEL) {
                    controls.board.set(temp1, new Excel(temp2, PieceColor.valueOf(temp3)));

                } else if (PieceType.valueOf(temp4) == PieceType.TERCEL) {
                    controls.board.set(temp1, new Tercel(temp2, PieceColor.valueOf(temp3)));

                } else if (PieceType.valueOf(temp4) == PieceType.ADVANCER){
                    controls.board.set(temp1, new Advancer(temp2, PieceColor.valueOf(temp3)));
                    controls.board.get(temp1).setMoveDirection(MoveDirection.valueOf(temp5));
                }
            }
            sf.close();
            fileName.delete();

            if(controls.getTurn() == PieceColor.ORANGE){
                controls.reverseIcon();
            }else{
                controls.setIcons();
            }
            controls.refreshPanel();
            controls.resetState();
            controls.changePlayerTurn();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * To reverse all the icons
     *
     * @author	   Seng Weng Hoong
     *
     */
    public void reverseIcon()
    {
        BufferedImage temp[] = new BufferedImage[10];
        for(int i=0; i<getIcons().size(); i++)
        {
            temp[i] = imageToBufferedImage(getIcons().get(i));
            controls.icons.set(i, new ImageIcon(rotateIcon(temp[i], -Math.PI/1)));
        }
        System.out.println("2222");
    }

    /**
     * Function to define the rotation of an BufferedImage
     *
     * @author	   Kuan Wei Ben
     * @param      image BufferedImage to be rotated
     * @param      angle angle to rotate
     * @return     rotated BufferedImage
     */
    public BufferedImage rotateIcon(BufferedImage image, double angle) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        double sin = Math.abs(Math.sin(angle));
        double cos = Math.abs(Math.cos(angle));
        int width = image.getWidth();
        int height = image.getHeight();
        int nwidth = (int)Math.floor((double)width * cos + (double)height * sin);
        int nheight = (int)Math.floor((double)height * cos + (double)width * sin);
        int transparency = image.getColorModel().getTransparency();
        BufferedImage newicon = gc.createCompatibleImage(nwidth, nheight, transparency);
        Graphics2D g = newicon.createGraphics();
        g.translate((nwidth - width) / 2, (nheight - height) / 2);
        g.rotate(angle, (double)(width / 2), (double)(height / 2));
        g.drawRenderedImage(image, (AffineTransform)null);
        return newicon;
    }

    /**
     * Function to convert ImageIcon to BufferedImage
     *
     * @author	   Seng Weng Hoong
     * @param      icon ImageIcon to be converted
     * @return     converted BufferedImage
     */
    public BufferedImage imageToBufferedImage(ImageIcon icon) {
        BufferedImage bi = new BufferedImage
                (icon.getIconWidth(),icon.getIconHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        icon.paintIcon(null, g, 0,0);
        g.dispose();

        return bi;
    }

}