/*
HOMEWORK #4
Suppose the Registrar of Voters retains only two files about her voters, a roster 
of legal voter ids and another file with the ids of people who cast a vote.  
Madam Registrar verifies the election results in steps:

1. Unique.  She goes through her cast votes file and makes sure every id is listed only once. 
You can assume the ids are in sorted order.
2. Valid.  She makes sure every cast vote is an entry in her roster.

Your program should output either Verified, Not Unique, or Not Valid, and nothing else.
 */
package homework4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Homework4
{

    public static void main(String[] args)
    {
       //createFiles(); // Optional to create files
        if (castCheck())
            if (validCheck())
                System.out.println("Verified");
            else
                System.out.println("Not Valid");
        else
            System.out.println("Not Unique");

    }

    // Create the two files
    public static void createFiles()
    {
        try
        {
            // Registered voters
            PrintWriter writeFile1 = new PrintWriter(new File("voters.txt"));
            for (int i = 0; i < 10; i++)
            {
                writeFile1.println(i);
            }
            writeFile1.close();

            // Voters who have casted a vote
            PrintWriter writeFile2 = new PrintWriter(new File("cast.txt"));
            for (int i = 0; i < 10; i++)
            {
                writeFile2.println(i);
            }
            writeFile2.close();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    // Check if there are no repeating values in casted votes.
    public static boolean castCheck()
    {
        boolean check = true;
        try
        {
            Scanner castRead = new Scanner(new File("cast.txt"));
            String currentValue = castRead.nextLine();
            while (castRead.hasNext())
            {
                String nextValue = castRead.nextLine();
                if (currentValue.equals(nextValue))
                {
                    check = false;
                }
                currentValue = nextValue;
            }
            castRead.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e);
        }
        return check;
    }

    // Check if every cast vote is a valid voter. 
    public static boolean validCheck()
    {
        int flagCount1 = 0;
        int flagCount2 = 0;
        String voter = "";
        String cast = "";
        try
        {
            Scanner castRead = new Scanner(new File("cast.txt"));
            while(castRead.hasNext())
            {
                flagCount1++;
                voter = castRead.nextLine();
              
               Scanner voteRead = new Scanner(new File("voters.txt"));
                while(voteRead.hasNext())
                {
                    cast = voteRead.nextLine();
                    if (voter.equals(cast))
                    {
                        flagCount2++;
                    }
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("ERROR: " + e);
        }
        return flagCount1 == flagCount2;
    }

}
