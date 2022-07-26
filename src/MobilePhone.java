import java.util.ArrayList;

public class MobilePhone {

    private String myNumber;
    private ArrayList<Contact> myContacts;

    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<Contact>();
    }
    //    true if not exists and created, false if else
    public boolean addNewContact(Contact contact) {
        if (findContact(contact) == -1) {
            this.myContacts.add(contact);
            return true;
        }
        return false;
    }
    //    true if exists and updated, false if else
    public boolean updateContact(Contact oldCont, Contact newCont) {
        int indexOfContact = findContact(oldCont);
        if (indexOfContact != -1) {
            this.myContacts.set(indexOfContact, newCont);
//            removeContact(oldCont);
//            addNewContact(newCont);
            return true;
        }
        return false;
    }
    //    true if exists and removed, false if else
    public boolean removeContact(Contact contact) {
        int indexOfContact = findContact(contact);
        if (indexOfContact != -1) {
            this.myContacts.remove(indexOfContact);
            return true;
        }
        return false;
    }
    //    return position in the arrayList or -1 if else
    private int findContact(Contact contact) {
        return findContact(contact.getName());
//        return this.myContacts.indexOf(contact);
    }
    //    as instructed above
    private int findContact(String name) {
        for (int i = 0; i < this.myContacts.size(); i++) {
            Contact contact = this.myContacts.get(i);
            if (contact.getName().equals(name)) return i;
        }
        return -1;
    }
    //    return contact based on string name, return null if else
    public Contact queryContact(String name) {
        int index = findContact(name);
        if (index != -1) return this.myContacts.get(index);
//        for (int i = 0; i < this.myContacts.size(); i++) {
//            Contact contact = this.myContacts.get(i);
//            if (contact.getName().equals(name)) return contact;
//        }
        return null;
    }

    public String queryContact(Contact contact) {
        if (findContact(contact) != -1) return contact.getName();
        else return null;
    }

    public Contact queryContact(int i) {
        return this.myContacts.get(i);
    }

    public int arrSize() {
        return this.myContacts.size();
    }

    //    print contacts pattern: 1. name -> phoneNumber
    public void printContacts() {
        for (int i = 0; i < this.myContacts.size(); i++) {
            Contact contact = this.myContacts.get(i);
            System.out.println((i+1) + ". " + contact.getName() + " -> " + contact.getPhoneNumber());
        }
    }

//    public void printContacts() {
//        for (int i = 0; i < this.myContacts.size(); i++)
//            System.out.println((i+1) + ". " + this.myContacts.get(i).getName() + " -> " + this.myContacts.get(i).getPhoneNumber());
//    }
}
