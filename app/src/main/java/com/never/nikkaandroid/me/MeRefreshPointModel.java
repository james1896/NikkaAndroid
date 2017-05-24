package com.never.nikkaandroid.me;

/**
 * Created by toby on 24/05/2017.
 */

public class MeRefreshPointModel {
    public float points;

    @Override
    public String toString() {
        return "MeRefreshPointModel{" +
                "points=" + points +
                '}';
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }
}
