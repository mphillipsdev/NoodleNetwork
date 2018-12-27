package com.nn.Beans;

public class Tank
{

    public int direction = 1; // 1 = north, 2 = east, 3 = south, 4 = west
    public int xLoc = 0;
    public int yLoc = 0;
    public boolean canGoLeft = false;
    public boolean canGoRight = false;
    public boolean canGoForward = false;

    public void setDirectionals()
    {
        canGoForward = canGoLeft = canGoRight = false;
        switch(direction)
        {
            // if facing north:
            case 1:
            {
                // if yLoc != 10, canGoForward
                if(yLoc != 10) canGoForward = true;
                // if xLoc != 0, canGoLeft
                if(xLoc != 0) canGoLeft = true;
                // if xLoc != 10, canGoRight
                if(xLoc != 10) canGoRight = true;
                break;
            }
            // if facing east:
            case 2:
            {
                // if xLoc != 10, canGoForward
                if(xLoc != 10) canGoForward = true;
                // if yLoc != 10, canGoLeft
                if(yLoc != 10) canGoLeft  = true;
                // if yLoc != 0, canGoRight
                if(yLoc != 0) canGoRight = true;
                break;
            }
            // if facing south:
            case 3:
            {
                // if yLoc != 0, canGoForward
                if(yLoc != 0) canGoForward = true;
                // if xLoc != 10, canGoLeft
                if(xLoc != 10) canGoLeft = true;
                // if xLoc != 0, canGoRight
                if(xLoc != 0) canGoRight = true;
                break;
            }
            // if facing west:
            case 4:
            {
                // if xLoc != 0, canGoForward
                if(xLoc != 0) canGoForward = true;
                // if yLoc != 0, canGoLeft
                if(yLoc != 0) canGoLeft = true;
                // if yLox != 10, canGoRight
                if(yLoc != 10) canGoRight = true;
                break;
            }
            default:
            {
                System.out.println("You're not supposed to be here.");
                break;
            }
        }
    }

    public void turnLeft(int i)
    {
        direction--;
        if(direction == 0)
            direction = 4;
        System.out.println("Turning left! Now facing " + nameDirection() + " (" + i + ")");
    }

    public void turnRight(int i)
    {
        direction++;
        if(direction == 5)
            direction = 1;
        System.out.println("Turning right! Now facing " + nameDirection() + " (" + i + ")");
    }

    public void moveForward(int i)
    {
        if(canGoForward)
        {
            System.out.println("Moving " + nameDirection() + "!" + " (" + i + ")");
            switch(direction)
            {
                case 1:
                {
                    yLoc++;
                    break;
                }
                case 2:
                {
                    xLoc++;
                    break;
                }
                case 3:
                {
                    yLoc--;
                    break;
                }
                case 4:
                {
                    xLoc--;
                    break;
                }
                default:
                {
                    System.out.println("You're not supposed to be here.");
                    break;
                }
            }
        }
        else
            System.out.println("Bump! (Can't go " + nameDirection() + ")");
    }

    public String nameDirection()
    {
        switch(direction)
        {
            case 1: return "North";
            case 2: return "East";
            case 3: return "South";
            case 4: return "West";
            default: return "Up";
        }
    }
}
