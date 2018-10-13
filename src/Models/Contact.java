package Models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.time.LocalDate;

public class Contact {

    private String firstName, lastName, address, phone;
    private int id;
    private LocalDate birthday;
    private File image;

    /**
     * Constructor for contact
     * @param firstName
     * @param lastName
     * @param birthday
     * @param address
     * @param phone
     */
    public Contact( String firstName, String lastName, LocalDate birthday,String address, String phone ) {
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setPhone(phone);
        setBirthday(birthday);
    }

    /**
     * get if of the contact
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * set id of the contact
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get first name of the contact
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * set first name of the contact if it isn't empty
     * @param firstName
     */
    public void setFirstName(String firstName) {
        if (firstName.isEmpty())
            throw new IllegalArgumentException("First name cannot be empty");
        else
        this.firstName = firstName;
    }

    /**
     * get the last name of the contact
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * set the last name of the contact if it isn't empty
     * @param lastName
     */
    public void setLastName(String lastName) {
        if (lastName.isEmpty())
            throw new IllegalArgumentException("Last name cannot be empty");
        else
        this.lastName = lastName;
    }

    /**
     * get the address of the contact
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * set the address of the contact if it isn't empty
     * @param address
     */
    public void setAddress(String address) {
        if (address.isEmpty())
            throw new IllegalArgumentException("Address cannot be empty");
        else
        this.address = address;
    }

    /**
     * get the phone number of the contact
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * set the phone number of the contact if it isn't empty
     * @param phone
     */
    public void setPhone(String phone) {
        if (phone.isEmpty())
            throw new IllegalArgumentException("Phone cannot be empty");
        else
        this.phone = phone;
    }

    /**
     * get the birthday of the contact
     * @return
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * set the birthday of the contact if the birthday is not after today
     * @param birthday
     */
    public void setBirthday(LocalDate birthday) {
        if (!birthday.isAfter(LocalDate.now()))
            this.birthday = birthday;
        else
            throw new IllegalArgumentException("Birthday's cannot be in the future");
    }



/*
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        ArrayList<String> validMakes = null;

        try
        {
            //get valid mobile phone manufacturers from database
            validMakes = DBConnect.getPhoneManufacturers();
            if (validMakes.contains(make))
                this.make = make;
            else
                throw new IllegalArgumentException("Valid manufacturers are: " + validMakes.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (!model.isEmpty() && model.length() <= 30)
            this.model = model;
        else
            throw new IllegalArgumentException("model name cannot be empty and" +
                    "must be less than 30 characters");
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public double getMemory() {
        return memory;
    }

    public void setMemory(double memory) {
        this.memory = memory;
    }

    public double getFrontCamRes() {
        return frontCamRes;
    }

    public void setFrontCamRes(double frontCamRes) {
        this.frontCamRes = frontCamRes;
    }

    public double getRearCamRes() {
        return rearCamRes;
    }

    public void setRearCamRes(double rearCamRes) {
        this.rearCamRes = rearCamRes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
*/
    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
/*
    public String toString()
    {
        return String.format("%s %s with %.0f gigs of memory", this.make,
                this.model, this.memory);
    }
*/
    /**
     * This method will copy the file specified to the images directory on this server and give it
     * a unique name
     */
    public void copyImageFile() throws IOException
    {
        //create a new Path to copy the image into a local directory
        Path sourcePath = image.toPath();

        String uniqueFileName = getUniqueFileName(image.getName());

        Path targetPath = Paths.get("./src/images/"+uniqueFileName);

        //copy the file to the new directory
        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

        //update the imageFile to point to the new File
        image = new File(targetPath.toString());
    }

    /**
     * This method will receive a String that represents a file name and return a
     * String with a random, unique set of letters prefixed to it
     */
    private String getUniqueFileName(String oldFileName)
    {
        String newName;

        //create a Random Number Generator
        SecureRandom rng = new SecureRandom();

        //loop until we have a unique file name
        do
        {
            newName = "";

            //generate 32 random characters
            for (int count=1; count <=32; count++)
            {
                int nextChar;

                do
                {
                    nextChar = rng.nextInt(123);
                } while(!validCharacterValue(nextChar));

                newName = String.format("%s%c", newName, nextChar);
            }
            newName += oldFileName;

        } while (!uniqueFileInDirectory(newName));

        return newName;
    }

    /**
     * This method will search the images directory and ensure that the file name
     * is unique
     */
    public boolean uniqueFileInDirectory(String fileName)
    {
        File directory = new File("./src/images/");

        File[] dir_contents = directory.listFiles();

        for (File file: dir_contents)
        {
            if (file.getName().equals(fileName))
                return false;
        }
        return true;
    }

    /**
     * This method will validate if the integer given corresponds to a valid
     * ASCII character that could be used in a file name
     */
    public boolean validCharacterValue(int asciiValue)
    {

        //0-9 = ASCII range 48 to 57
        if (asciiValue >= 48 && asciiValue <= 57)
            return true;

        //A-Z = ASCII range 65 to 90
        if (asciiValue >= 65 && asciiValue <= 90)
            return true;

        //a-z = ASCII range 97 to 122
        if (asciiValue >= 97 && asciiValue <= 122)
            return true;

        return false;
    }

}
