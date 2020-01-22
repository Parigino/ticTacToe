# ticTacToe

Technical exercise

Objectives

Create the game Tic-Tac-Toe:  https://en.wikipedia.org/wiki/Tic-tac-toe and make it available at a given domain.
Allow users to continue the game on a different browser or device by sharing the URL with that browser/device.

User Stories
Story 1

As a user I want to configure my game before starting to play, so that I can customize the game to what I like the most

Acceptance Criterias
On a configuration screen the user can:
select the grid size between those options
3 by 3
5 by 5
10 by 10
select the number of players
Min 2 players
Max 4 players
Once the user has finished to configure his game, he can continue to the playing screen
Once created the game cannot be edited/deleted
The user can create as many new games as he wants by accessing the configuration screen
Story 2
As a user I want to play the game with friends so that I can have fun
Acceptance Criterias
Each player will be represented by the symbol he uses to play (ex. Circle, Cross, etc.)
A playing grid is displayed on the playing screen accordingly to the game configuration
The current player is shown next to the grid (show the symbol that represents the current player)
Once the user selects an empty cell of the grid:
Add the symbol of the current player in the selected cell
If the user has won:
Show the winner next to the Grid at the place of the current player
Show a message explaining that the user has won
Show a button to reset the game
If he has not won:
Change the current player to the next player
When the user clicks the reset button, reset the game by:
Empting the grid
Showing the new current player at the place of the winner
All players play on the same screen (no need to synchronize the same game on different screens in real time)
Story 3
As a user I want to share the URL with a friend, so that I can continue playing on another browser
Acceptance Criterias

Every created game has a unique URL
Opening the unique URL in another browser will allow the players to continue playing from the same point where they left
The user can leave the game at any point and reopening that URL will allow the user to recover the last played status (not necessarily the status of when the url was copied)
Tech notes
Frontend and backend need to communicate via an API
For the backend you are free to choose the language/framework you prefer
The front-end should be on Web or Native app
The game to be shared via URL needs to be persisted in a relational database (maybe not the best choice for this exercise, but good to test your skills in structuring data)
This game should be publicly available
Please share the repository once you finish the exercise
