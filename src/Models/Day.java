package Models;

import Models.Hall;

import java.util.HashMap;

public class Day {
    private final HashMap<Integer, int[][]> hallCheck = new HashMap<Integer, int[][]>();
    private final HashMap<Integer, Integer> hallAvailableCapacity = new HashMap<Integer, Integer>();

    /*setter*/

    public void setHallCheck(Hall hall) {
        int number = hall.getHallID();
        int length = hall.getLength();
        int width = hall.getWidth();
        int cap = hall.getHallSize();
        int[][] checkTb = new int[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                checkTb[i][j] = 1;
            }
        }

        this.hallCheck.put(number, checkTb);
        this.hallAvailableCapacity.put(number, cap);
    }

    public void changeHallCheck(int hallNum, int len, int wid) {
        if (hallCheck.get(hallNum)[len][wid] == 1) {
            hallCheck.get(hallNum)[len][wid] = 0;                                                                        //to buy
            int temp = this.hallAvailableCapacity.get(hallNum);
            temp--;
            this.hallAvailableCapacity.put(hallNum, temp);
        }
        else {
            hallCheck.get(hallNum)[len][wid] = 1;                                                                        //to return
            int temp = this.hallAvailableCapacity.get(hallNum);
            temp++;
            this.hallAvailableCapacity.put(hallNum, temp);
        }
    }

    /*getter*/

    public int getHallCheck(int hallNum, int len, int wid) {
        return hallCheck.get(hallNum)[len][wid];
    }

    public int getHallAvailableCapacity(int hallNum) {
        return hallAvailableCapacity.get(hallNum);
    }

    /**/
}
