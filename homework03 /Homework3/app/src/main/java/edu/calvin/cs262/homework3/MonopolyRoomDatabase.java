package edu.calvin.cs262.homework3;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Manage source data with Player, Game, and PlayerGameJoin classes
 */

@Database(entities = { Player.class, Game.class, PlayerGameJoin.class }, version = 1, exportSchema = false)

public abstract class MonopolyRoomDatabase extends RoomDatabase {

    public abstract PlayerDao playerDao();
    public abstract GameDao gameDao();
    public abstract PlayerGameJoinDao playerGameJoinDao();
    private static MonopolyRoomDatabase INSTANCE;

    static MonopolyRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MonopolyRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MonopolyRoomDatabase.class, "player_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final PlayerDao playerDao;
        private final GameDao gameDao;
        private final PlayerGameJoinDao playerGameJoinDao;
        PopulateDbAsync(MonopolyRoomDatabase db) {
            playerDao = db.playerDao();
            gameDao = db.gameDao();
            playerGameJoinDao = db.playerGameJoinDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            // If we have no players, then create the initial list of players
            if (playerDao.getAnyPlayer().length < 1) {
                playerDao.insert(new Player("John",    "john@gmail.com", 50));
                playerDao.insert(new Player("Mary", "mary@gmail.com",  51));
                playerDao.insert(new Player("Hellen", "hellen@gmail.com",40));
            }

            // If we have no games, then create the initial list of games
            if (gameDao.getAnyGame().length < 1) {
                gameDao.insert(new Game("2006-06-29 18:41:00", 1));
                gameDao.insert(new Game("2019-06-29 8:45:00",  2));
                gameDao.insert(new Game("2019-06-29 13:00:01", 3));
            }

            // If we have no playerGameJoins, then create the initial list of playerGameJoins
            if (playerGameJoinDao.getAnyPlayerGameJoin().length < 1) {
                playerGameJoinDao.insert(new PlayerGameJoin(1, 50));
                playerGameJoinDao.insert(new PlayerGameJoin(1, 51));
                playerGameJoinDao.insert(new PlayerGameJoin(2, 40));
                playerGameJoinDao.insert(new PlayerGameJoin(3, 35));
            }

            return null;
        }
    }
}