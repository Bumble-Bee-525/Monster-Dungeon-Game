import java.util.Scanner;

public class gameMain
{
    public static void main(String[] args)
    {
        //This program was made by Bradley Liang
        //intro
        System.out.println("Welcome to the Text Monster Game!");
        System.out.println("You have noclipped into the backrooms and must find the prize to escape.");
        System.out.println("type 'help' for a list of commands");
        System.out.println();
        
        
        
        //declare variables
        Game game;
        String[] characters = {"🙂", "🎅", "🧐", "👽", "🤠"};
        Scanner in = new Scanner(System.in);
        String userAction = "";
        boolean temp = true;
        int prevRoom = 0;
        
        //prompt user for character choice
        System.out.println("Choose a character: 🙂 🎅 🧐 👽 🤠");
        System.out.println("Enter a number 1-5 to choose");
        
        //get user to choose a character
        while (true)
        {
            //there isn't really much I can do if the user types in a letter and not a number. Both pareInt and nextInt will automatically throw errors.
            int userCharacter = Integer.parseInt(in.nextLine());
            if (userCharacter > 0 && userCharacter < 6)
            {
                game = new Game(characters[userCharacter - 1]);
                break;
            }
            else
            {
                System.out.println("Not valid. Try again.");
            }
        }
        System.out.println();


        //run game
        while (true)
        {
            //set a temporary variable to true
            temp = true;
            while (temp)
            {
                //get user action
                game.printInventory();
                game.printLocation();
                System.out.println("What is your move?");
                userAction = in.nextLine();
            
                //find appropiate action based on the user's action
                switch (userAction)
                {
                    case "help":
                        System.out.println("type 'up', 'down', 'left', 'right' to move in these directions.");
                        System.out.println("you can only go up upstairs and down downstairs");
                        System.out.println("type 'grab' to grab a weapon in a room");
                        System.out.println("type 'fight' to attack a monster if one is in your room");
                        System.out.println("type 'help' to see this tutorial again.");
                        break;
                    //if they choose left
                    case "left":
                        //if the room is the far left room
                        if (game.getRoom() == 4)
                        {
                            //alert the user
                            System.out.println("There is no room to the left. Try again pls");
                        }
                        //if there is a monster
                        else if (game.getRoomContent().equals("monster") || game.getRoomContent().equals("boss monster"))
                        {
                            //if moving is a retreat
                            if (game.getRoom() + 1 == prevRoom)
                            {
                                temp = false;
                                prevRoom = game.getRoom();
                                game.horizontalMove(1);
                            }
                            else
                            {
                                //if moving is past the monster
                                System.out.println("You tried to move past the monster. R.I.P");
                                System.out.println("💀 You lose!");
                                return;
                            }
                        }
                        else
                        {
                            //move the character, change temp to false so while loop breaks
                            temp = false;
                            prevRoom = game.getRoom();
                            game.horizontalMove(1);
                        }
                        break;

                    //if they choose right
                    case "right":
                        //if the room is  the far right room
                        if (game.getRoom() == 0)
                        {
                            //alert user
                            System.out.println("There is no room to the right. Try again pls");
                        }
                        //if there is a monster
                        else if (game.getRoomContent().equals("monster") || game.getRoomContent().equals("boss monster"))
                        {
                            //if moving is a retreat
                            if (game.getRoom() - 1 == prevRoom)
                            {
                                //move the character, change temp to false so while loop breaks
                                temp = false;
                                prevRoom = game.getRoom();
                                game.horizontalMove(-1);
                            }
                            else
                            {
                                //if moving is past the monster
                                System.out.println("You tried to move past the monster. R.I.P");
                                System.out.println("💀 You lose!");
                                return;
                            }
                        }
                        
                        else
                        {
                            //move the character, change temp to false so while loop breaks
                            temp = false;
                            prevRoom = game.getRoom();
                            game.horizontalMove(-1);
                        }
                        break;
                    
                    //if they choose up
                    case "up":
                        //if the room is an upstairs
                        if (game.getRoomContent().equals("upstairs"))
                        {
                            //move the character, change temp to false so while loop breaks
                            temp = false;
                            game.verticalMove(1);
                        }
                        //if they can't move up
                        else
                        {
                            //alert user
                            System.out.println("Can not move up. Try again please");
                        }
                        break;
                    
                    //if they choose down
                    case "down":
                        //if the room is a downstairs
                        if (game.getRoomContent().equals("downstairs"))
                        {
                            //move the character, change temp to false so while loop breaks
                            temp = false;
                            game.verticalMove(-1);
                        }
                        //if they can't move down
                        else
                        {
                            //alert user
                            System.out.println("Can not move down. Try again please");
                        }
                        break;
                    
                    //if they choose to fight
                    case "fight":
                        if (game.getRoomContent().equals("monster") || game.getRoomContent().equals("boss monster"))
                        {
                            temp = false;
                            if (game.fight(game.getRoomContent()))
                            {
                                System.out.println("You killed the monster!");
                            }
                            else
                            {
                                System.out.println("You did not have the weapons to kill the monster. R.I.P ");
                                System.out.println("💀 You lose!");
                                return;
                            }
                        }
                        else
                        {
                            System.out.println("You try to fight the empty room. You look dumb doing this. Try something else?");
                        }
                        break;
                        
                    //if they choose to grab item
                    case "grab":
                        //if inventory is full, alert user
                        if (game.inventoryFull())
                        {
                            System.out.println("Inventory is full");
                        }
                        //if item is a valid weapon, add it to the inventory
                        else if (game.getRoomContent().equals("Sacred spork sword") || game.getRoomContent().equals("Magic stones of spontaneous combustion") || game.getRoomContent().equals("secret forbidden rock"))
                        {
                            temp = false;
                            game.addInventory(game.getRoomContent());
                        }
                        else if (game.getRoomContent().equals("monster") || game.getRoomContent().equals("boss monster"))
                        {
                            System.out.println("You start to try to pick up the monster but you get a feeling this isn't a good idea. The monster looks confused. Try something else?");
                        }
                        //if they've won the prize, they win
                        else if (game.getRoomContent().equals("prize"))
                        {
                            temp = false;
                            game.addInventory("prize");
                            game.win();
                            return;
                        }
                        else
                        {
                            System.out.println("No sword or stone to grab");
                        }
                        break;
                    //if what they typed doesn't match, alert user
                    default:
                        System.out.println("Action doesn't exist. Try again pls");
                }
            }
            
            System.out.println();
        }
    }
}