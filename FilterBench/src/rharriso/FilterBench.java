package rharriso;


import java.util.ArrayList;
import java.util.Random;

public class FilterBench {

    private static Random rand = new Random();
    static int appleCount;

    public static void main(String[] args) {
        try {
            int pow = Integer.parseInt(args[0]);
            appleCount = (int) Math.pow(2, pow);
        } catch(Exception e){
            System.out.println("Usage: \n");
            System.out.println("\tfilter [power]");
            System.out.println("\t\t power - number of items to filter, 2^[power]");
           return;
        }

        testObjects();
        testIntegers();
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
        long count1 = apples.stream()
                .sorted((Integer a, Integer b) -> a.compareTo(b))
                .filter(a -> a > 100)
                .count();
        long elapsed1 = System.nanoTime() - start1;

        System.out.printf("int, %d, %d, %d, %d, %d\n", appleCount, count0, elapsed0, elapsed1, elapsed0 - elapsed1);
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
        long count1 = apples.stream()
                .sorted((Apple a, Apple b) -> a.getWeight().compareTo(b.getWeight()))
                .filter(a -> a.getWeight() > 100)
                .count();
        long elapsed1 = System.nanoTime() - start1;

        System.out.printf("obj, %d, %d, %d, %d, %d\n", appleCount, count0, elapsed0, elapsed1, elapsed0 - elapsed1);
    }
}

class Apple {

    public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    private Integer weight;
}
