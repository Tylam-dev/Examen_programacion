package Models;

import java.util.ArrayList;

public class Square {
    private int _positionX;
    private char _positionY;
    private boolean _isMine;
    private boolean _isRevealed;
    private boolean _hasFlag;
    private char _minesAround;
    public Square(int positionX, char positionY){
        this._positionX = positionX;
        this._positionY = positionY;;
    }
    public int getPositionX(){
        return this._positionX;
    }
    public char getPositionY(){
        return this._positionY;
    }
    public boolean getIsMine(){
        return this._isMine;
    }
    public boolean getIsRevealed(){
        return this._isRevealed;
    }
    public boolean getHasFlag(){
        return this._hasFlag;
    }
    public void setIsMine(boolean value){
        this._isMine = value;
    }
    public void setIsRevealed(boolean value){
        this._isRevealed = value;
    }
    public void setHasFlag(boolean value){
        this._hasFlag = value;
    }
    public void setMinesAround(char value){
        this._minesAround = value;
    }
    public char getMinesAround(){
        return this._minesAround;
    }
}
