import java.util.Arrays;

public class Game {
    private final int[][] winCombos = {{0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}};
    private final String[] boardGrid = new String[9];
    private int turnCounter, moveCounter;
    private String currentPlayer, winner;
    private boolean gameOver;

    public Game(){
        Arrays.fill(boardGrid, "-");
        turnCounter = 0;
        moveCounter = 1;
        currentPlayer = "X";
        winner = "";
        gameOver = false;
    }

    public String changePlayer() {
        if(turnCounter % 2 == 0) return "X";
        else return "O";
    }

    public String getWinner() {
        return winner;
    }

    public String[] getBoardGrid() {
        return boardGrid;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void checkForWin(){
        for (int[] winCombo : winCombos) {
            String a = boardGrid[winCombo[0]];
            String b = boardGrid[winCombo[1]];
            String c = boardGrid[winCombo[2]];

            if (a.equals("-") || b.equals("-") || c.equals("-")) {
                continue;
            }
            if (a.equals(b) && b.equals(c)) {
                gameOver = true;
                break;
            }
        }
        checkGameOver();
    }

    public void checkGameOver(){
        if(gameOver){
            winner = currentPlayer;
            System.out.println(currentPlayer + " is the winner");
        }else if(moveCounter >= 9){
            winner = "Game Draw";
            System.out.println("Game Draw");
            gameOver = true;
        }else{
            moveCounter++;
            turnCounter++;
            currentPlayer = changePlayer();
            gameOver = false;
        }
    }
}
