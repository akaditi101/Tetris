/**
 * BoundedGrid.java  04/30/14
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */

import java.util.List;
import java.util.ArrayList;

// A BoundedGrid is a rectangular grid with a finite number of rows and columns.
public class BoundedGrid<E>
{
    private Object[][] occupantArray;  // the array storing the grid elements

    // Constructs an empty BoundedGrid with the given dimensions.
    // Precondition:  0 < rows and 0 < cols.
    public BoundedGrid(int rows, int cols)
    {
        occupantArray = new Object[rows][cols];
    }

    // Returns the number of rows.
    public int getNumRows()
    {
        return occupantArray.length;
    }

    // Returns the number of columns.
    public int getNumCols()
    {
        return occupantArray[0].length;
    }

    // Returns the object at location loc
    // (or null if the location is unoccupied).
    // Precondition:  loc is valid in this grid.
    @SuppressWarnings("unchecked")
    public E get(Location loc)
    {
        return (E)occupantArray[loc.getRow()][loc.getCol()];
    }

    // Puts obj at location loc in this grid and returns the object previously
    // at that location (or null if the location is unoccupied).
    // Precondition:  loc is valid in this grid.
    public E put(Location loc, E obj)
    {
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    // Returns true if loc is valid in this grid, false otherwise.
    // Precondition:  loc is not null.
    public boolean isValid(Location loc)
    {
        //hint:  use getNumRows and getNumCols
        if (loc.getRow() < getNumRows() && loc.getCol() < getNumCols() && loc.getRow() >= 0 && loc.getCol() >= 0)
            return true;
        return false;
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    // Removes the object at location loc from this grid and returns
    // the object that was removed (or null if the location is unoccupied).
    // Precondition:  loc is valid in this grid.
    public E remove(Location loc)
    {
        //hint:  use the put method to store a null at this location
        E temp = get(loc);
        put(loc, null);
        return temp;
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    // Returns a list of all occupied locations in this grid.
    public List<Location> getOccupiedLocations()
    {
        //hint:  use the get method to determine if a location is empty
        ArrayList<Location> occupiedLocs = new ArrayList<Location>();
        Location curr = new Location(0,0);
        for(int i = 0; i < getNumRows(); i++)
        {
            for (int j = 0; j < getNumCols(); j++)
            {
                curr = new Location(i, j);
                if (get(curr) != null)
                    occupiedLocs.add(curr); 
            }
            
        }
        return occupiedLocs;
    }
        
    
    }
