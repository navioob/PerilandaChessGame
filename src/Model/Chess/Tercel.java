package Model.Chess;

import Model.MoveDirection;
import Model.Piece;
import Model.PieceColor;

import java.util.ArrayList;

/**
 * This class is a children class of Piece and have the identity of Tercel
 *
 * @author Seng Weng Hoong
 * @author Boo Ee Kein Ivan
 */

public class Tercel extends Piece {
    private PieceType pieceType;

    public Tercel(int index, PieceColor pieceColor){
        super(index, pieceColor);
        pieceType = PieceType.TERCEL;
    }

    /**
     *This function returns PieceType
     *
     * @author Seng Weng Hoong
     * @author Boo Ee Kein Ivan
     * @return PieceType
     */
    @Override
    public PieceType getPieceType() {
        return this.pieceType;
    }
    /**
     * This function returns Index
     *
     * @author Seng Weng Hoong
     * @author Boo Ee Kein Ivan
     * @return Index
     */
    @Override
    public Integer getIndex(){
        return index;
    }
    /**
     * This function returns PieceColor
     *
     * @author Seng Weng Hoong
     * @author Boo Ee Kein Ivan
     * @return PieceColor
     */
    @Override
    public PieceColor getPieceColor(){
        return pieceColor;
    }
    /**
     * This class set MoveDirection
     *
     * @author Seng Weng Hoong
     * @author Boo Ee Kein Ivan
     */

    @Override
    public void setMoveDirection(MoveDirection forward) {

    }
    /**
     * This class get MoveDirection
     *
     * @author Seng Weng Hoong
     * @author Boo Ee Kein Ivan
     * @return pieceColor
     */
    @Override
    public MoveDirection getMoveDirection() {
        return null;
    }
}
