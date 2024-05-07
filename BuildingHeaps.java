package ADS;
import java.util.*;
class BuildingHeaps {
    public void buildMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = (n - 1) / 2; i >= 0; i--) {
            maxHeapify(arr, n, i);
        }
    }

    public void maxHeapify(int[] arr, int size, int index) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < size && arr[largest] < arr[left])
            largest = left;

        if (right < size && arr[largest] < arr[right])
            largest = right;

        if (largest != index) {
            int temp = arr[largest];
            arr[largest] = arr[index];
            arr[index] = temp;
            maxHeapify(arr, size, largest);
        }
    }

    public void buildMinHeap(int[] arr) {
        int n = arr.length;
        for (int i = (n - 1) / 2; i >= 0; i--) {
            minHeapify(arr, n, i);
        }
    }

    public void minHeapify(int[] arr, int size, int index) {
        int smallest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < size && arr[smallest] > arr[left])
            smallest = left;

        if (right < size && arr[smallest] > arr[right])
            smallest = right;

        if (smallest != index) {
            int temp = arr[smallest];
            arr[smallest] = arr[index];
            arr[index] = temp;
            maxHeapify(arr, size, smallest);
        }
    }

    public int getMax(int[] arr) {
        buildMaxHeap(arr);
        return arr[0];
    }

    public int getMin(int[] arr) {
        buildMinHeap(arr);
        return arr[0];
    }
}

class Assignment6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BuildingHeaps heap1 = new BuildingHeaps();

        System.out.println("\t*** Subjects in second year ***\t");
        String[] subjects = { "Fundamentals of Data structures (FDS)", "Discrete Mathematics (DM)",
                "Computer Architecture & Organization (CAO)", "Analog and Digital Electronics (ADE)",
                "Universal Human Values (UHV)", "Advanced Data Structure (ADS)", "Theory of Computation (TOC)",
                "Database Managment Systenm (DBMS)", "Probability & Statistics (PS)", "Operating System (OS)" };
        String[] shortCuts = { "FDS", "DM", "CAO", "ADE", "UHV", "ADS", "TOC", "DBMS", "PS", "OS" };
        System.out.println("----------------------------------------");
        for (int i = 0; i < 10; i++) {
            System.out.println((i + 1) + ". " + subjects[i]);
        }
        System.out.println("----------------------------------------");

        System.out.println("Enter the marks of respective subject (out of 100): ");
        int[] marks = new int[10];
        int[] temp = new int[10];
        for (int i = 0; i < 10; i++) {
            System.out.print(shortCuts[i] + " : ");
            marks[i] = sc.nextInt();
            temp[i] = marks[i];
        }

        System.out.println("----------------------------------------");
        int maxMark = heap1.getMax(temp);
        int minMark = heap1.getMin(temp);

        for (int i = 0; i < 10; i++) {
            if (marks[i] == maxMark) {
                System.out.println("Congrats !!\nYour maximum score is " + maxMark + " / 100 in " + shortCuts[i]);
            }
        }

        System.out.println("&");
        for (int i = 0; i < 10; i++) {
            if (marks[i] == minMark) {
                System.out
                        .println("Your minimum score is " + minMark + " / 100 \nNeeded improvement in " + shortCuts[i]);
            }
        }
        System.out.println("----------------------------------------");
    }
}

