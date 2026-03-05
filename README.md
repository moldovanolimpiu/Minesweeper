# Minesweeper
This is a recreation of the game *Minesweeper* with a few experimental features, those being the *Stun Mine* and *Rusty Mine* (more details soon). It was made in Java for the most part, with Java Swing being used for the 
interface. It has four difficulties: Easy, Medium, Hard and Custom. It also has a DevMode, which was mostly used when testing the game. 

## Gameplay
It's the run of the mill game loop of Minesweeper. Left-click to uncover a tile. If it's an empty tile, it will uncover all the empty tiles surrounding it until it reaches either the edge or a tile neighboring a mine
(a number tile). If it's a number tile, it just gets uncovered, and if it's a mine, well, it's game over. Right-click is used to flag a tile if it's suspected of being a mine. There is a counter for the amount of placed 
flags on the top right, and the number of mines on the top left. Once all the number and/or empty tiles have been clicked, without clicking on a mine in the process, the game is won.

## Difficulties

Easy:
  - 8x8 playing field
  - 10 mines  
Medium:
  - 14x15 playing field
  - 40 mines  
Hard:
  - 30 x 16 playing field
  - 99 mines  
Custom:
  - up to 88x37 playing field (the numbers can go beyond, the limit, but it may look weird)
  - the number of tiles - 1 (so the field is not completely full of mines 

