package com.nn;

import com.nn.Beans.OldNeuron;
import com.nn.Beans.Tank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Drive
{

    public static Random rand = new Random();
    public static List<OldNeuron> inputNeurons = new ArrayList<OldNeuron>();
    public static List<List<OldNeuron>> hiddenLayers = new ArrayList<List<OldNeuron>>();
    public static OldNeuron outputNeuron = new OldNeuron();
    public static int inputsPerNeuron = 256;

    public static double threshold = 0.424725;
    public static Tank t = new Tank();
    // [^] [>] [v] [<]

    public static void main(String[] args) {
        long start = System.nanoTime();
        t.setDirectionals();
        for(int i = 0; i < 1000000; i ++)
            tankTurn(i);
        System.out.println((System.nanoTime() - start) / 1000000000.0);
    }

    public static void number()
    {
        double smallest = Double.MAX_VALUE;
        double largest = Double.MIN_VALUE;
        double avg = 0;
        int numPos = 0;
        for (int i = 0; i < 100000; i++) {
            long start = System.nanoTime();
            int numNodes = inputsPerNeuron;// / 2;
            //rand = new Random();
            for (int j = 0; j < numNodes; j++)
                inputNeurons.add(populateInputNeuron(new OldNeuron(), inputsPerNeuron));
            for (OldNeuron n : inputNeurons)
                n.processInput();

            numNodes /= 2;
            List<OldNeuron> hl = new ArrayList<OldNeuron>();
            for(int j = 0; j < numNodes; j++)
                hl.add(populateHiddenNeuron(new OldNeuron(), inputNeurons));
            hiddenLayers.add(hl);

            while(numNodes > 2)
            {
                for(OldNeuron n : hiddenLayers.get(hiddenLayers.size() - 1))
                    n.processInput();
                numNodes /= 2;
                hl = new ArrayList<OldNeuron>();
                for(int j = 0; j < numNodes; j++)
                    hl.add(populateHiddenNeuron(new OldNeuron(), hiddenLayers.get(hiddenLayers.size() - 1)));
                hiddenLayers.add(hl);
                for(int j = 0; j < numNodes; j++)
                    hiddenLayers.get(hiddenLayers.size() - 1).get(j).processInput();
            }

            outputNeuron = populateHiddenNeuron(new OldNeuron(), hiddenLayers.get(hiddenLayers.size() - 1));
            outputNeuron.processInput();
            double fin = outputNeuron.renderOutput();
            if (fin > largest)
                largest = fin;
            if (fin < smallest)
                smallest = fin;
            if(fin != 0)
                numPos++;
            if (i % 1000 == 0)
            {
                long time = (System.nanoTime() - start) / 1000000;
                avg += numPos;
                double percentile = ((avg/i)*100);
                System.out.println("Output Range: " + smallest + " to " + largest + " with " + String.format("%.2f", percentile) + "% positive results. (Threshold " + threshold + ")");
                System.out.println("1000 iterations (of " + i + ") with " + (hiddenLayers.size() + 1) + " layers in " + time + " seconds.");
                //threshold = rand.nextDouble();
                //avg = 0;
                numPos = 0;
                smallest = Double.MAX_VALUE;
                largest = Double.MIN_VALUE;
            }

            inputNeurons.clear();
            for(List<OldNeuron> ls : hiddenLayers)
                ls.clear();
            hiddenLayers.clear();
        }
        // System.out.println("Output Range: " + smallest + " to " + largest);
    }

    public static void tankTurn(int i)
    {
        // input layer
        OldNeuron canLeft = new OldNeuron();
        OldNeuron canRight = new OldNeuron();
        OldNeuron canForward = new OldNeuron();

        // output layer
        OldNeuron leftTread = new OldNeuron();
        OldNeuron rightTread = new OldNeuron();

        // Weights for input and hidden neurons
//    	double fwdWeight = 0.75;
//    	double lfWeight = 0.25;
//    	double rtWeight = 0.25;


        // Weights and inputs added in FORWARD, LEFT, RIGHT order
        canForward.addInput(t.canGoForward ? 1 : 0);
        canForward.addInput(t.canGoLeft ? 1 : 0);
        canForward.addInput(t.canGoRight ? 1 : 0);
        canForward.addWeight(rand.nextDouble());
        canForward.addWeight(rand.nextDouble());
        canForward.addWeight(rand.nextDouble());

        canLeft.addInput(t.canGoForward ? 1 : 0);
        canLeft.addInput(t.canGoLeft ? 1 : 0);
        canLeft.addInput(t.canGoRight ? 1 : 0);
        canLeft.addWeight(rand.nextDouble());
        canLeft.addWeight(rand.nextDouble());
        canLeft.addWeight(rand.nextDouble());

        canRight.addInput(t.canGoForward ? 1 : 0);
        canRight.addInput(t.canGoLeft ? 1 : 0);
        canRight.addInput(t.canGoRight ? 1 : 0);
        canRight.addWeight(rand.nextDouble());
        canRight.addWeight(rand.nextDouble());
        canRight.addWeight(rand.nextDouble());

        canForward.processInput();
        canLeft.processInput();
        canRight.processInput();


        leftTread.addInput(canForward.renderOutput());
        leftTread.addInput(canLeft.renderOutput());
        leftTread.addInput(canRight.renderOutput());
        leftTread.addWeight(rand.nextDouble());
        leftTread.addWeight(rand.nextDouble());
        leftTread.addWeight(rand.nextDouble());
        rightTread.addInput(canForward.renderOutput());
        rightTread.addInput(canLeft.renderOutput());
        rightTread.addInput(canRight.renderOutput());
        rightTread.addWeight(rand.nextDouble());
        rightTread.addWeight(rand.nextDouble());
        rightTread.addWeight(rand.nextDouble());

        leftTread.processInput();
        rightTread.processInput();

        double rt = rightTread.renderOutput();
        double lt = leftTread.renderOutput();

        if(rt >= threshold && lt < threshold)
            t.turnRight(i);

        if(lt >= threshold && rt < threshold)
            t.turnLeft(i);

        if(rt >= threshold && lt >= threshold)
        {
            t.moveForward(i);
            System.out.println("X: " + t.xLoc + " | Y: " + t.yLoc);
        }

        t.setDirectionals();

    }

    public static OldNeuron populateInputNeuron(OldNeuron n, int numInputs) {
        for (int i = 0; i < numInputs; i++) {
            n.addInput(rand.nextDouble());
            n.addWeight(rand.nextDouble());
        }
        n.setBias(1);
        n.setThreshold(threshold);
        return n;
    }

    public static OldNeuron populateHiddenNeuron(OldNeuron n, List<OldNeuron> input) {
        for (int i = 0; i < input.size(); i++) {
            n.addInput(input.get(i).renderOutput());
            n.addWeight(rand.nextDouble());
        }
        n.setBias(1);
        n.setThreshold(threshold);

        return n;
    }

    public static int getDist(int x1, int y1, int x2, int y2)
    {
        return (int)Math.abs(Math.round(Math.sqrt(Math.pow((x1 - x2),2) + Math.pow((y1-y2),2))));
    }
}
