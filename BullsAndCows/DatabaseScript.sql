DROP DATABASE IF EXISTS bullsandcows;
CREATE DATABASE bullsandcows;
USE bullsandcows;
CREATE TABLE game (gameId INT PRIMARY KEY DEFAULT 1, answer VARCHAR(4) NOT NULL, isFinished BOOLEAN NOT NULL);
CREATE TABLE round (roundId INT PRIMARY KEY DEFAULT 1, guess VARCHAR(4) NOT NULL, exactMatch INT NOT NULL, partialMatch INT NOT NULL, sessionTime DATETIME NOT NULL);
CREATE TABLE games_rounds 
(gameId INT, 
roundId INT, 
PRIMARY KEY (gameId, roundId), 
FOREIGN KEY (gameId) REFERENCES game (gameId),
FOREIGN KEY (roundId) REFERENCES round (roundId)
);

SELECT * FROM game;
SELECT * FROM round;
SELECT * FROM games_rounds;