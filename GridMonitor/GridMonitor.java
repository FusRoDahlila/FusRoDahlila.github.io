//Imports List
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * GridMonitor - Warmup Assignment
 * 
 * @description GridMonitor monitors solar array stability levels and determines
 *              how dangerous they are. If they are at risk, the
 *              system will identify the cells and shut them down before the
 *              starship leaves for space.
 * 
 * @class CS221-003
 * @author Bethany Grentz
 * 
 */
public class GridMonitor implements GridMonitorInterface {

    //Attributes:
    private String fileName; // Name of the file name being read
    private double[][] solarArray; // 2D Array containing solary array stability levels
    private int rows;
    private int cols;

    /**
     * Main Constructor
     * Opens, reads plain text files using one or more file readers. Throws if file
     * name is not recognized.
     * 
     * @param fileName name of the file being read
     * @throws FileNotFoundException in cases where file name is not recognized
     */
    public GridMonitor(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        // Defining array's dimensions
        rows = scanner.nextInt();
        cols = scanner.nextInt();
        solarArray = new double[rows][cols];

        // Populating the data in the array
        for (int x = 0; x < (rows); x++) {
            for (int y = 0; y < (cols); y++) {
                solarArray[x][y] = scanner.nextDouble();
            }
        }
        scanner.close();
        return;
    }

    @Override
    public double[][] getBaseGrid() {
        double[][] baseGrid = new double[rows][cols];
        for (int x = 0; x < (rows); x++) {
            for (int y = 0; y < (cols); y++) {
                baseGrid[x][y] = solarArray[x][y];
            }
        }
        return baseGrid;
    }

    @Override
    public double[][] getSurroundingSumGrid() {
        double[][] totalSum = new double[solarArray.length][solarArray[0].length];
        //For-loops to check above, below, left and right of target solar cell 
        for (int x = 0; x < solarArray.length; x++) {
            for (int y = 0; y < solarArray[x].length; y++) {
                if (y == 0) {
                    totalSum[x][y] += solarArray[x][y];
                } else {
                    totalSum[x][y] += solarArray[x][y - 1];
                }
                if (x > 0) {
                    totalSum[x][y] += solarArray[x - 1][y];
                } else {
                    totalSum[x][y] += solarArray[x][y];
                }
                if (y == (solarArray[x].length - 1)) {
                    totalSum[x][y] += solarArray[x][y];
                } else {
                    totalSum[x][y] += solarArray[x][y + 1];
                }
                if (x == (solarArray.length - 1)) {
                    totalSum[x][y] += solarArray[x][y];
                } else {
                    totalSum[x][y] += solarArray[x + 1][y];
                }
            }
        }
        return totalSum;
    }

    @Override
    public double[][] getSurroundingAvgGrid() {
        double[][] avgGrid = getSurroundingSumGrid();
        for (int x = 0; x < solarArray.length; x++) {
            for (int y = 0; y < solarArray[x].length; y++) {
                avgGrid[x][y] /= 4; // Divide by 4 for average
            }
        }
        return avgGrid;
    }

    @Override
    public double[][] getDeltaGrid() {
        double[][] deltaGrid = getSurroundingAvgGrid();
        for (int x = 0; x < solarArray.length; x++) {
            for (int y = 0; y < solarArray[x].length; y++) {
                deltaGrid[x][y] = Math.abs(deltaGrid[x][y] / 2); //Take absolute and divide by 2 for delta values
            }
        }
        return deltaGrid;
    }

    @Override
    public boolean[][] getDangerGrid() {
        double[][] avgGrid = getSurroundingAvgGrid();
        double[][] deltaGrid = getDeltaGrid();
        boolean[][] dangerGrid = new boolean[rows][cols];
        //For-loops and if statements determine if solar cell is in danger for explosion
        for (int x = 0; x < solarArray.length; x++) {
            for (int y = 0; y < solarArray[x].length; y++) {
                if (solarArray[x][y] < (avgGrid[x][y] - deltaGrid[x][y])
                        || solarArray[x][y] > (avgGrid[x][y] + deltaGrid[x][y])) {
                    dangerGrid[x][y] = true;
                } else {
                    dangerGrid[x][y] = false;
                }
            }
        }
        return dangerGrid;
    }

    @Override
    public String toString() {
        //Running methods to feed into StringBuilder
        StringBuilder allGrids = new StringBuilder();
        double[][] sumGrid =  getSurroundingSumGrid();
        double[][] avgGrid = getSurroundingAvgGrid();
        double[][] deltaGrid = getDeltaGrid();
        boolean[][] dangerGrid = getDangerGrid();

        //Appending Stringbuilder to output Solar Array data
        allGrids.append("\nBase Grid: \n");
        for (int x = 0; x < solarArray.length; x++) {
            for (int y = 0; y < solarArray[x].length; y++) {
                allGrids.append(solarArray[x][y] + "  ");
            }
            allGrids = allGrids.append("\n"); 
        }
        allGrids.append("\nSum Grid: \n");
        for (int x = 0; x < solarArray.length; x++) {
            for (int y = 0; y < solarArray[x].length; y++) {
                allGrids.append(sumGrid[x][y] + "  ");
            }
            allGrids = allGrids.append("\n"); 
        }
        allGrids.append("\nAvg Grid: \n");
        for (int x = 0; x < solarArray.length; x++) {
            for (int y = 0; y < solarArray[x].length; y++) {
                allGrids.append(avgGrid[x][y] + "  ");
            }
            allGrids.append("\n");
        }
        allGrids.append("\nDelta Grid: \n");
        for (int x = 0; x < solarArray.length; x++) {
            for (int y = 0; y < solarArray[x].length; y++) {
                allGrids.append(deltaGrid[x][y] + "  ");
            }
            allGrids.append("\n"); 
        }
        allGrids.append("\n ***DANGER*** Grid: \n");
        for (int x = 0; x < solarArray.length; x++) {
            for (int y = 0; y < solarArray[x].length; y++) {
                allGrids.append(dangerGrid[x][y] + "  ");
            }
            allGrids.append("\n");
        }
        allGrids.append("\nWARNING: SHUTDOWN TRUE CELLS IMMEDIATELY TO AVOID EXPLOSION\n");
        return allGrids.toString();
    }
}
