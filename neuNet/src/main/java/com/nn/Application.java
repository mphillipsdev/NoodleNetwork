package com.nn;

import com.nn.Beans.Brain;
import com.nn.Beans.Neuron;

import java.util.ArrayList;
import java.util.List;

public class Application
{
    static Brain mind = new Brain();

    public static void main(String args[])
    {
        System.out.println("Thinking...");
        mind.think();
    }
}
