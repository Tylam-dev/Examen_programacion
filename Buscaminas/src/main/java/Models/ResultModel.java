package Models;

import Interfaces.IFinder;

import java.util.ArrayList;

public class ResultModel implements IFinder {
    private int _size;
    private boolean _finish;
    private ArrayList<Square> _squares;
    public ResultModel(int size, boolean finish, ArrayList<Square> squares){
        this._size = size;
        this._finish = finish;
        this._squares = squares;
    }
    public boolean getFinish(){
        return this._finish;
    }
    public int getSize(){
        return this._size;
    }

    @Override
    public Square findSquare(int positionX, int positionY){
        var charPositionY = this.positionYConvert(positionY);
        var foundSquare = this._squares.stream().filter(s -> positionX == s.getPositionX() && charPositionY == s.getPositionY()).findFirst();
        return foundSquare.get();
    }
    @Override
    public char positionYConvert(int position){
        var positions = new char[]{'A','B','C','D','F','G','H','J','K','M','N'};
        return positions[position];
    }
}
