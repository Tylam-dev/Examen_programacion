package Models;

public class ResultModel {
    private int _size;
    private int _existingMines;
    private boolean _finish;
    private char[][] _board;
    private boolean[][] _mines;
    private boolean[][] _revealed;
    private boolean[][] _flags;
    public ResultModel(int size, int existingMines, boolean finish, char[][] board, boolean[][] mines, boolean[][] revealed, boolean[][] flags){
        this._size = size;
        this._existingMines = existingMines;
        this._finish = finish;
        this._board = board;
        this._mines = mines;
        this._revealed = revealed;
        this._flags = flags;
    }
    public char[][] getBoard(){
        return this._board;
    }
    public boolean[][] getMines(){
        return this._mines;
    }
    public boolean[][] getRevealed(){
        return this._revealed;
    }
    public boolean[][] getFlags(){
        return this._flags;
    }
    public boolean getFinish(){
        return this._finish;
    }
    public int getSize(){
        return this._size;
    }
    public int getExistingMines(){
        return this._existingMines;
    }
}
