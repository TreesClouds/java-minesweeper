import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(20, 20, 20); 
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                double rn = Math.random();
                if (rn < 0.158) {
                    addObject(new Tile(true), x, y);
                } else {
                    addObject(new Tile(), x, y);
                }
            }
        }
        for (Tile i : getObjects(Tile.class)) {
            i.determineSurrounding();
        }
    }
}
