package Models;

public class InputClass {
    private char _action;
    private int _row;
    private int _col;
    public InputClass(char action, int row, int col){
        this._action = action;
        this._row = row;
        this._col = col;
    }
    public char getAction(){
        return this._action;
    }
    public int getRow(){
        return this._row;
    }
    public int getCol(){
        return this._col;
    }
}
