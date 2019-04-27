Team31 COMP 3004
Members: Korede Adegboye, Amr Shurman, Y Pham, Matthew Seferian Kaprielian-Seferian 
Date: Dec.5th/2018

Platform: Eclispe Java Photon
Build All 
Start: Run Ui.java as a java application

Main Menu
	- The use of a timer during the game is optional.

Playing with Humans (2 players, 3 Players, 4 Players)
	Choose the stratgies you would prefer to play with (Playing with 2 humans is not yet suppprted)
	The mini game, regarding pick-a tile displays the results in the console 
	Once the user is done picking tiles from there hand, they must press the end turn button 
	User gets to keep track of their school at all times 
	User gets to keep track of the tiles played by other players. This resets every round
	If timer is on, the user has 2 minutes to play tiles from their hand or their turn ends
	If user clicks the suggestions button once; Tiles suggested tiles will be highlighted, 
	If user clicks the button again; details as to why those tiles should be discarded will be outputed in the console   
	If the user wins, they will be prompted with a "Winner" screen 
	If the user wins, they will be prompted with a "Loser" screen
	
	4 Human Players 
		After the current Humans turn ends, the Hand of the next Humans turn will appear 
		

AI's only 
	Once this button (option) is chosen; upon entry the AI's will start to play the game 
	To continue seeing the results of the AI's playing the user needs to press the end turn button 

Scenarios (Design Pattern)
	Several game-rigging scenarios involving functionality of the game can be tested.
	Rigged (doesnt apply to all scenarios)
		The initial hands of the player(s) 
		Tiles are placed on the table
		Initial meld is complete 
		The next tile to be drawn, if they cant play 
		

Scenarios (File Input) 
	Enter a valid file name, then click the submit button. Txt File needs to be inside target folder
	This order must be followed 

		A strategy; {S1, S2, S3, S4} 
		C - initital meld complete {indicated or not}
		Initital Hand of the current strategy - {Format: ColorNumber i.e., R1 (Red 1) B2 (Blue 2)}
		D - indicated tiles to be drawn, after initial turn
		O - indicate order of players; must have order for each player stated 
		N - indicated number of turns to play, i.e. 1 turn is a players turn  
	
	order of strategies does not matter
	
Link to Videos: https://cmailcarletonca-my.sharepoint.com/:f:/r/personal/amrshurman_cmail_carleton_ca/Documents/TEAM%2031%20iteration%202%20vids?csf=1&e=3TZEfb
	
	
	


	