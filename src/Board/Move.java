package Board;

public class Move {

    private int origin;
    private int destination;

    public Move(int origin, int destination) {
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "[" + " or:" + origin + " , dst:" + destination + " ]";
    }

    public int getOrigin() {
        return origin;
    }

    public int getDestination() {
        return destination;
    }
}
