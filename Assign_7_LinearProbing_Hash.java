package ADS;
import java.util.*;

class HashContents {
    // implementing the hash table to store the roll no and the name of the students
    // in department
    // 2 or more students may have same roll no. from different division
    int[] keys; // for roll no
    String[] values; // for name
    int size;

    HashContents(int maxSize) {
        keys = new int[maxSize];
        values = new String[maxSize];
        for (int i = 0; i < maxSize; i++) {
            keys[i] = -1;
            values[i] = null;
        }
        int size = 0;
    }

    public int hash(int key) {
        return key % keys.length;
    }

    public void insertPair(int key, String value) {
        if (size == keys.length)
            System.out.println("Hash Table is full\nNot able to insert the pair");

        int index = hash(key);

        while (keys[index] != -1) { // util the next empty space get
            index = (index + 1) % keys.length;
        }
        keys[index] = key;
        values[index] = value;
        size++;
        System.out.println("Pair get inserted successfully !!");
    }

    public void removePair(int key) {
        if (size == 0)
            System.out.println("Hash Table is empty\nCann't remove the pair");

        int index = hash(key);

        while (keys[index] != key && keys[index] != -1) {
            index = (index + 1) % keys.length;
        }
        if (keys[index] == key) {
            keys[index] = -1;
            values[index] = null;
            size--;

            System.out.println("Pair get deleted successfully !!");
        } else {
            System.out.println("Key not present cann't remove it");
        }
    }

    public void searchPair(int key) {
        if (size == 0)
            System.out.println("Empty Hash Table");

        int index = hash(key);

        while (keys[index] != key && keys[index] != -1) {
            index = (index + 1) % keys.length;
        }

        if (keys[index] == key)
            System.out.println("Roll No. " + key + " is present in the class.");
        else
            System.out.println("Roll No. " + key + " is absent.");
    }

    // printing hashTable
    public void printHashTable() {
        for (int i = 0; i < keys.length; i++) {
            System.out.println("Roll No. : " + keys[i] + " Name : " + values[i]);
        }
    }
}

class Assignment7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("\t***** Hash Table Implementation Using Linear Probing *****\t");
        System.out.println("\t--- Hash Table to store the roll no. and name of the student--- \t");
        boolean x = true;

        System.out.println("----------------------------------------------");
        System.out.print("First enter the size of the hash table: ");
        int size = sc.nextInt();
        System.out.println("----------------------------------------------");

        HashContents table1 = new HashContents(size);

        while (x) {
            System.out.println("\t *** Operations Available ***\t");
            System.out.println(
                    "1. Insert the pair.\n2. Remove the pair.\n3. Search the pair.\n4. Display Hash Table.\n5. Exit");
            System.out.println("----------------------------------------------");

            System.out.print("Enter your choice : ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the roll no. : ");
                    int roll = sc.nextInt();
                    System.out.println("Enter the name : ");
                    String name = sc.next();
                    table1.insertPair(roll, name);
                    System.out.println("----------------------------------------------");
                    break;

                case 2:
                    System.out.print("Enter the roll no. which you want to delete : ");
                    int roll1 = sc.nextInt();
                    table1.removePair(roll1);
                    System.out.println("----------------------------------------------");
                    break;

                case 3:
                    System.out.print("Enter the roll no. which you want to search : ");
                    int roll2 = sc.nextInt();
                    table1.searchPair(roll2);
                    System.out.println("----------------------------------------------");
                    break;

                case 4:
                    table1.printHashTable();
                    System.out.println("----------------------------------------------");
                    break;

                case 5:
                    x = false;
                    System.out.println("----------------------------------------------");
                    break;
            }
        }
    }
}

