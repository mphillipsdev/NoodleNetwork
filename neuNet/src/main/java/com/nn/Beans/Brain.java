package com.nn.Beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Brain
{

    // Create a layer of input neurons
    static List<Neuron> inputLayer = new ArrayList<>();

    // Create a hidden layer of neurons
    static List<Neuron> hiddenLayer = new ArrayList<>();

    // Create a layer of output neurons
    static List<Neuron> outputLayer = new ArrayList<>();

    // Create the final neuron
    static Neuron finalNeuron = new Neuron();

    // Set number of neurons per layer
    static int numInputNeurons = 8;
    static int numHiddenNeurons = 4;
    static int numOutputNeurons = 2;

    // Set number of inputs for the neurons in each layer
    static int inputLayerInputs = 1;
    static int hiddenLayerInputs = 8;
    static int outputLayerInputs = 4;

    // Test variables
    static double testWt = 0.5;

    // RNG for input data
    static Random rand = new Random();

    /**
     * Take an input, process through the layers, and provide an output.
     */
    public static void think()
    {
        generateInputLayer();
        generateHiddenLayer();
        generateOutputLayer();
        seedInputLayer();
        seedHiddenLayer();
        seedOutputLayer();
        seedFinalOutput();

        System.out.println("Final output is: " + finalNeuron.renderOutput());
        System.out.println("... for all the good it does you.");
    }

    /**
     * Create a list of Neurons to form the input layer
     */
    public static void generateInputLayer()
    {
        System.out.println("Creating Input Layer...");

        // For each neuron in the layer
        for(int i = 0; i < numInputNeurons; i++)
        {
            // Create a neuron
            Neuron n = new Neuron();
            // Add the given number of weights and inputs
            for(int j = 0; j < inputLayerInputs; j++)
            {
                n.addInput(rand.nextDouble());
                n.addWeight(testWt);
            }
            inputLayer.add(n);
        }
    }

    /**
     * Create a list of Neurons to form the hidden layer
     */
    public static void generateHiddenLayer()
    {
        System.out.println("Creating Hidden Layer...");
        // For each neuron in the layer
        for(int i = 0; i < numHiddenNeurons; i++)
        {
            // Create a neuron
            Neuron n = new Neuron();
            // Add the given number of weights
            for(int j = 0; j < hiddenLayerInputs; j++)
                n.addWeight(rand.nextDouble());
            hiddenLayer.add(n);
        }
    }

    /**
     * Create a list of Neurons to form the output layer
     */
    public static void generateOutputLayer()
    {
        System.out.println("Creating Output Layer...");
        // For each neuron in the layer
        for(int i = 0; i < numOutputNeurons; i++)
        {
            // Create a neuron
            Neuron n = new Neuron();
            // Add the given number of weights
            for(int j = 0; j < outputLayerInputs; j++)
                n.addWeight(rand.nextDouble());
            outputLayer.add(n);
        }
    }

    /**
     * Provide weights and inputs from [SOURCE]
     */
    public static void seedInputLayer()
    {
        System.out.println("Processing Input Layer...");
        System.out.print("Inputs: | ");
        for(Neuron n : inputLayer)
             System.out.print(n.getInput(0) + " | ");
        System.out.println(" ");
    }

    /**
     * Take output from input layer and push to hidden layer
     */
    public static void seedHiddenLayer()
    {
        System.out.println("Moving to Hidden Layer...");
        // For each neuron in the hidden layer
        for(Neuron n : hiddenLayer)
            // Add all of the outputs of the input layer
            for(Neuron i : inputLayer)
                n.addInput(i.renderOutput());

        for(Neuron n : hiddenLayer)
            System.out.print(n.getInput(0) + " | ");
        System.out.println(" ");
    }

    /**
     * Take output from hidden layer and push to output layer
     */
    public static void seedOutputLayer()
    {
        System.out.println("Moving to Output Layer...");
        // For each neuron in the output layer
        for(Neuron n : outputLayer)
            // Add all of the outputs of the input layer
            for(Neuron h : hiddenLayer)
                n.addInput(h.renderOutput());

        for(Neuron n : outputLayer)
            System.out.print(n.getInput(0) + " | ");
        System.out.println(" ");
    }

    public static void seedFinalOutput()
    {
        for(Neuron n : outputLayer)
        {
            finalNeuron.addInput(n.renderOutput());
            finalNeuron.addWeight(rand.nextDouble());
        }
    }
}

/*
Theory:

1 INPUT - O -|       | - 8 INPUTS - O --|
1 INPUT - O -|       |                  |
1 INPUT - O -|       | - 8 INPUTS - O --|       | - 4 INPUTS - O ---|
1 INPUT - O -|       |                  |       |                   |
             |-------|                  |-------|                   | - 2 INPUTS - O -> OUTPUT
1 INPUT - O -|       |                  |       |                   |
1 INPUT - O -|       | - 8 INPUTS - O --|       | - 4 INPUTS - O ---|
1 INPUT - O -|       |                  |
1 INPUT - O -|       | - 8 INPUTS - O --|

[INPUT LAYER]           [HIDDEN LAYER]              [OUTPUT LAYER]              [FINAL]





 */