# Text-based-game project
**Assignment #2 – World of Zuul**

Title: World of Zuul

Author: Szabolcs Daniel Nagy

Student number: 20073246

Due Date: 02/12/2020

I did not change the original name of the game; it remained the same. My game was built on the second example given. The player finds himself lost in a dungeon, and he must find a way out. In the adventure the player can interact with other characters, obtain powerful and interesting items, also use them. The main objective of the game is to get out of the dungeon, which can be done by finding the bread in the dungeon. After giving the bread to the dwarf, he gives a wand in exchange, which upon use teleports you out of the dungeon. Implemented features which I am proud of:

- processCommand method refactored (HashMap instead of a switch)
- Automated NPC movement
- pickup, drop and use items
- Interaction with NPCs, player can give items to them also
- back command

Base tasks completed: (Tasks which were already implemented in the game are not included here)

1. The game has several locations/rooms.
  - Firstly, I drew a map of the game with all the rooms and exits on paper, then I translated it into code.
2. There are items in some rooms. Every room can hold any number of items. Some items can be picked up by the player, others can&#39;t.
  - I created an Item and an Inventory class, which I think were a must to create as new classes, because if I were to extend the game it would not be possible without refactoring almost the whole game class. Inventories store the items in a HashMap (item, amount). For the rooms I did not use the Inventory class, rather I just stored an ArrayList of items in them, because I think it is more logical if they can carry any number of items. Items which the players can&#39;t pick up were basically given an unrealistic weight (999), which size the player&#39;s inventory never has.
3. The player can win. There must be some situation that is recognised as the end of the game where the player is informed that they have won. oThe player can win by giving the bread to the dwarf, which leads to obtaining a magical wand. I solved this problem by creating an endGameScenario method, which checks if the player has given the bread to the dwarf. The player can also lose the game, if the goes over the limit of the maximum steps

he can take. Upon losing/winning, the game asks if the player wants to play another game. If not, the program terminates.

1. Implement a command &quot;back&quot; that takes you back to the last room you&#39;ve been in.
  - I solved this by creating a Stack field in the Player class, which holds the previous rooms the player has been in. I got this idea from the book.
2. Add at least four new commands (in addition to those that were present in the code you got from us).
  - (Drop, give, charge, use, pickup, back, interact, inventory, look, help, fire) → these commands will be explained further in the report Challenge tasks completed:

1. Add characters to your game. Characters are people or animals or monsters – anything that moves, really. Characters are also in rooms (like the player and the items). Unlike items, characters can move around by themselves. oI created an abstract class Character, which were further extended by the NPC and the Player class. I did it this way, because these two classes had common methods and fields. The Character class helped me to avoid code duplication. I solved the NPC movement by randomly moving them each time the player moves (Back command doesn&#39;t count; they only move if the player uses the &#39;go&#39; command in a possible direction). I also considered them moving in a certain pattern, but I rather found that boring than exciting. The randomness was solved by obtaining all the possible direction an NPC can move to, then selecting a random element of that collection. For example, if I want to move an NPC in the game, I have to must set the &#39;moving&#39; field to true (false on default), other processes are automated.
2. Extend the parser to recognise three-word commands. You could, for example, have a command give bread dwarf to give some bread (which you are carrying) to the dwarf.
  - I had to refactor the code of the Parser and extend it by adding a third String to be able to read three-word commands. The &#39;give&#39; command as an example was solved this way, but the user has to type the ID of the items/NPCs rather the name. I liked it better this way. (Each NPC, item has an automatically generated separate ID).
3. Add a magic transporter room – every time you enter it you are transported to a random room in your game.
  - Upon initializing the rooms each room is added to an ArrayList except for the teleporter room. When the player enters the teleporter room, there is a method that returns a random element from the initialized rooms (excluded teleporter). Entering the teleport, it also flushes the Stack of the player&#39;s previous rooms.

Other tasks I have implemented in my game: (I used several ideas from the book)

1. Add some form of time limit to the game oSolved by counting the number of steps the player has taken and comparing it to the maximum movement allowed in the game. The maximum movement is a random number between 10 and 16 (it is randomly generated every time the player starts a new game). I found it more interesting this way.
2. Implement a trapdoor oI created a room which has no exits. The player can&#39;t escape from this room, except if the player has the beamer or the wand item. The beamer is automatically added to the player&#39;s inventory when the game starts.

1. Add a beamer to the game oThe player spawns with a beamer item. This item can be charged and fired. When charged, the item stores the current room of the player. Upon firing it teleports back the player to the room.
2. Add locked doors to the game oRooms have a field whether they are locked or not. Locked doors can be opened with keys found in the dungeon. The game checks whether a key has been used and unlocks the door which is locked.
3. Usable items oThe players can use the items they obtain (Some of them are not usable). For example, the beamer can only be charged and fired, not used. Each item is removed from the player inventory and can only be used once.

Code quality considerations:

1. Responsibility driven design oEach class contains their own methods, fields which were not implemented in the original game. All the commands were in the Game class, so I extracted each command to their own class. As an example: player&#39;s goRoom command.
2. Coupling oThere is loose coupling in the project between classes. I designed each of them so that they are not highly dependent on others. Of course, there must be a loose coupling, otherwise the program would not work.
3. Cohesion oEach class is designed with a single purpose. Before the Game class created all the rooms, exits etc. For this purpose, I created the &#39;initializers&#39; package, which contains the initializer for each purpose (item, room, player, NPC). Each initializer has its unique single purpose.
4. Maintainability oI documented the whole program according to the Javadoc style. Every method and class have its own comment, which are easily understandable. The program can be maintained easily by only reading the needed method/class comments.

Walk-through for the game

![](RackMultipart20201204-4-xc0ai6_html_e8fbadc93ad74b1e.jpg)

**The following image was taken from the game. (The player uses the map which can be found in the Storage Room)**

When the game is started the player must enter the name of their character. Writing the following lines leads to a win:

- go north
- charge
- go up
- go east
- pickup [id of bread]
- fire
- go down
- go north
- go east
- go east
- give [id of bread] [id of dwarf]
- inventory
- use [id of wand]

# Sources:
- Objects First with Java (6th Edition)
- [www.stackoverflow.com](http://www.stackoverflow.com/)(Used code from this website is listed in the code)
- FIGLET Generator (for drawing the map and the welcome message of the program)
