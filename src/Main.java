import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone("883615113");
    private static String nonExistentAnswer = "Contact non existent, please try again.";

    public static void main(String[] args) {
        boolean quit = false;
        int choice = 0;
        printInstructions();

        while (!quit) {
            System.out.println("Enter your choice (0 for menu, 6 to quit): ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    printInstructions();
                    break;
                case 1:
                    System.out.println("Add new contact.");
                    addContact();
                    break;
                case 2:
                    System.out.println("Update existing contact.");
                    updateCont();
                    break;
                case 3:
                    System.out.println("Remove existing contact.");
                    remCont();
                    break;
                case 4:
                    System.out.println("Find contact.");
                    findCont();
                    break;
                case 5:
                    System.out.println("Print all contacts.");
                    mobilePhone.printContacts();
                    break;
                case 6:
                    quit = true;
                    break;
                default:
                    System.out.println("Choice out of bounds, please try again (0 for menu, 6 to quit): ");
                    break;
            }
        }
    }
    private static void printInstructions() {
        System.out.println("Press 0 to print menu.");
        System.out.println("Press 1 to add new contact.");
        System.out.println("Press 2 to update existing contact.");
        System.out.println("Press 3 to remove existing contact.");
        System.out.println("Press 4 to find contact.");
        System.out.println("Press 5 to print all contacts.");
        System.out.println("Press 6 to quit.");
    }

    public static void addContact() {
        System.out.println("Enter contact name: \r");
        String name = scanner.nextLine();
        System.out.println("Enter phone number: \r");
        String number = scanner.nextLine();
        Contact contact = Contact.createContact(name, number);
        if (mobilePhone.addNewContact(contact)) System.out.println(
                "New contact named " + contact.getName() + " added successfully.");
        else System.out.println("Unable to add new contact. Contact named " +
                contact.getName() + " already exists.");
    }

    public static void updateCont() {
        System.out.println("Enter name/position of the contact you want to update: \r");
        String name = scanner.nextLine();
        int position;
        try {position = Integer.parseInt(name);}
        catch (NumberFormatException e) {position = -1;}

        if (position > 0 && position <= mobilePhone.arrSize()) {
            Contact oldCont = mobilePhone.queryContact(position - 1);
            updateContactCopy(oldCont);
        } else {
            Contact oldCont = mobilePhone.queryContact(name);
            updateContactCopy(oldCont);
        }
    }

    public static void updateContactCopy(Contact contact) {
        if (contact != null) {
            System.out.println("Enter new name: \r");
            String newName = scanner.nextLine();
            System.out.println("Enter new phone number: \r");
            String newNum = scanner.nextLine();
            Contact newCont = Contact.createContact(newName, newNum);
            if (mobilePhone.queryContact(newCont) != null) System.out.println(
                    "Unable to update contact. Contact named " + newCont.getName() + " already exists.");
            else if (mobilePhone.updateContact(contact, newCont)) System.out.println(
                    "Contact successfully updated.");
            else System.out.println("Error while updating contact.");
        } else System.out.println(nonExistentAnswer);
    }

    public static void remCont() {
        System.out.println("Enter name of the contact you want to remove: \r");
        String name = scanner.nextLine();
        int position;
        try {position = Integer.parseInt(name);}
        catch (NumberFormatException e) {position = -1;}

        if (position > 0 && position <= mobilePhone.arrSize()) {
            Contact remCont = mobilePhone.queryContact(position - 1);
            remContCopy(remCont);
        } else {
            Contact remCont = mobilePhone.queryContact(name);
            remContCopy(remCont);
        }
    }

    public static void remContCopy(Contact contact) {
        if (contact != null) {
            if (mobilePhone.removeContact(contact)) System.out.println("Contact removed successfully.");
            else System.out.println("Error while removing contact.");
        } else System.out.println(nonExistentAnswer);
    }

    public static void findCont() {
        System.out.println("Enter name of the contact you want to find: \r");
        String name = scanner.nextLine();
        Contact findCont = mobilePhone.queryContact(name);
        if (findCont != null) {
            System.out.println(findCont.getName() + " -> " + findCont.getPhoneNumber());
        } else System.out.println(nonExistentAnswer);
    }

//    not needed in the form of a method, can be called directly from the switch statement
//    public static void print() {
//        MobilePhone.printContacts();
//    }
}
