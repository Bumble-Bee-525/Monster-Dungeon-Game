import java.util.ArrayList;
import java.lang.Math;

public class Game {
    private int playerFloor;
    private int playerRoom;
    private ArrayList<String> inventory;
    private String character;
    private String[][] map = {
            {"upstairs", "Sacred spork sword", "Sacred spork sword", "Sacred spork sword", "nothing"},
            {"downstairs", "Sacred spork sword", "monster", "monster", "upstairs"},
            {"prize", "boss monster", "Magic stones of spontaneous combustion", "monster", "downstairs"}
        };
    
    public Game(String characterChoice)
    {
        inventory = new ArrayList<String>();
        //keep in mind that the array is flipped across the x and y axis for us since the origin (0, 0) is actually the top left corner
        //so going up still is the same
        //but, going to the right actually means going minus 1, left is actually + 1
        character = characterChoice;
        playerFloor = 0;
        playerRoom = 4;
        
        if (Math.random() > 0.7)
        {
            map[0][4] = "secret forbidden rock";
        }
    }
    
    public void printInventory()
    {
        System.out.print("Current inventory: ");
        for (String item : inventory)
        {
            System.out.print("[" + item + "] ");
        }
        
        for (int i = 0; i < 3- inventory.size(); i++)
        {
            System.out.print("[empty] ");
        }
        
        System.out.println();
    }
    
    public void printLocation()
    {
        int floor = 2 - playerFloor;
        int room = 4  - playerRoom;
        
        System.out.println("Current location:");
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (i == floor && j == room)
                {
                    System.out.print("|" + character + " | ");
                }
                else
                {
                    System.out.print("|__| ");
                }
            }
            System.out.println();
        }
    }
    
    public boolean inventoryFull()
    {
        return inventory.size() == 3;
    }
    
    
    public void addInventory(String item)
    {
        inventory.add(item);
        map[playerFloor][playerRoom] = "nothing";
        System.out.println("You picked up " + item);
    }
    
    public boolean fight(String monster)
    {
        // if they have a sword
        int i = inventory.indexOf("Sacred spork sword");
        int j = inventory.indexOf("Magic stones of spontaneous combustion");
        if (i != -1 && monster.equals("monster"))
        {
            map[playerFloor][playerRoom] = "nothing";
            inventory.remove(i);
            return true;
        }
        //if they have a sword and gem
        if (i != -1 && j != -1 && monster.equals("boss monster"))
        {
            map[playerFloor][playerRoom] = "nothing";
            inventory.remove(i);
            return true;
        }
        //otherwise, they lose
        return false;
    }
    
    public String getRoomContent()
    {
        return map[playerFloor][playerRoom];
    }
    
    public int getFloor()
    {
        return playerFloor;
    }
    
    public int getRoom()
    {
        return playerRoom;
    }
    
    public void horizontalMove(int move)
    {
        playerRoom += move;
        System.out.println("You see " + getRoomContent());
    }
    
    public void verticalMove(int move)
    {
        playerFloor += move;
        System.out.println("You see " + getRoomContent());
    }
    
    public void win()
    {
        System.out.println();
        System.out.println("Congratulations adventurer, you win the game!");
        System.out.println("When you grab the prize you suddenly teleport out of the backrooms.");
        System.out.println("🎉 🎉 🎉 🎉 🎉");
    }
}