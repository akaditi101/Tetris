/**
 * Tetris.java  4/30/2014
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */

// Represents a Tetris game.
public class Tetris implements ArrowListener
{
    private BoundedGrid<Block> grid;    // The grid containing the Tetris pieces.
    private BlockDisplay display;        // Displays the grid.
    private Tetrad activeTetrad;        // The active Tetrad (Tetris Piece).

    // Constructs a Tetris Game
    public Tetris()
    {
        grid = new BoundedGrid<Block>(20, 10);
        display = new BlockDisplay(grid);
        display.setArrowListener(this);
        display.setTitle("Tetris");
        activeTetrad = new Tetrad(grid);
        
        //activeTetrad.translate(1,10);
        display.showBlocks();
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    // Play the Tetris Game
    public void play()
    {
        
        for (int i = 0; i < 5; i--)
        {
            sleep(1.0);
            downPressed();
            display.showBlocks();
            if (!activeTetrad.translate(1, 0))
            {
                clearCompletedRows(); 
                activeTetrad = new Tetrad(grid);
                 
            }
        }
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }


    // Precondition:  0 <= row < number of rows
    // Postcondition: Returns true if every cell in the given row
    //                is occupied; false otherwise.
    private boolean isCompletedRow(int row)
    {
        //does rotate work??
        Boolean x = true;
        for (int i = 0; i < 10; i++)
        {
            Location curr = new Location(row, i);
            if (grid.get(curr) == null)
                x = false;
        }
        return x;
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    // Precondition:  0 <= row < number of rows;
    //                The given row is full of blocks.
    // Postcondition: Every block in the given row has been removed, and
    //                every block above row has been moved down one row.
    private void clearRow(int row)
    {
        for (int i = 0; i < 10; i++)
        {
            Location curr = new Location(row, i);
            grid.put(curr, null);
        }
        for (int i = row -1; i > 0; i --)
        {
            for (int j = 0; j < 10; j++)
            {
                Location curr = new Location(i, j);
                Block temp = grid.get(curr);
                Location curr2 = new Location(i + 1, j);
                if (grid.isValid(curr2))
                    grid.put(curr2, temp);
                grid.remove(curr);
            }
        }
        for (int i = 0; i < 10; i++)
        {
            Location curr = new Location(0, i);
            grid.put(curr, null);
        }
        display.showBlocks();
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    // Postcondition: All completed rows have been cleared.
    private void clearCompletedRows()
    {
        for (int i = 0; i < 20; i++)
        {
            if (isCompletedRow(i))
            {
                clearRow(i);
                //activeTetrad = null;
            }
        }
        
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    // Sleeps (suspends the active thread) for duration seconds.
    private void sleep(double duration)
    {
        final int MILLISECONDS_PER_SECOND = 1000;

        int milliseconds = (int)(duration * MILLISECONDS_PER_SECOND);

        try
        {
            Thread.sleep(milliseconds);
        }
        catch (InterruptedException e)
        {
            System.err.println("Can't sleep!");
        }
    }

    public void upPressed()
    {
        activeTetrad.rotate();
        display.showBlocks();
    }
    
    public void downPressed()
    {
        activeTetrad.translate(1,0);
        display.showBlocks();
    }
    
    public void leftPressed()
    {
        activeTetrad.translate(0,-1);
        display.showBlocks();
    }
    
    public void rightPressed()
    {
        activeTetrad.translate(0,1);
        display.showBlocks();
    }

    // Creates and plays the Tetris game.
    public static void main(String[] args)
    {
        Tetris tester = new Tetris();
        tester.play();
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }
}
