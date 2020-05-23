//Patrick Garry
//Initially Written for: Java CSC 222 - 11/11/19 - CSP
//Edited for: CSC 422 - 5/18/20
//
//PetDatabase Release Project
//
package Package;

import java.util.Scanner;

public class PetDatabase
{
//globals

    static Pet[] pets = new Pet[100];
    static Scanner s = new Scanner(System.in);
    static int petCount = 0;

    public static void main(String[] args)
    {
        System.out.println("Pet Database Program.");
        Boolean running = true;
        while (running == true)
        {
            switch (getUserChoice())
            {
                case 1:
                    showAllPets();
                    break;
                case 2:
                    addPets();
                    break;
                /*
                case 3:
                    updatePet();
                    break;
                case 4:
                    removePet();
                    break;
                 */
                case 5:
                    searchPetsByName();
                    break;
                case 6:
                    searchPetsByAge();
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Input. Reenter:\n");
                    break;
            }
        }
    }

    private static int getUserChoice()
    {
        System.out.println();
//User options
        System.out.println("What would you like to do?\n");
        System.out.println("1) View all pets");
        System.out.println("2) Add more pets ");
        System.out.println("3) Update an existing pet ");
        System.out.println("4) Remove an existing pet ");
        System.out.println("5) Search pets by name ");
        System.out.println("6) Search pets by age ");
        System.out.println("7) Exit program\n");
//User choice
        System.out.print("Your choice: ");
        int choice = s.nextInt();
        System.out.println();

        return choice;
    }

    private static void addPets()
    {
        Boolean running = true;
        s.nextLine();

        for (int i = 0; i < 100; i++)
        {
            while (running == true)
            {
                System.out.print("add pet (name, age): ");
                String petTempInfo = s.nextLine().trim();                       //what if input is empty of " "
                if ("done".equals(petTempInfo))
                {
                    running = false;
                    break;
                }
//extract name
                String nameTemp = petTempInfo.substring(0, petTempInfo.indexOf(' '));
//extract age
                String ageTemp = petTempInfo.substring(petTempInfo.indexOf(' ') + 1);
//convert age String to Int
                int ageInt = Integer.parseInt(ageTemp);
//add current pet object into arrPets
                pets[petCount] = new Pet(nameTemp, ageInt);
//add to running counter
                petCount++;
            }
        }
    }

    private static void showAllPets()
    {
        printTableHeader();
        for (int i = 0; i < petCount; i++)
        {
            printTableRow(i, pets[i].getName(), pets[i].getAge());
        }
        printTableFooter(petCount);
    }

    /*
    private static void updatePet()
    {
        String originalName;
        int originalAge;
        showAllPets();
        System.out.println();
        System.out.print("Enter the pet ID you want to update: ");
//user input
        int updateChoice = s.nextInt();
        System.out.print("Enter new name and new age: ");
//user input
        s.nextLine();
        String updatePetTempInfo = s.nextLine().trim();
        String nameTemp = updatePetTempInfo.substring(0, updatePetTempInfo.indexOf(' '));
//extract age
        String ageTemp = updatePetTempInfo.substring(updatePetTempInfo.indexOf(' ') + 1);
//convert age String to Int
        int ageInt = Integer.parseInt(ageTemp);
//save original name and age to show what was changed
        originalName = pets[updateChoice].getName();
        originalAge = pets[updateChoice].getAge();
//add current pet object into arrPets
        pets[updateChoice].setName(nameTemp);
        pets[updateChoice].setAge(ageInt);
//print out what was changed
        System.out.println(originalName + " " + originalAge + " changed to " + nameTemp + " " + ageTemp);
    }

    private static void removePet()
    {
        showAllPets();
        System.out.println();
        System.out.print("Enter the pet ID to remove: ");
//user input
        int removeChoice = s.nextInt();
//print to user which pet was removed
        System.out.println(pets[removeChoice].getName() + " " + pets[removeChoice].getAge() + " is removed.");
//copy later items in array up one index
        for (; removeChoice < petCount - 1; removeChoice++)
        {
            pets[removeChoice].setName(pets[removeChoice + 1].getName());       //-->PROBLEM IF DELETING LAST ITEM IN ARRAY
            pets[removeChoice].setAge(pets[removeChoice + 1].getAge());         //-->NOTHING TO COPY OVER LAST ITEM
//removeChoice incremented to iterate through all pets
        }
        petCount--;
    }
    */
    
    private static void searchPetsByName()
    {
        int nameSearchPetCount = 0;
        System.out.print("Enter a name to search: ");
//user input
        s.nextLine();
        String searchChoice = s.nextLine().trim();
//print header
        printTableHeader();
//iterate through pets array to find where user input equals pet name
//print row for each true
        for (int i = 0; i < petCount; i++)
        {
            if (searchChoice.equals(pets[i].getName()))
            {
                printTableRow(i, pets[i].getName(), pets[i].getAge());
                nameSearchPetCount++;
            }
        }
//print table footer with nameSearchPetCount parameter
        printTableFooter(nameSearchPetCount);
    }

    private static void searchPetsByAge()
    {
        int ageSearchPetCount = 0;
        System.out.print("Enter an age to search: ");
//user input
        int ageSearch = s.nextInt();
//print header
        printTableHeader();
//iterate through pets array to find where user input equals pet age
//print row for each true
        for (int i = 0; i < petCount; i++)
        {
            if (ageSearch == pets[i].getAge())
            {
                printTableRow(i, pets[i].getName(), pets[i].getAge());
                ageSearchPetCount++;
            }
        }
//print table footer with ageSearchPetCount parameter
        printTableFooter(ageSearchPetCount);
    }

    private static void printTableHeader()
    {
        System.out.print("+---------------------+\n");
        System.out.print("| ID | NAME     | AGE |\n");
        System.out.print("+---------------------+\n");
    }

    private static void printTableRow(int id, String name, int age)
    {
        System.out.printf("| %2s | %-8s | %3d |\n", id, name, age);
    }

    private static void printTableFooter(int rowCount)
    {
        System.out.println("+---------------------+");
        System.out.println(rowCount + " rows in set.");
    }
}

class Pet
{
//attributes

    private String name;
    private int age;

//constructor
    Pet(String objectName, int objectAge)
    {
        name = objectName;
        age = objectAge;
    }

    String getName()
    {
        return name;
    }

    int getAge()
    {
        return age;
    }

    void setName(String newName)
    {
        name = newName;
    }

    void setAge(int newAge)
    {
        age = newAge;
    }
}
