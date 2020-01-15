package View;

import Controller.ChessController;
import Model.Chess.PieceType;
import Model.PieceColor;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class serves the purpose of initialising components for GUI
 *
 * @author Boo Ee Kein Ivan
 */


public class Panelling {

    public static ArrayList<JLabel> labels;
    public static ChessController cc;
    public static JLabel playerTurn;

    //When the constructor of Panelling is called, the initialization of JLabels for ChessBoard and playerTurn
    public Panelling(ChessController cc){
        this.cc = cc;

        labels = new ArrayList<JLabel>();
        playerTurn = new JLabel("Green turn!",JLabel.CENTER);
        playerTurn.setFont(new Font("Arial", Font.PLAIN, 20));
        playerTurn.setForeground(Color.GREEN.darker());

        for (int i = 0; i < 49; i++) {
            if (cc.board.get(i) == null) {
                JLabel label = new JLabel();
                label.setHorizontalAlignment(0);
                label.setOpaque(true);
                label.setBackground(Color.WHITE);
                label.addMouseListener(new Table.MyMouseListener(i));
                labels.add(label);
            } else if (cc.board.get(i).getPieceType() == PieceType.EXCEL) {
                if (cc.board.get(i).getPieceColor() == PieceColor.ORANGE) {
                    JLabel label = new JLabel(cc.icons.get(0),JLabel.CENTER);
                    label.setOpaque(true);
                    label.setBackground(Color.WHITE);
                    label.addMouseListener(new Table.MyMouseListener(i));
                    labels.add(label);
                } else if (cc.board.get(i).getPieceColor() == PieceColor.GREEN) {
                    JLabel label = new JLabel(cc.icons.get(5),JLabel.CENTER);
                    label.setOpaque(true);
                    label.setBackground(Color.WHITE);
                    label.addMouseListener(new Table.MyMouseListener(i));
                    labels.add(label);
                }
            } else if (cc.board.get(i).getPieceType() == PieceType.TERCEL) {
                if (cc.board.get(i).getPieceColor() == PieceColor.ORANGE) {
                    JLabel label = new JLabel(cc.icons.get(1),JLabel.CENTER);
                    label.setOpaque(true);
                    label.setBackground(Color.WHITE);
                    label.addMouseListener(new Table.MyMouseListener(i));
                    labels.add(label);
                } else if (cc.board.get(i).getPieceColor() == PieceColor.GREEN) {
                    JLabel label = new JLabel(cc.icons.get(6),JLabel.CENTER);
                    label.setOpaque(true);
                    label.setBackground(Color.WHITE);
                    label.addMouseListener(new Table.MyMouseListener(i));
                    labels.add(label);
                }
            } else if (cc.board.get(i).getPieceType() == PieceType.TRIDENT) {
                if (cc.board.get(i).getPieceColor() == PieceColor.ORANGE) {
                    JLabel label = new JLabel(cc.icons.get(2),JLabel.CENTER);
                    label.setOpaque(true);
                    label.setBackground(Color.WHITE);
                    label.addMouseListener(new Table.MyMouseListener(i));
                    labels.add(label);
                } else if (cc.board.get(i).getPieceColor() == PieceColor.GREEN) {
                    JLabel label = new JLabel(cc.icons.get(7),JLabel.CENTER);
                    label.setOpaque(true);
                    label.setBackground(Color.WHITE);
                    label.addMouseListener(new Table.MyMouseListener(i));
                    labels.add(label);
                }
            } else if (cc.board.get(i).getPieceType() == PieceType.CHIEF) {
                if (cc.board.get(i).getPieceColor() == PieceColor.ORANGE) {
                    JLabel label = new JLabel(cc.icons.get(3),JLabel.CENTER);
                    label.setOpaque(true);
                    label.setBackground(Color.WHITE);
                    label.addMouseListener(new Table.MyMouseListener(i));
                    labels.add(label);
                } else if (cc.board.get(i).getPieceColor() == PieceColor.GREEN) {
                    JLabel label = new JLabel(cc.icons.get(8),JLabel.CENTER);
                    label.setOpaque(true);
                    label.setBackground(Color.WHITE);
                    label.addMouseListener(new Table.MyMouseListener(i));
                    labels.add(label);
                }
            } else if (cc.board.get(i).getPieceType() == PieceType.ADVANCER) {
                if (cc.board.get(i).getPieceColor() == PieceColor.ORANGE) {
                    JLabel label = new JLabel(cc.icons.get(4),JLabel.CENTER);
                    label.setOpaque(true);
                    label.setBackground(Color.WHITE);
                    label.addMouseListener(new Table.MyMouseListener(i));
                    labels.add(label);
                } else if (cc.board.get(i).getPieceColor() == PieceColor.GREEN) {
                    JLabel label = new JLabel(cc.icons.get(9),JLabel.CENTER);
                    label.setOpaque(true);
                    label.setBackground(Color.WHITE);
                    label.addMouseListener(new Table.MyMouseListener(i));
                    labels.add(label);
                }

            }
        }
    }
}
