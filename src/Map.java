/*
https://carminati.altervista.org/PROJECTS/PYTHON3/PACMAN/pacman.html
    0	border, white block
    2	point, black block with normal point
   -2	normal black block
    3	special point, black block with big point
    4	door detail
    1	empty space black block

 */

import java.util.ArrayList;
import java.util.Arrays;

class Map {
    private byte[][] map;
    private int seed;
    private int cageCenterX;
    private int cageBottomY;


    public Map(int seed){
        this.seed = seed;
        if (this.seed == -1)
            ClassicMap();
    }

    public void ClassicMap(){
        map = new byte[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0},
                {0, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 0, 0, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 0},
                {0, 3, 1, 0, 0, 0, 2, 1, 0, 0, 0, 0, 2, 1, 0, 0, 2, 1, 0, 0, 0, 0, 2, 1, 0, 0, 0, 3, 1, 0},
                {0, 2, 1, 0, 0, 0, 2, 1, 0, 0, 0, 0, 2, 1, 0, 0, 2, 1, 0, 0, 0, 0, 2, 1, 0, 0, 0, 2, 1, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0},
                {0, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1, 0},
                {0, 2, 1, 0, 0, 0, 2, 1, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 2, 1, 0, 0, 0, 2, 1, 0},
                {0, 2, 2, 2, 2, 2, 2, 1, 0, 2, 2, 2, 2, 1, 0, 0, 2, 2, 2, 2, 1, 0, 2, 2, 2, 2, 2, 2, 1, 0},
                {0, 1, 1, 1, 1, 1, 2, 1, 0, 1, 1, 1, 2, 1, 0, 0, 2, 1, 1, 1, 1, 0, 2, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, -2, 1, 0, 0, -2, 1, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 0, 2, 1, 0, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, 1, 0, 2, 1, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 0, 2, 1, 0, -2, 1, 1, 1, 1, 1, 1, 1, 1, 1, -2, 1, 0, 2, 1, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 0, 2, 1, 0, -2, 1, 0, 0, 0, 4, 4, 0, 0, 0, -2, 1, 0, 2, 1, 0, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 2, 1, 0, -2, 1, 0, 1, 1, 1, 1, 1, 1, 0, -2, 1, 0, 2, 1, 0, 0, 0, 0, 0, 0},
                {-2, -2, -2, -2, -2, -2, 2, -2, -2, -2, 1, 0, 1, 1, 1, 1, 1, 1, 0, -2, -2, -2, 2, -2, -2, -2, -2, -2, -2, -2},
                {1, 1, 1, 1, 1, 1, 2, 1, 1, -2, 1, 0, 1, 1, 1, 1, 1, 1, 0, -2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 2, 1, 0, -2, 1, 0, 1, 1, 1, 1, 1, 1, 0, -2, 1, 0, 2, 1, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 0, 2, 1, 0, -2, 1, 0, 0, 0, 0, 0, 0, 0, 0, -2, 1, 0, 2, 1, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 0, 2, 1, 0, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, 1, 0, 2, 1, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 0, 2, 1, 0, -2, 1, 1, 1, 1, 1, 1, 1, 1, 1, -2, 1, 0, 2, 1, 0, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 2, 1, 0, -2, 1, 0, 0, 0, 0, 0, 0, 0, 0, -2, 1, 0, 2, 1, 0, 0, 0, 0, 0, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0},
                {0, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 0, 0, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 0},
                {0, 2, 1, 0, 0, 0, 2, 1, 0, 0, 0, 0, 2, 1, 0, 0, 2, 1, 0, 0, 0, 0, 2, 1, 0, 0, 0, 2, 1, 0},
                {0, 3, 2, 2, 1, 0, 2, 2, 2, 2, 2, 2, 2, -2, -2, -2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 2, 2, 3, 1, 0},
                {0, 1, 1, 2, 1, 0, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 0, 2, 1, 1, 1, 0},
                {0, 0, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 0, 0},
                {0, 2, 2, 2, 2, 2, 2, 1, 0, 2, 2, 2, 2, 1, 0, 0, 2, 2, 2, 2, 1, 0, 2, 2, 2, 2, 2, 2, 1, 0},
                {0, 2, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 2, 1, 0, 0, 2, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 2, 1, 0},
                {0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        cageCenterX = 14;
        cageBottomY = 15;
    }

    public byte[][] asByteArray(){
        return map;
    }

    public int getCageCenterX() {
        return cageCenterX;
    }

    public int getCageBottomY() {
        return cageBottomY;
    }

    public int getSeed() {
        return seed;
    }

    // returns a vector of which the collision is going to happen
    public ArrayList<Integer> wallCollisionBlocked(Entity entity) {
        ArrayList<Integer> blockedDirs = new ArrayList<Integer>();
        int block;
        int[] entityDir = entity.getDir();
        int entityX = entity.getX();
        int entityY = entity.getY();

        if (entityDir[0] == 1){
            // right
            block = map[entityY][entityX + 1];
            if (!(block == 2 || block == -2 || block == 3))
                blockedDirs.add(Entity.RIGHT);
        }
        if (entityDir[0] == -1){
            // left
            block = map[entityY][entityX];
            if (!(block == 2 || block == -2 || block == 3))
                blockedDirs.add(Entity.LEFT);
        }

        if (entityDir[1] == 1){
            // down
            block = map[entityY + 1][entityX];
            if (!(block == 2 || block == -2 || block == 3))
                blockedDirs.add(Entity.DOWN);
        }

        if (entityDir[1] == -1){
            // up
            block = map[entityY][entityX];
            if (!(block == 2 || block == -2 || block == 3))
                blockedDirs.add(Entity.UP);
        }

        return blockedDirs;
    }


    public ArrayList<Integer> atIntersectionAvailable(Entity entity) {
        ArrayList<Integer> blockedDirections = new ArrayList<>();
        int x = entity.getX();
        int y = entity.getY();
        int[] entityDir = entity.getDir();

        if (entityDir[0] == 1) {
            int block = map[y + 1][x];
            if (!(block == 0 || block == 1 || block == 4))
                blockedDirections.add(Entity.DOWN);

            block = map[y - 1][x];
            if (!(block == 0 || block == 1 || block == 4))
                blockedDirections.add(Entity.UP);
        } else if (entityDir[0] == -1) {
            int block = map[y + 1][x + 1];
            if (!(block == 0 || block == 1 || block == 4))
                blockedDirections.add(Entity.DOWN);

            block = map[y - 1][x + 1];
            if (!(block == 0 || block == 1 || block == 4))
                blockedDirections.add(Entity.UP);
        } else if (entityDir[1] == 1) {
            int block = map[y][x + 1];
            if (!(block == 0 || block == 1 || block == 4))
                blockedDirections.add(Entity.LEFT);

            block = map[y][x - 1];
            if (!(block == 0 || block == 1 || block == 4))
                blockedDirections.add(Entity.RIGHT);
        } else if (entityDir[1] == -1) {
            int block = map[y + 1][x + 1];
            if (!(block == 0 || block == 1 || block == 4))
                blockedDirections.add(Entity.LEFT);

            block = map[y + 1][x - 1];
            if (!(block == 0 || block == 1 || block == 4))
                blockedDirections.add(Entity.RIGHT);
        }

        return blockedDirections;
    }

    public boolean eatPoint(Pacman p, Ghost[] ghosts){
        // if pacman is on point (we need to decide how can we be on point as it is scaling related, contains related etc)
        if (map[p.getY()][p.getX()] == 2){
            map[p.getY()][p.getX()] = -2;
            p.setScore(p.getScore() + 1);
            return true;
        }
        if (map[p.getY()][p.getX()] == 3){
            for (Ghost g : ghosts)
                g.setGhostMode(Ghost.FRIGHTENED);

            new Thread(() -> {
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (Ghost g : ghosts)
                    g.setGhostMode(Ghost.CHASE);

            }).start();

            map[p.getY()][p.getX()] = -2;
            p.setScore(p.getScore() + 4);
            return true;
        }
        return false;
    }

    public void setMap(byte[][] bm) {
        this.map = bm;
    }

    public void removeCageDoors() {
        map[13][14] = -2;
        map[13][15] = -2;

    }

    public void addCageDoors(){
        map[13][14] = 4;
        map[13][15] = 4;
    }

    public int[] isAtPath(Entity entity){
        if (entity.getX() + 2 == map[0].length && entity.getY() == 15){
            return new int[]{0, 15};
        }

        if (entity.getXInPanel() == 0 && entity.getY() == 15){
            return new int[]{26,15};
        }
        return null;
    }

}