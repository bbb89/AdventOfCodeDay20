package com.lukasz;

import java.util.Comparator;

public class Particle implements Comparable<Particle> {
    private int id;
    private long[] position;
    private long[] velocity;
    private long[] acceleration;
    private long accelerationSum;
    private long velocitySum;
    private long distanceToZero;

    public Particle(int id, long[] position, long[] velocity, long[] acceleration) {
        this.id = id;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        accelerationSum = Math.abs(acceleration[0]) + Math.abs(acceleration[1]) + Math.abs(acceleration[2]);
        velocitySum = Math.abs(velocity[0]) + Math.abs(velocity[1]) + Math.abs(velocity[2]);
        distanceToZero = calculateDistance();

    }

    public long[] step() {
        for(int i = 0; i < velocity.length; i++) {
            velocity[i] += acceleration[i];
        }

        for(int i = 0; i < position.length; i++) {
            position[i] += velocity[i];
        }

        distanceToZero = calculateDistance();

        return position;
    }

    @Override
    public int compareTo(Particle o) {
        return Comparator.comparing( Particle::getAccelerationSum ).thenComparing( Particle::getVelocitySum ).compare(this, o);
    }

    private long calculateDistance() {
        return Math.abs(position[0]) + Math.abs(position[1]) + Math.abs(position[2]);
    }

    public long getDistanceToZero() {
        return distanceToZero;
    }

    public long getAccelerationSum() {
        return accelerationSum;
    }

    public long getVelocitySum() {
        return velocitySum;
    }

    public long[] getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }
}
