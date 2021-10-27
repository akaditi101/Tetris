/**
 * Tetrad.java  4/30/2014
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */

import java.awt.Color;

// Represents a Tetris piece.
public class Tetrad
{
    private Block[] blocks;    // The blocks for the piece.

    // Constructs a Tetrad.
    public Tetrad(BoundedGrid<Block> grid)
    {
        blocks = new Block[4];
        for (int i = 0; i < blocks.length; i++)
        {
            blocks[i] = new Block();
        }
        Color temp = Color.RED;
        int rand = (int) (Math.random() * 7);
        Location[] locations = new Location[4];
        if (rand == 0)
        {
            locations[1] = new Location(0, 3);
            locations[0] = new Location(0, 4);
            locations[2] = new Location(0, 5);
            locations[3] = new Location(0, 6);
            temp = Color.RED;
        }
        else if (rand == 1)
        {
            locations[1] = new Location(0, 4);
            locations[0] = new Location(0, 5);
            locations[2] = new Location(0, 6);
            locations[3] = new Location(1, 5);
            temp = Color.GRAY;
        }
          else if (rand == 2)
        {
            locations[1] = new Location(0, 4);
            locations[0] = new Location(0, 5);
            locations[2] = new Location(1, 4);
            locations[3] = new Location(1, 5);
            temp = Color.CYAN;
        }
          else if (rand == 3)
        {
            locations[1] = new Location(0, 4);
            locations[0] = new Location(0, 5);
            locations[2] = new Location(0, 6);
            locations[3] = new Location(1, 4);
            temp = Color.YELLOW;
        }
          else if (rand == 4)
        {
            locations[1] = new Location(0, 4);
            locations[0] = new Location(0, 5);
            locations[2] = new Location(0, 6);
            locations[3] = new Location(1, 6);
            temp = Color.MAGENTA;
        }
          else if (rand == 5)
        {
            locations[0] = new Location(1, 5);
            locations[3] = new Location(0, 5);
            locations[1] = new Location(0, 6);
            locations[2] = new Location(1, 4);
            
            temp = Color.BLUE;
        }
          else if (rand == 6)
        {
            locations[2] = new Location(0, 4);
            locations[1] = new Location(0, 5);
            locations[0] = new Location(1, 5);
            locations[3] = new Location(1, 6);
            temp = Color.GREEN;
        }
        for (Block curr : blocks)
        {
            curr.setColor(temp);
        }
        addToLocations(grid, locations);
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }


    // Postcondition: Attempts to move this tetrad deltaRow rows down and
    //                        deltaCol columns to the right, if those positions are
    //                        valid and empty.
    //                        Returns true if successful and false otherwise.
    public boolean translate(int deltaRow, int deltaCol)
    {
        BoundedGrid<Block> temp = blocks[0].getGrid();
        Location[] old = removeBlocks();
        Location[] locs = new Location[4];
        
        for (int count = 0; count < 4; count++)
        {
            if (old[count] != null)
            locs[count] = new Location(old[count].getRow() + deltaRow, old[count].getCol() + deltaCol);
        }
        Boolean test = areEmpty(temp, locs);
        if (test)
        {
            addToLocations(temp, locs);
            return true;
        }
        else
        {
            addToLocations(temp, old);
            return false;
        }

        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    // Postcondition: Attempts to rotate this tetrad clockwise by 90 degrees
    //                about its center, if the necessary positions are empty.
    //                Returns true if successful and false otherwise.
    public boolean rotate()
    {
        Location c = blocks[0].getLocation();
        BoundedGrid<Block> temp = blocks[0].getGrid();
        Location[] old = removeBlocks();
        Location[] locs = new Location[4];
        
        for (int count = 0; count < 4; count++)
        {
            Location p = old[count];
            if (old[count] != null)
            locs[count] = new Location(c.getRow() - c.getCol() + p.getCol(), c.getRow() + c.getCol()  - p.getRow());
        }
        Boolean test = areEmpty(temp, locs);
        if (test)
        {
            addToLocations(temp, locs);
            return true;
        }
        else
        {
            addToLocations(temp, old);
            return false;
        }
        
        
        
        // true;
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }


    // Precondition:  The elements of blocks are not in any grid;
    //                locs.length = 4.
    // Postcondition: The elements of blocks have been put in the grid
    //                and their locations match the elements of locs.
    private void addToLocations(BoundedGrid<Block> grid, Location[] locs)
    {
        for (int i = 0; i < 4; i++)
        {
            //if (locs[i] != null && blocks[i] != null)
                blocks[i].putSelfInGrid(grid, locs[i]);
        }
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    // Precondition:  The elements of blocks are in the grid.
    // Postcondition: The elements of blocks have been removed from the grid
    //                and their old locations returned.
    private Location[] removeBlocks()
    {
        Location [] temp = new Location[4];
        for(int i = 0; i < 4; i++)
        {
            temp[i] = blocks[i].getLocation();
            if (blocks[i].getGrid() != null)
                blocks[i].removeSelfFromGrid();
        }
        return temp;
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    // Postcondition: Returns true if each of the elements of locs is valid
    //                and empty in grid; false otherwise.
    private boolean areEmpty(BoundedGrid<Block> grid, Location[] locs)
    {
        for(Location loc : locs)
        {
            if (loc != null)
                if (!(grid.isValid(loc) && grid.get(loc) == null))
                    return false;
        }
        return true;
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }
}
