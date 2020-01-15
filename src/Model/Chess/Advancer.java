package Model.Chess;

import Model.MoveDirection;
import Model.Piece;
import Model.PieceColor;

/**
 * This class is a children class of Piece and have the identity of Advancer
 *
 * @author Seng Weng Hoong
 * @author Boo Ee Kein Ivan
 */

public class Advancer extends Piece {
    private PieceType pieceType;
    private MoveDirection moveDirection;



    public Advancer(Integer index, PieceColor pieceColor)
    {
        super(index, pieceColor);
        pieceType = PieceType.ADVANCER;
        moveDirection = MoveDirection.FORWARD;
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
    public Integer getIndex()
    {
        return this.index;
    }
    /**
     * This function returns PieceColor
     *
     * @author Seng Weng Hoong
     * @author Boo Ee Kein Ivan
     * @return PieceColor
     */
    @Override
    public PieceColor getPieceColor()
    {
        return this.pieceColor;
    }

    /**
     * This class get MoveDirection
     *
     * @author Seng Weng Hoong
     * @author Boo Ee Kein Ivan
     * @return pieceColor
     */
    public MoveDirection getMoveDirection()
    {
        return this.moveDirection;
    }
    /**
     * This class set MoveDirection
     *
     * @author Seng Weng Hoong
     * @author Boo Ee Kein Ivan
     */
    public void setMoveDirection (MoveDirection moveDirection){
        this.moveDirection = moveDirection;

    }
}