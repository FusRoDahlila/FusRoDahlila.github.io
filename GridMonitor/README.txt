****************
* Project 1 - GridMonitor: Avoiding an Unscheduled Space Walk
* Class: CS221-003
* Date: 8/31/23
* Name: Bethany Grentz
**************** 

OVERVIEW:

The GridMonitor is designed to identify and notify users that solar cells on the Constitution-class starship 
remain stable. If the solar cell is "TRUE" it is in danger of exploding and must be shut down immediately.


INCLUDED FILES:

    List the files required for the project with a brief
    explanation of why each is included.

    * GridMonitor.java - source file
    * GridMonitorInterface.java - source file
    * GridMonitorTest.java - test driver file
    * README - this file


COMPILING AND RUNNING:

    In user's terminal of choice, cd to the path where program is stored. Then compile the GridMonitor.java
    via the command:
    $ javac GridMonitor.java 
    Followed by running the compiled file by:
    $ java GridMonitor
    Once ran, check the terminal where it will output the base, average, delta and danger grids. 


PROGRAM DESIGN AND IMPORTANT CONCEPTS:

    This program was built to identify and shut down solar cells that are out of the average delta limits in order to prevent explosions.
    To achieve this, the GridMonitor class implements the GridMonitorInterface in order to do this. There's a constructor in GridMonitor
    which opens any .txt type file with solar cell coordinates and reads them into 2D Array form (double[][] solarArray). Once the file
    has been read, any of the methods can be called to run through algorithms including: array sum, array average, array delta and 
    a boolean TRUE/FALSE that specifies an unstable solar cell when TRUE. 

    By setting it up this way, any called method can be used inside any other method. For example, in the toString() for final output
    (what the user will see communicated to them) all methods are called to output the base grid, average grid, delta grid and the
    danger grid (boolean) so the user can verify the danger grid is accurate and then shut down the explosive cells. 

    Algorithm methods are easier to call rather than calculating all the algorithm steps in one toString() method.  Plus, by breaking
    them out each step of the algorithm helps the engineer to evaluate the data. When calculations are this complex, it's critical
    to have access to important chunks in order to troubleshoot future issues. It also helps them determine if they later decide to 
    adjust the delta tolerance of the average solar cell values, since sometimes business requires these adjustments. 

    For future versions of this program, it would be helpful to use a GUI to help the user visualize the data with colored gradient
    for the values. Then they can visually see the most sensitive blocks and can identify any patterns on the cell grid. It may
    also be good if the system could recommend solar cell adjustments to the user beyond shutting it down, because there may be
    opportunities to save money without just shutting it down. 


TESTING:

    The testing process required the GridMonitorTest driver class and multiple prints from the GridMonitor class to ensure
    the solar values were being calculated accurately. The GridMonitorTest ran many text files through the GridMonitor class
    under 33 total tests, such as getting all of the algorithm grids (base, sum, average, delta), verifying toString() output, 
    success reading/loading text files and a series of Encapsulation tests to ensure code is secure. 

    Alongside these tests, the developer used temporary prints for each grid type at each step to visualize the values and compared
    to manual calculations written out on paper. The toString() at the end of the GridMonitor class helped with final sign off
    and remains for the user to also sign off the data, since shutting down the solar cells is expensive. 

    At this time there are no known bugs, but it's possible program would not interpret imaginary numbers or may struggle with
    reading in fraction time values. Negative and positive numbers successfully
    pull through. The system might also error if it pulls in a text file with dimensions provided
    but not data set below it.


DISCUSSION:

    First issue was starting out with too many double[][] arrays in the attributes section of the GridMonitor class. My goal was to 
    pull the tables where data was stored, but this didn't work because the methods weren't called yet. It's unpredictable
    if the user will ever call them or not, so it's better to call them for the methods that need them. This also helped clean up the
    code by re-using my methods and only keeping my main base grid array in atrributes. 
    
    I most struggled with the sum part of the code, to work on this I pulled out the Minewalker project from CS121 as my reference, because 
    that project also altered cells based on the cells around them. I still struggled a bit, so I went to the Kount Learning Center and 
    Connor/Mesa helped me with that portion. That's also when Mesa helped me work through my method issue. 

    After getting help for the sum grid and average grid portions, it finally "clicked" and I was able to to the delta grid, danger grid and toString
    on my own. The danger grid was a bit tricky, but I noticed the interface worded what it needed to do and I translated it into an if statement.

    Most errors were related to the lack of methods being called, after that I had one case of out of bounds. To fix that I subtracted solarArray.length by
    1. I'm still improving my interpreting of Java errors. 

 
 
EXTRA CREDIT:

 If the project had opportunities for extra credit that you attempted,
 be sure to call it out so the grader does not overlook it.

     No EC steps taken, thank you!


----------------------------------------------------------------------------

All content in a README file is expected to be written in clear English with
proper grammar, spelling, and punctuation. If you are not a strong writer,
be sure to get someone else to help you with proofreading. Consider all project
documentation to be professional writing for your boss and/or potential
customers.

