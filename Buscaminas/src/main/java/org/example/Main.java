package org.example;
import Exceptions.InvalidMoveException;
import View.View;

public class Main {

    public static void main(String[] args) {
        View view = new View();
        view.Incialize();
        while (true) {
            try{
                var response = view.input();
                if(response){
                    break;
                }
            }catch (InvalidMoveException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}