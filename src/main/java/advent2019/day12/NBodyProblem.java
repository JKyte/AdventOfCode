package advent2019.day12;

import java.util.ArrayList;

/**
 * Example 1: 179
 * Example 2: 1940
 * Part 1: 10189
 * Example 1, part 2: 2772
 * Example 2, part 2: 4686774924
 * Part 2:
 */
public class NBodyProblem {

    public static void main(String[] args) {
        NBodyProblem nBody = new NBodyProblem();
//        nBody.printMoons();
//        nBody.simulate(1000);
//        nBody.printMoons();
//        System.out.println(nBody.getTotalEnergy());

        //  Example 1, Part 2. Find period of each dimension, then find LCM of each.
        long x = nBody.findPeriod(0);
        long y = nBody.findPeriod(1);
        long z = nBody.findPeriod(2);
        System.out.println("X period: " + x);
        System.out.println("Y period: " + y);
        System.out.println("Z period: " + z);
        long lcm = nBody.lcm(x, y, z);
        System.out.println("lcm: " + lcm);
    }

    //  This needs to be pair-wise.
    //https://stackoverflow.com/questions/14586131/lcm-lowest-common-multiple-in-java
    private long lcm(long... xyz) {
        for (int m = 1; ; m++) {
            int n = xyz.length;
            for (long i : xyz) {
                if (m % i != 0) {
                    break;
                }
                if (--n == 0) {
                    return m;
                }
            }
        }
    }

    private ArrayList<Moon> moons = new ArrayList<>();

    public NBodyProblem() {
        //  Example 1
        //<x=-1, y=0, z=2>
        //<x=2, y=-10, z=-7>
        //<x=4, y=-8, z=8>
        //<x=3, y=5, z=-1>
//        Moon io = new Moon(-1, 0, 2);
//        Moon europa = new Moon(2, -10, -7);
//        Moon ganymede = new Moon(4, -8, 8);
//        Moon callisto = new Moon(3, 5, -1);

        //  Example 2
        //<x=-8, y=-10, z=0>
        //<x=5, y=5, z=10>
        //<x=2, y=-7, z=3>
        //<x=9, y=-8, z=-3>
        Moon io = new Moon(-8,-10,0);
        Moon europa = new Moon(5,5,10);
        Moon ganymede = new Moon(2,-7,3);
        Moon callisto = new Moon(9,-8,-3);

        //  Part 1
        //<x=14, y=15, z=-2>
        //<x=17, y=-3, z=4>
        //<x=6, y=12, z=-13>
        //<x=-2, y=10, z=-8>
//        Moon io = new Moon(14,15,-2);
//        Moon europa = new Moon(17,-3,4);
//        Moon ganymede = new Moon(6,12,-13);
//        Moon callisto = new Moon(-2,10,-8);

        moons.add(io);
        moons.add(europa);
        moons.add(ganymede);
        moons.add(callisto);
    }

    public void simulate(int turns) {
        for (int ii = 0; ii < turns; ii++) {
            simulateTurn();
        }
    }

    public void printMoons() {
        for (Moon moon : moons) {
            System.out.println(moon.toString());
        }
        System.out.println();
    }

    public void simulateTurn() {
        applyGravity();
        applyVelocity();
    }

    public void applyGravity() {
        for (int ii = 0; ii < moons.size(); ii++) {
            for (int jj = 0; jj < moons.size(); jj++) {
                if (jj == ii)
                    continue;
                applyGravity(moons.get(ii), moons.get(jj));
            }
        }
    }

    public void applyGravity(Moon moon, Moon other) {
        applyGravity(moon, other, 0);
        applyGravity(moon, other, 1);
        applyGravity(moon, other, 2);
    }

    private void applyGravity(Moon moon, Moon other, int index) {
        if (moon.a[index] > other.a[index]) {
            moon.a[index + 3]--;
        } else if (moon.a[index] < other.a[index]) {
            moon.a[index + 3]++;
        }
    }

    public void applyVelocity() {
        for (Moon moon : moons) {
            moon.applyVelocity(0);
            moon.applyVelocity(1);
            moon.applyVelocity(2);
        }
    }

    public int getTotalEnergy() {
        int total = 0;
        for (Moon moon : moons) {
            total += moon.totalEnergy();
        }
        return total;
    }

    public long findPeriod(int index) {
        int[] orig = new int[4];
        orig[0] = moons.get(0).a[index];
        orig[1] = moons.get(1).a[index];
        orig[2] = moons.get(2).a[index];
        orig[3] = moons.get(3).a[index];

        long period = 1;
        while (true) {

            //  Apply gravity
            for (int ii = 0; ii < moons.size(); ii++) {
                for (int jj = 0; jj < moons.size(); jj++) {
                    if (jj == ii)
                        continue;
                    applyGravity(moons.get(ii), moons.get(jj), index);
                }
            }

            for (Moon moon : moons) {
                moon.applyVelocity(index);
            }

            period++;
            if (isRepeat(orig, index))
                break;
//            if (period > 3000) {
//                System.out.println("Breaking because math'd wrongly.");
//                break;
//            }
        }
        return period;
    }

    public boolean isRepeat(int[] orig, int index) {
        for (int ii = 0; ii < moons.size(); ii++) {
            if (orig[ii] != moons.get(ii).a[index])
                return false;
        }
        return true;
    }

    private class Moon {

        // 0-2 are x,y,z position
        // 3-5 are x,y,z velocity
        int[] a;

        public Moon(int xPos, int yPos, int zPos) {
            this.a = new int[6];
            this.a[0] = xPos;
            this.a[1] = yPos;
            this.a[2] = zPos;
            this.a[3] = 0;
            this.a[4] = 0;
            this.a[5] = 0;
        }

        public void applyVelocity(int index) {
            a[index] += a[index + 3];
        }

        public int totalEnergy() {
            return potentialEnergy() * kineticEnergy();
        }

        public int potentialEnergy() {
            return Math.abs(a[0]) + Math.abs(a[1]) + Math.abs(a[2]);
        }

        public int kineticEnergy() {
            return Math.abs(a[3]) + Math.abs(a[4]) + Math.abs(a[5]);
        }

        public String toString() {
            String pos = "pos=<x= " + a[0] + ", y= " + a[1] + ", z= " + a[2] + ">,";
            String vel = "vel=<x= " + a[3] + ", y= " + a[4] + ", z= " + a[5] + ">";
            return pos + " " + vel;
        }
    }
}
