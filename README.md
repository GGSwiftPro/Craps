Homework:

Pick one of the following two to implement a server which can be used to play the respective game and perform minor statistical analysis on the outcomes of long term play.

Spider: 
	Picture a cube with a hungry spider on one corner and terrified ant on the opposite corner.
 Suppose spider is at NE corner of the ceiling and the ant is on the SW corner on the floor.
 The ant never moves. The spider follows a random walk: each time it reaches a corner,
 it picks an edge uniformly at random (independently of its past choices) and follows it to the next corner.
 On average how many edge lengths will the spider walk before reaching the ant
A server, which is an implementation of the picked game (Spider or Craps).

Craps:
	The game of craps, played with 2 dice is a popular gambling game. The rules are these: Only totals of two dice count.
The player throws the dice and wins at once if the total for the first throw is 7 or 11, loses at once if the total is 2, 3 or 12. Any other throw is called the player's point.
If the first throw is a point the player throws the dice repeatedly until either wins by throwing his point again or loses by throwing a 7.
What is the player's chance to win?


For Spider:
	Each time the player starts a game they pay $11 (stake).
	Each of the cube's edge that was passed by the spider gives a $1 reward.
	The game ends when the spider gets to the ant.
	At this point the win is rewarded to the player. (we only pay out the reward calculated by the spider walk, we do not return the stake)
	
For Craps:
	Each time the player starts the game - they pay $1 (stake).
	When the game ends:
		if they won - they get the $1 stake they did, plus $1 win.
		if they lost - they get $0. (they lose their stake)

The server needs to be able to accept HTTP requests in JSON format, which have to include:
	Play a round:
		the request details should contain 
			the stake
			the game type
		the response should contain 
			the stake
			the game type
			the outcome of the game
				total win
				additional details needed to trace the game play (in here make sure to include all details that would be needed by a front end to visualize the flow of the game)
				
	Play n rounds
		the request details should contain 
			the stake
			the game type
			the number of rounds to be played
		the response should contain 
			the sum of all stakes
			the sum of all wins
			the sum of all wins/the sum of all stakes
