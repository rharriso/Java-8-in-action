package rharriso;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

    private static Random rand = new Random();
    static double appleCount;

    public static void main(String[] args) {
        int pow = Integer.parseInt(args[0]);
        appleCount = Math.pow(2, pow);
        System.out.printf("starting with set size (%d)\n", (int) appleCount);
        testObjects();
        testIntegers();
        System.out.printf("finished with set size (%d)\n", (int) appleCount);
    }

    static void testIntegers() {
        ArrayList<Integer> apples = new ArrayList<Integer>();
        for(int i = 0; i < appleCount; i++){
            apples.add(rand.nextInt(200));
        }

        long start0 = System.nanoTime();
        long count0 = apples.parallelStream().filter(a -> a > 100).count();
        long elapsed0 = System.nanoTime() - start0;

        long start1 = System.nanoTime();
        long count1 = apples.stream().filter(a -> a > 100).count();
        long elapsed1 = System.nanoTime() - start1;

        System.out.printf("Par-Int: %d \nSer-Int: %d\nDif-Int: %d\n", elapsed0, elapsed1, elapsed0 - elapsed1);
    }

    static void testObjects(){
        ArrayList<Apple> apples = new ArrayList<Apple>();
        for(int i = 0; i < appleCount; i++){
            Apple a = new Apple();
            a.setWeight(rand.nextInt(150) + 50);
            apples.add(a);
        }

        long start0 = System.nanoTime();
        long count0 = apples.parallelStream().filter(a -> a.getWeight() > 100).count();
        long elapsed0 = System.nanoTime() - start0;

        long start1 = System.nanoTime();
        long count1 = apples.stream().filter(a -> a.getWeight() > 100).count();
        long elapsed1 = System.nanoTime() - start1;

        System.out.printf("Par-Obj %d \nSer-Obj %d\nDif-Obj %d\n", elapsed0, elapsed1, elapsed0 - elapsed1);
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
