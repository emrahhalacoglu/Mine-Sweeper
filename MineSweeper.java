import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
public class MineSweeper {

    static void mineLandmap(int size,Object[][] ary)
    {
        for (int i = 1; i <= size; i++)
        {
            for(int j = 1; j <= size; j++)
            {
                System.out.print(ary[i][j]+"\t");
            }
            System.out.println();
        }
    }
    //================================================================
   static boolean isboom(int i, int j, Object[][] ary)
    {
        if (ary[i][j].equals("*"))
        {
            return true;
        }
        return false;
    }
    //================================================================
    static int mineCount(int x, int y, Object[][] ary)
    {
        int counter = 0;
        for (int i = x - 1; i <= x + 1; i++)
        {
            for (int j = y - 1; j <= y + 1; j++)
            {
                if(ary[i][j].equals("*"))
                {
                    counter++;
                }
            }
        }
        return counter;
    }
    //================================================================
    static void mineFlag(int x, int y, Object[][] ary)
    {
        ary[x][y] = ("#");
    }
    //================================================================
    static boolean checkWin(int x, int y, Object[][] ary, Object[][] userAry)
    {

        int mineCounter = 0;
        for (int i = 1; i < ary.length - 1; i++)
        {
            for (int j = 1; j < ary.length - 1; j++)
            {
                if (ary[i][j].equals("*") && userAry[i][j].equals("#"))
                {
                    mineCounter++;
                }
            }
        }
        if (mineCounter == x * y / 4)
        {
            System.out.println("\tYOU WİN!");
            return true;
        }
        return false;
    }
    //================================================================
    static void mineCounter(int landSize ,Object[][] userAry)
    {
        int counter = 0;
        for (int i = 1; i < userAry.length - 1; i++)
        {
            for (int j = 1; j < userAry.length - 1; j++)
            {
                if (userAry[i][j].equals("#"))
                {
                    counter++;
                }
            }
        }
        System.out.println(landSize * landSize - counter + " mine left");
    }
    //================================================================
}
