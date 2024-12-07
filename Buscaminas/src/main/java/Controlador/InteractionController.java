package Controlador;

import Models.BoardModel;
import Models.ResultModel;

public class InteractionController {
    private BoardModel _board = new BoardModel();
    public void start(){
        this._board.initializeBoard();
    }
    public boolean sendAction(char action, int row, int col){
        return this._board.checkInput(action, row, col);
    }
    public ResultModel getStatus(){
        return this._board.status();
    }
}
