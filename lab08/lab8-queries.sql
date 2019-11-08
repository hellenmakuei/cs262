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
