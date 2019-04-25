public class Candidate implements Comparable<Candidate>
{
    String value = "";
    int fitness = 0;

    public  Candidate(String value)
    {
        this.value = value;
    }

    @Override
    public int compareTo(Candidate o)
    {
        if(this.fitness < o.fitness) return -1;
        if(this.fitness == o.fitness) return 0;
        return 1;
    }

    @Override
    public String toString()
    {
        return "Word: " + this.value + " Score: " + this.fitness;
    }
}
