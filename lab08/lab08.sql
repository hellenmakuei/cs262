--
-- This SQL script builds a monopoly database, deleting any pre-existing version.
--
-- Hellen Ayor Makuei
-- @version Summer, 2015
--

-- Drop previous versions of the tables if they they exist, in reverse order of foreign keys.
DROP TABLE IF EXISTS Properties;
DROP TABLE IF EXISTS PlayerDetail;
DROP TABLE IF EXISTS PlayerGame;
DROP TABLE IF EXISTS Game;
DROP TABLE IF EXISTS Player;




-- Create the schema.
CREATE TABLE Game (
	gID integer PRIMARY KEY, 
	time timestamp

	);

CREATE TABLE Player (
	pID integer PRIMARY KEY, 
	emailAddress varchar(50) NOT NULL,
	name varchar(50)
	);


CREATE TABLE PlayerGame (
    PRIMARY KEY (gID, pID),
	gameID integer REFERENCES Game(ID) NOT NULL, 
	playerID integer REFERENCES Player(ID) NOT NULL,
    Income integer
	score integer
    
	);


    CREATE TABLE Properties (

        ID integer PRIMARY KEY,
        name varchar(50) NOT NULL,
        category varchar(50) NOT NULL
);
    
 CREATE TABLE PlayerDetail (
        PRIMARY KEY (gID, pID),
        gID integer REFERENCES Game(gID) NOT NULL,
        pID integer REFERENCES Player(pID) NOT NULL,
        board_location integer NOT NULL,--should be one of 0 thru 39
        cash money,
        score integer
        );
    

-- Allow users to select data from the tables.
GRANT SELECT ON Game TO PUBLIC;
GRANT SELECT ON Player TO PUBLIC;
GRANT SELECT ON PlayerGame TO PUBLIC;
GRANT SELECT ON Proterties To PUBLIC;
GRANT SELECT ON PlayerDetail To PUBLIC;

-- Add sample records.
INSERT INTO Game VALUES (1, '2006-06-27 08:00:00');
INSERT INTO Game VALUES (2, '2007-06-28 13:20:00');
INSERT INTO Game VALUES (3, '2007-06-29 18:41:00');



INSERT INTO Player(pID, emailAddress) VALUES (1, 'me@calvin.edu');
INSERT INTO Player VALUES (2, 'king@gmail.edu', 'The King');
INSERT INTO Player VALUES (3, 'dog@gmail.edu', 'Dogbreath');

INSERT INTO PlayerGame VALUES (1, 1, 0.00,10);
INSERT INTO PlayerGame VALUES (1, 2, 500.00,14);
INSERT INTO PlayerGame VALUES (1, 3, 2350.00,20);
INSERT INTO PlayerGame VALUES (2, 1, 1000.00,15);
INSERT INTO PlayerGame VALUES (2, 3, 2250.00,16);
INSERT INTO PlayerGame VALUES (2, 3, 1500.00,13);
INSERT INTO PlayerGame VALUES (3, 2, 6000.00,25);
INSERT INTO PlayerGame VALUES (3, 3, 5500.00,22);


INSERT INTO Properties  VALUES (1, 2, 5,100, 400 );
INSERT INTO Properties VALUES (2, 1 ,10,18,500);
INSERT INTO Properties VALUES (3, 3,16,30,100);
INSERT INTO Properties VALUES (1, 2, 8,99, 300 );
INSERT INTO Properties VALUES (3, 1 ,15,17,500);
INSERT INTO Properties  VALUES (3, 2,9,20,100);


INSERT INTO PlayerDetail  VALUES (1, 2, 2);
INSERT INTO PlayerDetail VALUES (2, 1 ,3);
INSERT INTO PlayerDetail  VALUES (3, 3,4);
INSERT INTO PlayerDetail VALUES (1, 2, 9);
INSERT INTO PlayerDetail VALUES (3, 1 ,1);
INSERT INTO PlayerDetail VALUES (3, 2,10);



---Exercise  8.1
--Retrieve a list of all the games, ordered by date with the most recent game coming first.

SELECT * FROM Game ORDER BY time DESC;



--Retrieve all the games that occurred in the past week.
SELECT * FROM GAME WHERE time > Now() - INTERVAL '7 days';


--Retrieve a list of players who have (non-NULL) names.

 SELECT * FROM Player WHERE name IS NOT NULL



--Retrieve a list of IDs for players who have some game score larger than 2000.

SELECT Player.pID FROM Player, PlayerGame WHERE Player.pID = PlayerGame.pID AND PlayerGame.score > 2000.00;




--Retrieve a list of players who have GMail accounts.
SELECT * FROM Player WHERE emailAddress LIKE '%GMail%' OR emailAddress LIKE '%gmail%'





--EXcercise 8.2


--Retrieve all “The King”’s game scores in decreasing order.
    SELECT PlayerGame.score FROM PlayerGame, Player
     WHERE PlayerGame.pID = Player.pID AND Player.name = 'The King'
     ORDER BY PlayerGame.score DESC;
  

-- Retrieve the name of the winner of the game played on 2006-06-28 13:20:00

SELECT Player.name FROM Player, Game, PlayerGame
 WHERE Player.pID = PlayerGame.pID
  AND Game.ID = PlayerGame.gID
  AND Game.time = '2006-06-28 13:20:00'
 ORDER BY PlayerGame.score DESC
  LIMIT 1;


--So what does that P1.ID < P2.ID clause do in the last example query?

SELECT P1.name-FROM Player AS P1, Player AS P2
WHERE P1.name = P2.name AND P1.pID < P2.pID;


--The query that joined the Player table to itself seems rather contrived. Can you think of a realistic situation in which you’d want to join a table to itself

SELECT P1.name
FROM Player AS P1, Player AS P2
WHERE P1.name = P2.name
  AND P1.ID < P2.ID;

  -- because the if found the same name of the p1 itself it can not comprare with it self example in faulty database student can not grade for themselves thus can  asigned grades
