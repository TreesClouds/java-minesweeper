import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile extends Actor
{
    /**
     * Act - do whatever the Tile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public boolean clicked = false;
    public boolean flagged = false;
    public int surrounding = 0;
    public boolean bomb;
    
    public Tile() {
        bomb = false;
    }
    
    public Tile(boolean initBomb) {
        bomb = initBomb;
    }
    
    public void determineSurrounding() {
        int count = 0;
        for (Tile t : getNeighbours(1, true, Tile.class)) {
            if (t.bomb) {
                count++;
            }
        }
        surrounding = count;
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(this) && Greenfoot.getMouseInfo().getButton() == 1 && !clicked) {
            click();
        } else if (Greenfoot.mouseClicked(this) && Greenfoot.getMouseInfo().getButton() == 3 && !clicked) {
            toggleFlag();
        }
    }
    
    public void click()
    {
        clicked = true;
        if (flagged) {
            toggleFlag();
        }
        if (bomb) {
            for (Tile t : getWorld().getObjects(Tile.class)) {
                t.reveal();
                Greenfoot.stop();
            }
        } else {
            reveal();
        }
    }
    
    public void toggleFlag() {
        flagged = !flagged;
        if (flagged) {
            setImage("flagged.png");
        } else {
            setImage("tile.png");
        }
        
        // scoring
    }
    
    public void reveal() {
        if (bomb) {
            setImage("bomb.png");
        } else {
            setImage(surrounding + ".png");
        }
        if (surrounding == 0) {
            for (Tile t : getNeighbours(1, true, Tile.class)) {
                if (!t.clicked) {
                    t.click();
                }
            }
        }
    }
}
