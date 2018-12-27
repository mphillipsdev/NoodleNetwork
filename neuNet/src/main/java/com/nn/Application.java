package com.nn;

import com.nn.Beans.Brain;

public class Application
{
    static Brain mind = new Brain();

    public static void main(String args[])
    {
        System.out.println("Thinking...");
        mind.think();
    }
}
