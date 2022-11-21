package Systems;

public class Statistics
{   static int games=0;
    static int playerOneWins=0;
    static int playerTwoWins=0;

    public static int getGames() {
        return games;
    }

    public static void setGames(int games) {
        Statistics.games = games;
    }

    public static int getPlayerOneWins() {
        return playerOneWins;
    }

    public static void setPlayerOneWins(int playerOneWins) {
        Statistics.playerOneWins = playerOneWins;
    }

    public static int getPlayerTwoWins() {
        return playerTwoWins;
    }

    public static void setPlayerTwoWins(int playerTwoWins) {
        Statistics.playerTwoWins = playerTwoWins;
    }
}
