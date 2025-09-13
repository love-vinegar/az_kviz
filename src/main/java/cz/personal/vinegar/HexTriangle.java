package cz.personal.vinegar;

import java.util.*;

public class HexTriangle {
    private final int size; // number of rows
    private final int[][] values; // stores 0, 1, 2 values

    public HexTriangle(int size) {
        this.size = size;
        this.values = new int[size][];
        for (int r = 0; r < size; r++) {
            values[r] = new int[r + 1];
            for (int c = 0; c < r + 1; c++) {
                values[r][c] = 0;
            }
        }
    }

    public void setValue(int row, int col, int val) {
        values[row][col] = val;
    }

    public int getValue(int row, int col) {
        return values[row][col];
    }

    private List<int[]> getNeighbors(int row, int col) {
        List<int[]> neighbors = new ArrayList<>();

        if (row > 0 && col > 0) neighbors.add(new int[]{row - 1, col - 1});
        if (row > 0 && col < row) neighbors.add(new int[]{row - 1, col});

        if (row < size - 1) neighbors.add(new int[]{row + 1, col});
        if (row < size - 1) neighbors.add(new int[]{row + 1, col + 1});

        if (col > 0) neighbors.add(new int[]{row, col - 1});
        if (col < row) neighbors.add(new int[]{row, col + 1});

        return neighbors;
    }

    public boolean hasConnectingPath(int targetValue) {
        int n = size;

        boolean[][] visited = new boolean[n][n];

        for (int r = 0; r < n; r++) {
            for (int c = 0; c <= r; c++) {
                if (!visited[r][c] && values[r][c] == targetValue) {
                    Set<String> borders = new HashSet<>();
                    List<int[]> stack = new ArrayList<>();
                    stack.add(new int[]{r, c});
                    visited[r][c] = true;

                    while (!stack.isEmpty()) {
                        int[] cell = stack.remove(stack.size() - 1);
                        int cr = cell[0], cc = cell[1];

                        if (cc == 0) borders.add("LEFT");
                        if (cc == cr) borders.add("RIGHT");
                        if (cr == n - 1) borders.add("BOTTOM");

                        for (int[] nb : getNeighbors(cr, cc)) {
                            int nr = nb[0], nc = nb[1];
                            if (!visited[nr][nc] && values[nr][nc] == targetValue) {
                                visited[nr][nc] = true;
                                stack.add(new int[]{nr, nc});
                            }
                        }
                    }

                    if (borders.size() == 3) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static int[] indexToRowCol(int index) {
        int row = 0;

        // Find row where index belongs
        while (index >= triangular(row + 1)) {
            row++;
        }

        int col = index - triangular(row);
        return new int[]{row, col};
    }

    // Helper: triangular number T_r = r*(r+1)/2
    private static int triangular(int r) {
        return r * (r + 1) / 2;
    }

    // Demo
    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            int[] rc = indexToRowCol(i);
            System.out.printf("Index %d -> row=%d, col=%d%n", i, rc[0], rc[1]);
        }
    }
}
