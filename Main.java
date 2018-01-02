package com.lukasz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Particle> getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter input: ");
        String line;
        List<String> lines = new ArrayList<>();

        while(!(line = scanner.nextLine()).isEmpty()) {
            lines.add(line);
        }

        return parseParticles(lines);
    }

    private static List<Particle> parseParticles(List<String> lines) {

        List<Particle> particles = new ArrayList<>();

        for(int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] pnumbers = line.substring(line.indexOf("<") + 1, line.indexOf(">")).split(",");
            line = line.substring(line.indexOf("v="));
            String[] vnumbers = line.substring(line.indexOf("<") + 1, line.indexOf(">")).split(",");
            line = line.substring(line.indexOf("a="));
            String[] anumbers = line.substring(line.indexOf("<") + 1, line.indexOf(">")).split(",");

            long[] position = convertToIntArray(pnumbers);
            long[] velocity = convertToIntArray(vnumbers);
            long[] acceleration = convertToIntArray(anumbers);

            Particle particle = new Particle(i, position, velocity, acceleration);
            particles.add(particle);
        }

        return particles;
    }

    private static long[] convertToIntArray(String[] numbers) {
        return Arrays.stream(numbers).mapToLong( Long::parseLong ).toArray();
    }



    public static void main(String[] args) {
        List<Particle> particles = getInput();
        Solution solution = new Solution(particles);
        System.out.println("In long term particle that will stay closest to 0,0,0 is " + solution.getPartI());
        System.out.println("There are " + solution.getPartII() + " particles after collisions.");
    }
}
