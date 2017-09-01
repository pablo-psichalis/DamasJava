import Board.*;

public class Damas {

    public static void main(String[] args) {

        Board bo = new Board();
        printBoard(bo);

    }

    private static final int[] ALL_SQUARE_IDS =
            {
                11, 5, 31, 25,
                10, 4, 30, 24,
                3, 29, 23, 17,
                2, 28, 22, 16,
                27, 21, 15, 9,
                26, 20, 14, 8,
                19, 13, 7, 1,
                18, 12, 6, 0
            };


    private static void printBoard(Board board) {

        int i=0;
        for(int row=0; row<Board.NUM_ROWS; row++){
            if(row % 2==0){
                for(int col=0; col<Board.NUM_COLUMNS; col+=2, i++){
                    System.out.print("[ ]");
                    printRetrievedToken(ALL_SQUARE_IDS[i], board);
                }
            }else{
                for(int col=0; col<Board.NUM_COLUMNS; col+=2, i++){
                    printRetrievedToken(ALL_SQUARE_IDS[i], board);
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
    }

    private static void printRetrievedToken(int id, Board board){
        if (board.getOccupiedByRed().containsKey(id)) {
            System.out.print("[R]");
        } else if (board.getOccupiedByBlack().containsKey(id)) {
            System.out.print("[B]");
        } else {
            System.out.print("[ ]");
        }
    }

}
