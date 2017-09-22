package Board;

import Token.Color;

public class Turn {

    private Color color;

    public Turn() {this.color = Color.RED;} // Red starts by default

    public Turn(Color color) {this.color = color;}

    public void switchTurn() {
        if (this.color == Color.BLACK) {
            this.color = Color.RED;
        } else {
            this.color = Color.BLACK;
        }
    }

    @Override
    public String toString() {
        if (this.color == Color.RED){
            return "Red";
        }else{
            return "Black";
        }
    }

    public Color getColor() { return color; }
}
