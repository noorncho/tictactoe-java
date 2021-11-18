import java.util.Arrays;
import java.util.Random;

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
    private int xScore, oScore;
    private String currentPlayer, winner;
    private boolean gameOver, singlePlayer, computerTurn;
    GUI gui;

    public Game(GUI gui){
        this.gui = gui;
        Arrays.fill(boardGrid, "-");
        turnCounter = 0;
        moveCounter = 1;
        xScore = oScore = 0;
        currentPlayer = "X";
        winner = "";
        gameOver = false;
        singlePlayer = false;
        computerTurn = false;
    }

    public String changePlayer() {
        if(turnCounter % 2 == 0) return "X";
        else return "O";
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public String getWinner() {
        return winner;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public int getxScore() {
        return xScore;
    }

    public int getoScore() {
        return oScore;
    }

    public boolean isSinglePlayer() {
        return singlePlayer;
    }

    public void setSinglePlayer(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
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

    public void singlePlayerMode(){
        computerTurn = true;
        while(computerTurn){
            int computerMove = new Random().nextInt(8);
            if(boardGrid[computerMove].equals("-")){
                boardGrid[computerMove] = currentPlayer;
                computerTurn = false;

                gui.buttons[computerMove].setText(currentPlayer);
                gui.buttons[computerMove].setEnabled(false);
            }
        }
    }

    public void playGame(int btnIndex){
        boardGrid[btnIndex] = currentPlayer;
        checkForWin();

        if(singlePlayer && moveCounter < 9 && !gameOver){
            singlePlayerMode();
            checkForWin();
        }
    }

    public void increaseScore(){
        if(winner.equals("X")) xScore++;
        if(winner.equals("O")) oScore++;
    }

    public void resetVariables(){
        Arrays.fill(boardGrid, "-");
        turnCounter = 0;
        moveCounter = 1;
        currentPlayer = "X";
        winner = "";
        gameOver = false;
        singlePlayer = false;
        computerTurn = false;
    }
}
