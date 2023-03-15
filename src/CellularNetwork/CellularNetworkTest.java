package CellularNetwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CellularNetworkTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        String[] cells = scanner.nextLine().split("\\s+");
        String[] cellIds = new String[cells.length];
        int[] capacities = new int[cells.length];
        for (int i = 0; i < cells.length; ++i) {
            String[] parts = cells[i].split(":");
            cellIds[i] = parts[0];
            capacities[i] = Integer.parseInt(parts[1]);
        }
        CellularNetwork cellularNetwork = new CellularNetwork(name, cellIds, capacities);
        int n = scanner.nextInt();
        scanner.nextLine();
        System.out.println("----- Making calls -----");
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            try {
                cellularNetwork.makeCall(parts[0], parts[1]);
            } catch (CellFullException e) {
                System.out.println(e.getMessage());
            }
        }
        n = scanner.nextInt();
        scanner.nextLine();
        System.out.println("----- Making handovers -----");
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            try {
                cellularNetwork.handover(parts[0], parts[1], parts[2]);
            } catch (CellFullException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("===== Find numbers =====");
        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String number = scanner.nextLine();
            cellularNetwork.findNumber(number);
        }
        System.out.println("===== Load =====");
        cellularNetwork.load();
        scanner.close();
    }
}

class CellFullException extends Exception {
    public CellFullException(String message) {
        super(message);
    }
}

class CellularNetwork {
    public String name;
    public String[] cellIds;
    public int[] capacities;

    public CellularNetwork(String name, String[] cellIds, int[] capacities) {
        this.name = name;
        this.cellIds = cellIds;
        this.capacities = capacities;
    }

    public void makeCall(String cellId, String number) throws CellFullException {
        // метод за креирање повик од даден број number во базна станица со дадено cellId.
        // Притоа ако е исполнет капацитетот за дадената базна станица да се фрли исклучок од тип
        // CellFullException со порака: CellFullException: {cell_id}.
        // Креирањето нов повик треба да биде со комлексност O(logN) за N вкупно базни станици.

        for (int i = 0; i < cellIds.length; i++) {
            cellIds[i] = cellId;
            capacities[i] = Integer.parseInt(number);
        }

        //throw new CellFullException(String.format("CellFullException: %s", cellId));

    }

    public void handover(String number, String fromCellId, String toCellId) throws CellFullException {

    }

    public void load() {
        //за печатење за информациите за секоја базна станица,
        //подредени лексикографски во однос на идентификаторот на базната станица.
        // За секоја базна станица се печти (пример):
        //
        //ID: {id}
        //***** 55.34%
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(Arrays.toString(cellIds)).append('\n');
    }

    public void findNumber(String number) {

    }
}