package rharriso;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

    private static Random rand = new Random();

    public static void main(String[] args) {
        System.out.println("started");

        double appleCount = Math.pow(2, 25);
        ArrayList<Apple> apples = new ArrayList<Apple>();
        for(int i = 0; i < appleCount; i++){
            Apple a = new Apple();
            a.setWeight(rand.nextInt(150) + 50);
            apples.add(a);
        }

        System.out.println("Constructed");
        System.out.println();

        System.out.println("Starting Parallel");
        long start0 = System.nanoTime();
        long count0 = apples.parallelStream().filter(a -> a.getWeight() > 100).count();
        long elapsed0 = System.nanoTime() - start0;
        System.out.println("Parallel Done");
        System.out.println();

        System.out.println("Starting Serial");
        long start1 = System.nanoTime();
        long count1 = apples.stream().filter(a -> a.getWeight() > 100).count();
        long elapsed1 = System.nanoTime() - start1;
        System.out.println("Serial Done");
        System.out.println();

        System.out.printf("Par %d \nSer %d\nDif %d\n", elapsed0, elapsed1, elapsed0 - elapsed1);
    }
}

class Apple {

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    private int weight;
}
