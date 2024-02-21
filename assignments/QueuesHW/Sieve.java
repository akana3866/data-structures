package QueuesHW;

import java.util.Scanner;

public class Sieve {
    private LinkedQueue<Integer> numbers;
    private LinkedQueue<Integer> primes;
    
    public Sieve() { //constructor
        numbers = new LinkedQueue<Integer>();
        primes = new LinkedQueue<Integer>();
    }
    
    public void primesTo(int val) throws IllegalArgumentException { //primes method
        if (val < 2) {
            throw new IllegalArgumentException("Input must be a number greater than 2."); //2 and 1 are already primes
        }
        
        for (int i = 2; i <= val; i++) { //adds the values from 2-the value inputed (20) into the queue
            numbers.enqueue(i);
        }
        
        int p = numbers.dequeue(); 
        
        while (p <= Math.sqrt(val)) { 
            primes.enqueue(p);
            
            LinkedQueue<Integer> tmp = new LinkedQueue<Integer>();
            
            while (!numbers.isEmpty()) {
                int number = numbers.dequeue();
                
                if (number % p != 0) {
                    tmp.enqueue(number);
                }
            }
            
            numbers = tmp;
            p = numbers.first();
        }
        
        while (!numbers.isEmpty()) {
            primes.enqueue(numbers.dequeue());
        }
        
        System.out.print("Primes up to " + val + " are: ");
        
        while (!primes.isEmpty()) {
            System.out.print(primes.dequeue());
            
            if (!primes.isEmpty()) {
                System.out.print(", ");
            }
        }
        
        System.out.println();
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter upper bound: ");
        int user_num = input.nextInt();
        
        Sieve sieve = new Sieve();
        
        try {
            sieve.primesTo(user_num);
        } catch (IllegalArgumentException err) {
            System.out.println("Error: " + err.getMessage());
        }
    }
}
