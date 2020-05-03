import java.sql.*;

public class BoardGamesDB
{

    public BoardGamesDB(){}

    static final String url = "jdbc:mysql://localhost/boardgamesDB";
    static final String user = "root";
    static final String password = "";

    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    static UIInterface UIpanel = new UIInterface();

    public void showAll()
    {
        String query = "SELECT name , player_min, player_max , game_time , game_type , game_subtype, complexity " +
                "FROM boardgamesDB.games";
        String gameName;
        String playerMin;
        String playerMax;
        String gameTime;
        String gameType;
        String gameSubType;
        String complexity;

        try
        {
            conn = DriverManager.getConnection(url, user, password);
            statmt = conn.createStatement();
            resSet = statmt.executeQuery(query);

            while (resSet.next())
            {
                gameName = resSet.getString(1);
                playerMin = resSet.getString(2);
                playerMax = resSet.getString(3);
                gameTime = resSet.getString(4);
                gameType = resSet.getString(5);
                gameSubType = resSet.getString(6);
                complexity = resSet.getString(7);

                UIpanel.printToOutput(gameName + " : " + playerMin + " : " + playerMax + " : " + gameTime +
                        " : " + gameType + " : " + gameSubType + " : " + complexity + "\n");
            }

        }
        catch (SQLException sqlEx)
        {
            UIpanel.printToOutput("SQL exception\n");
            sqlEx.printStackTrace();
        }  finally
        {
            //close connection and stmt here
            try { conn.close(); } catch(SQLException se) { /*can't do anything */ }
            try { statmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

    public  void addEntry(String name, int playersMin, int playersMax, int gameTime, int gameType, int gameSubType, int complexity)
    {
        String query = "INSERT INTO boardgamesDB.games (name, player_min , player_max , game_time , game_type , game_subtype, complexity) "
                + "VALUES ('" + name + "', '" + playersMin + "', '" + playersMax + "' , '" + gameTime + "','" + gameType
                + "' , '" + gameSubType + "', '" + complexity + "')";
        try
        {
            // opening database connection to MySQL server
            conn = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            statmt = conn.createStatement();
            statmt.executeUpdate(query);
            UIpanel.printToOutput("Game " + name + " was successfully added\n");
        }
        catch (SQLException sqlEx)
        {
            UIpanel.printToOutput("SQL exception\n");
            sqlEx.printStackTrace();
        }  finally
        {
            //close connection and stmt here
            try { conn.close(); } catch(SQLException se) { /*can't do anything */ }
            try { statmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

    public  void deleteEntry(String name)
    {
        String query = "DELETE FROM boardgamesDB.games WHERE name = '" + name + "'";
        try
        {
            // opening database connection to MySQL server
            conn = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            statmt = conn.createStatement();
            statmt.executeUpdate(query);
            UIpanel.printToOutput("Game " + name + " was successfully deleted\n");
        }
        catch (SQLException sqlEx)
        {
            UIpanel.printToOutput("SQL exception\n");
            sqlEx.printStackTrace();
        }  finally
        {
            //close connection and stmt here
            try { conn.close(); } catch(SQLException se) { /*can't do anything */ }
            try { statmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

    public void playNow(String playerMin, String gameTime)
    {
        String query = "SELECT name, game_time, game_type, game_subtype, complexity FROM boardgamesDB.games WHERE " +
                "player_min >= " + playerMin + " AND player_max >= " +  playerMin + " AND game_time <= " + gameTime;
        String gameName, gameType, gameSubType, complexity;
        try
        {
            conn = DriverManager.getConnection(url, user, password);
            statmt = conn.createStatement();
            resSet = statmt.executeQuery(query);
            UIpanel.printToOutput("name : game_time : game_type : game_subtype : complexity\n");

            while (resSet.next())
            {
                gameName = resSet.getString(1);
                gameTime = resSet.getString(2);
                gameType = resSet.getString(3);
                gameSubType = resSet.getString(4);
                complexity = resSet.getString(5);

                UIpanel.printToOutput(gameName + " : " + gameTime + " : " + gameType + " : " + gameSubType +
                        " : " + complexity + "\n");
            }
        }
        catch (SQLException sqlEx)
        {
            UIpanel.printToOutput("SQL exception\n");
            sqlEx.printStackTrace();
        }  finally
        {
            //close connection and stmt here
            try { conn.close(); } catch(SQLException se) { /*can't do anything */ }
            try { statmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

}
