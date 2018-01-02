package com.lukasz;


import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    List<Particle> particles;
    List<Particle> particlesAfterRemoving;
    int partI;
    int partII;

    public Solution(List<Particle> particles) {
        this.particles = particles;
        this.particlesAfterRemoving = new ArrayList<>(particles);

        removeCollided();

        for(int i = 0; i < 10000; i++) {
            for(Particle p : particlesAfterRemoving) {
                p.step();
            }
            removeCollided();
        }

        partII = particlesAfterRemoving.size();

        Collections.sort(particles);
        partI = particles.get(0).getId();
    }

    private void removeCollided() {
        Map<Point, List<Particle>> positions = new HashMap<>();
        List<Particle> newParticles = new ArrayList<>();

        positions = particlesAfterRemoving.stream()
                .collect(Collectors.groupingBy( particle -> new Point(particle.getPosition()) ));

        for(Point point : positions.keySet()) {
            if (positions.get(point).size() == 1) {
                newParticles.add(positions.get(point).get(0));
            }
        }

        particlesAfterRemoving = newParticles;
    }

    public int getPartI() {
        return partI;
    }

    public int getPartII() {
        return partII;
    }

    class Point {
        private long[] position;

        public Point(long[] position) {
            this.position = position;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(position);
        }

        @Override
        public boolean equals(Object obj) {
            return position[0] == ((Point) obj).getX() &&
                    position[1] == ((Point) obj).getY() &&
                    position[2] == ((Point) obj).getZ();
        }

        public long getX() {
            return position[0];
        }

        public long getY() {
            return position[1];
        }

        public long getZ() {
            return position[2];
        }
    }

}
