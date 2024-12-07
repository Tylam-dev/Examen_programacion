package org.example;
import View.View;

public class Main {

    public static void main(String[] args) {
        View view = new View();
        view.Incialize();
        while (true) {
            var response = view.input();
            if(response){
                break;
            }
        }
    }
}