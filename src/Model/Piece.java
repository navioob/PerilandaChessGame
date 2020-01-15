package Model;
import Model.Chess.PieceType;

import java.util.*;

/**
 * This class is a parent class for Piece
 *
 * @author Seng Weng Hoong
 * @author Boo Ee Kein Ivan
 */

public abstract class Piece {

    protected int index;
    protected PieceColor pieceColor;



    public Piece( int index, PieceColor pieceColor)
    {
        this.index = index;
        this.pieceColor = pieceColor;
    }

    /**
     *This function returns PieceType
     *
     * @author Seng Weng Hoong
     * @author Boo Ee Kein Ivan
     * @return PieceType
     */
    public abstract PieceType getPieceType();
    /**
     * This function returns Index
     *
     * @author Seng Weng Hoong
     * @author Boo Ee Kein Ivan
     * @return Index
     */
    public abstract Integer getIndex();
    /**
     * This function returns PieceColor
     *
     * @author Seng Weng Hoong
     * @author Boo Ee Kein Ivan
     * @return PieceColor
     */
    public abstract PieceColor getPieceColor();

    /**
     * This class set MoveDirection
     *
     * @author Seng Weng Hoong
     * @author Boo Ee Kein Ivan
     */
    public abstract void setMoveDirection(MoveDirection forward);
    /**
     * This function returns MoveDirection
     *
     * @author Seng Weng Hoong
     * @author Boo Ee Kein Ivan
     * @return MoveDirection
     */
    public abstract MoveDirection getMoveDirection();
}