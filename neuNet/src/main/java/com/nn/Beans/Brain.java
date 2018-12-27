package com.nn.Beans;

import java.util.ArrayList;
import java.util.List;

public class Brain
{

    // Create a layer of input neurons
    static List<Neuron> inputLayer = new ArrayList<>();

    // Create a hidden layer of neurons
    static List<Neuron> hiddenLayer = new ArrayList<>();

    // Create a layer of output neurons
    static List<Neuron> outputLayer = new ArrayList<>();

    // Set number of inputs for the neurons in each layer
    static int inputLayerInputs = 8;
    static int hiddenLayerInputs = 4;
    static int outputLayerInputs = 2;

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

        throw new RuntimeException("Idea not found.");
    }

    /**
     * Create a list of Neurons to form the input layer
     */
    public static void generateInputLayer()
    {
        System.out.println("Creating Input Layer...");
    }

    /**
     * Create a list of Neurons to form the hidden layer
     */
    public static void generateHiddenLayer()
    {
        System.out.println("Creating Hidden Layer...");
    }

    /**
     * Create a list of Neurons to form the output layer
     */
    public static void generateOutputLayer()
    {
        System.out.println("Creating Output Layer...");
    }

    /**
     * Provide weights and inputs from [SOURCE]
     */
    public static void seedInputLayer()
    {
        System.out.println("Seeding Input Layer...");
    }

    /**
     * Take output from input layer and push to hidden layer
     */
    public static void seedHiddenLayer()
    {
        System.out.println("Moving to Hidden Layer...");
    }

    /**
     * Take output from hidden layer and push to output layer
     */
    public static void seedOutputLayer()
    {
        System.out.println("Moving to Output Layer...");
    }
}
