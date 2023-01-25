import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Input size of the mine land : ");
        int landSize = input.nextInt();

        Object[][] mineLand = new Object[landSize + 2][landSize + 2];
        Object[][] userMineland = new Object[landSize + 2][landSize + 2];
        //String[][] userInput = new String[landSize][landSize];

        for (int i = 0; i < landSize + 2; i++)
        {
            for (int j = 0; j < landSize + 2; j++)
            {
                mineLand[i][j] = "0";
            }
        }


        for (int i = 0; i < landSize + 2; i++)
        {
            for (int j = 0; j < landSize + 2; j++)
            {
                userMineland[i][j] = "-";
            }
        }


        for (int i = 0; i < landSize * landSize / 4; i++)
        {
            int mineCoordinatx = rand.nextInt(1,landSize);
            int mineCoordinaty = rand.nextInt(1, landSize);
            if (mineLand[mineCoordinatx][mineCoordinaty].equals("*"))
            {
                i -= 1;
            }
            mineLand[mineCoordinatx][mineCoordinaty] = "*";
        }

        //mineLandmap(landSize,mineLand);
        System.out.println("=================");
        mineLandmap(landSize,userMineland);

        int xCoordinate;
        int yCoordinate;
        int xflag;
        int yflag;

        do
        {
            System.out.print("Would you like to continue with flag mode (y/n) ? : ");
            String flagMode = input.next();
            while (flagMode.equals("y"))
            {
                do
                {
                    System.out.print("Input vertical coordinate " + "1 to " + landSize + " : ");
                    xflag = input.nextInt();
                    System.out.print("Input horizontal coordinate " + "1 to " + landSize + " : ");
                    yflag = input.nextInt();
                    if (xflag < 1 || xflag > landSize || yflag < 1 || yflag> landSize)
                    {
                        System.out.println("Wrong input!\n===========");
                    }
                }
                while (xflag < 1 || xflag > landSize || yflag < 1 || yflag> landSize);

                mineFlag(xflag, yflag, userMineland);
                mineLandmap(landSize,userMineland);
                mineCounter(landSize,userMineland);
                System.out.print("Press y to continue on flag mode, n to quit : ");
                flagMode = input.next();
            }
            do
            {
                System.out.println("Input vertical coordinate " + "1 to " + landSize + " :");
                xCoordinate = input.nextInt();
                System.out.println("Input horizontal coordinate " + "1 to " + landSize + " :");
                yCoordinate = input.nextInt();
                if (xCoordinate < 1 || xCoordinate > landSize || yCoordinate < 1 || yCoordinate> landSize)
                {
                    System.out.println("Wrong input!\n===========");
                }
            }
            while(xCoordinate < 1 || xCoordinate > landSize || yCoordinate < 1 || yCoordinate > landSize);

            if (mineLand[xCoordinate][yCoordinate].equals("*"))
            {
                System.out.println("\tBOOO0M!\n\tGAME OVER!");
                mineLandmap(landSize,mineLand);
                break;
            }
            else
            {
                userMineland[xCoordinate][yCoordinate] = mineCount(xCoordinate, yCoordinate, mineLand);
                mineLandmap(landSize,userMineland);
            }

        }
        while (!isboom(xCoordinate, yCoordinate, mineLand));

        /*for (int i = 1; i <= landSize; i++)
        {
            for(int j = 1; j <= landSize; j++)
            {
                System.out.print(mineLand[i][j]+"\t");
            }
            System.out.println();
        }

        /*int x;
        int y;
        do
        {
            System.out.print("Input X (vertical direction) coordinate : ");
            x = input.nextInt();
            System.out.print("Input Y (horizontal direction) coordinate : ");
            y = input.nextInt();
        }
        while (!mineLand[y][x].equals("*"));*/

    }
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

    /*static void userLandmap(int size, Object[][] copyAry)
    {
        for (int i = 1; i <= size; i++)
        {
            for(int j = 1; j <= size; j++)
            {
                System.out.print(copyAry[i][j]+"\t");
            }
            System.out.println();
        }
    }*/
    static boolean isboom(int i, int j, Object[][] ary)
    {
        if (ary[i][j].equals("*"))
        {
            return true;
        }
            return false;
    }

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

    static void mineFlag(int x, int y, Object[][] ary)
    {
        if (ary[x][y] == ("-"))
        {
            ary[x][y] = ("#");
        }
        else
        {
            System.out.println("Wrong spot!");
        }
    }

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
       System.out.println(landSize * landSize / 4 - counter + " mine left");
    }
}
