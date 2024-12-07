package Interfaces;

import Models.Square;

public interface IFinder {
    Square findSquare(int positionX, int positionY);
    char positionYConvert(int position);
}
