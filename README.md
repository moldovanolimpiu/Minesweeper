# Minesweeper
This is a recreation of the game *Minesweeper* with a few experimental features, those being the *Stun Mine* and *Rusty Mine* (more details soon). It was made in Java for the most part, with Java Swing being used for the 
interface. It has four difficulties: Easy, Medium, Hard and Custom. It also has a DevMode, which was mostly used when testing the game. 

## Gameplay
It's the run of the mill game loop of Minesweeper. Left-click to uncover a tile. If it's an empty tile, it will uncover all the empty tiles surrounding it until it reaches either the edge or a tile neighboring a mine
(a number tile). If it's a number tile, it just gets uncovered, and if it's a mine, well, it's game over. Right-click is used to flag a tile if it's suspected of being a mine. There is a counter for the amount of placed 
flags on the top right, and the number of mines on the top left. Once all the number and/or empty tiles have been clicked, without clicking on a mine in the process, the game is won.

## Difficulties

Easy:
  - field: 8x8 tiles  
  - mines: 10  

Medium:
  - field: 14x15 tiles  
  - 40 mines  

Hard:
  - field: 30x16 tiles  
  - 99 mines  

Custom:
  - field: up to 88x37 playing field (the numbers can go beyond, the limit, but it may look weird)
  - mines: the number of tiles - 1 (so the field is not completely full of mines

## The new mines

The Stun and Rusty mines are the new addition to the game. They are more forgiving if the player happens to click on them, due to the fact that the game is not immediately over. Despite this, they still pose a significant 
threat. They can be enabled from the main menu, and as a result, 3 of each appear regardless of difficulty.  

### Stun mine

A stun or concussion mine is used to disorient the one unforunate enough to step on it. If this mine is clicked, instead of ending the game, it covers at random, half of the number tiles that have been discovered thus far, without affecting any of the empty tiles. This also removes all the previously place flags, thus "stunning" the player by making them "forget" what has been discovered until that point. If this mine is triggered in the earlier stages of the game, the effect is rather limited. On the other hand, triggering it during late-game will set the user much further back, due to the flags disappearing.

### Rusty mine

A rusty mine is a regular mine that has been placed in the field a long time ago. As a result, the internal mechanisms have deteriorated, making the triggering of the mine much slower than contemporary mines. If one of these mines are clicked, a small window pops up with a minigame. This window contains a 10 second timer, and four wires with numbers from 1 to 4 above them and arranged in a random order. The wires must be cut in order, starting from 1 and ending at 4. If the wires are cut in any other order, the mine detonates and the game is instantly lost. Same if the 10 second timer runs out.

## DevMode

It's a feature that allows the player to instantly win the game, regardless of difficulty. Once a game is started, a small red *Win* button shows up on the bottom left of the screen, which if clicked, instantly ends the game in a win. In addition, if a rusty mine is clicked while DevMode is activated, the minigame cand be closed immediately from a new button that appears in the minigame window, with zero consequences.

## Future improvements

The initial idea was to make some sort of story driven campaign, with progress, stats and multiple endings. This was the motivation behind the new mines. This idea might be expanded in the future.

