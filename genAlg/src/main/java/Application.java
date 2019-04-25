import java.util.*;


public class Application
{
    static List<Candidate> population;
    static List<Integer> letters;
    static int populationSize = 10000;
    static int selectionCutoff = 5000;
    static String wordGoal = "antidisestablishmentarianism";
    static int wordLength = wordGoal.length();
    static int fitnessGoal = 0;
    static int generation = 0;

    // 65-90, 97-122

    public static void main(String[] args)
    {
        generateLetters();
        generatePopulation();
        generationLoop();
        System.out.println("Suitable candidate " + population.get(0).value + " selected in " + generation + " generations with a fitness score of " + population.get(0).fitness + ".");
    }

    public static void generateLetters()
    {
        letters = new ArrayList<>();
        for(int i = 65; i < 91; i++)
            letters.add(i);
        for(int i = 97; i < 123; i++)
            letters.add(i);
    }

    public static void generatePopulation()
    {
        population = new ArrayList<>();
        for(int i = 0; i < populationSize; i++)
        {
            StringBuilder s = new StringBuilder();
            Random rand = new Random();
            for(int j = 0; j < wordLength; j++)
            {
                int size = letters.size();
                int index = rand.nextInt(size);
                s.append((char)letters.get(index).intValue());
            }
            population.add(new Candidate(s.toString()));
        }
    }

    public static void computeFitness()
    {
        for(Candidate c : population)
        {
            int score = 0;
            for(int i = 0; i < wordLength; i++)
                 score += Math.abs(((int) wordGoal.toCharArray()[i]) - ((int) c.value.toCharArray()[i]));
            c.fitness = score;
        }
    }

    public static void computeReverseFitness()
    {
        for(Candidate c : population)
        {
            int score = 0;
            for(int i = 0; i < wordLength; i++)
                score += Math.abs((((int) c.value.toCharArray()[i]) - (int) wordGoal.toCharArray()[i]));
            c.fitness = score;
        }
    }

    public static void generationLoop()
    {
        GENERATION: while (true)
        {
            generation++;
//            computeFitness();
            computeReverseFitness();
            selection();
            mostMostCrossover();
            mutation();
            reseedPopulation();
            System.out.println("Generation " + generation + " - Top Candidate: " + population.get(0).value + " (Fitness Rating " + population.get(0).fitness +")" );
            if(population.get(0).fitness <= fitnessGoal)
                break;
        }
    }

    public static void selection()
    {
        Collections.sort(population);
    }

    public static void mostMostCrossover()
    {
        Random rand = new Random();
        /*
        Crosspoint needs to be after the first index and before the last index.
        wordLength 10:
            rand.nextInt(wordLength) = 0 - 9
            rand.nextInt(wordLength - 1) = 0 - 8
            rand.nextInt(wordLength - 1) + 1 = 1 - 9
            rand.nextInt(wordLength - 2) = 0 - 7
            rand.nextInt(wordLength - 2) + 1 = 1 - 8
         */

        /*
            Order of Operations:
                Select point of separation
                Get current and next word
                Split both words into substrings
                Build new words with separated substrings
                Replace words into list
         */

        for(int i = 0; i < selectionCutoff; i += 2)
        {
            int crossPoint = rand.nextInt(wordLength - 1) + 1;
            String w1 = population.get(i).value;
            String w2 = population.get(i + 1).value;
            StringBuilder n1 = new StringBuilder();
            StringBuilder n2 = new StringBuilder();
            n1.append(w1.substring(0, crossPoint));
            n1.append(w2.substring(crossPoint));
            n2.append(w2.substring(0, crossPoint));
            n2.append(w1.substring(crossPoint));
            population.get(i).value = n1.toString();
            population.get(i + 2).value = n2.toString();
        }
    }

    public static void mostLeastCrossover()
    {
        Random rand = new Random();
        for(int i = 0; i < selectionCutoff; i += 2)
        {
            int crossPoint = rand.nextInt(wordLength - 1) + 1;
            String w1 = population.get(i).value;
            String w2 = population.get(population.size() - 1 - i).value;
            StringBuilder n1 = new StringBuilder();
            StringBuilder n2 = new StringBuilder();
            n1.append(w1.substring(0, crossPoint));
            n1.append(w2.substring(crossPoint));
            n2.append(w2.substring(0, crossPoint));
            n2.append(w1.substring(crossPoint));
            population.get(i).value = n1.toString();
            population.get(population.size() - 1 - i).value = n2.toString();
        }
    }

    public static void mutation()
    {
    }

    public static void reseedPopulation()
    {
        List<Candidate> newGen = new ArrayList<>();
        for(int i = 0; i < population.size() / 2; i++)
            newGen.add(population.get(i));
        population = newGen;
        for(int i = 0; i < populationSize / 2; i++)
        {
            StringBuilder s = new StringBuilder();
            Random rand = new Random();
            for(int j = 0; j < wordLength; j++)
            {
                int size = letters.size();
                int index = rand.nextInt(size);
                s.append((char)letters.get(index).intValue());
            }
            population.add(new Candidate(s.toString()));
        }
    }



}



/*
START
Generate the initial population
Compute fitness
REPEAT
    Selection
    Crossover
    Mutation
    Compute fitness
UNTIL population has converged
STOP
 */