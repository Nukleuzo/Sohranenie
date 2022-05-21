package com.company;

import java.io.Serializable;

public class GameProgress implements Serializable {
        private static final long serialVersionUID = 1L;

        private String filename;
        private int health;
        private int weapons;
        private int lvl;
        private double distance;

    public String getName() {
        return filename;
    }

    public GameProgress(String filename, int health, int weapons, int lvl, double distance) {
            this.filename = filename;
            this.health = health;
            this.weapons = weapons;
            this.lvl = lvl;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "GameProgress{" +
                    "health=" + health +
                    ", weapons=" + weapons +
                    ", lvl=" + lvl +
                    ", distance=" + distance +
                    ", name =" + filename +
                    '}';
        }
    }

