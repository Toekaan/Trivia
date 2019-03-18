package com.example.trivia;

/** Randomizer class taken from:
 * https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java?page=1&tab=active#tab-top
 * Returns a random int somewhere between min and max**/
public class Randomizer
{
    public static int generate(int min,int max)
    {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}