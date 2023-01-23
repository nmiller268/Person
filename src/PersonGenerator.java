import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {

    public static void main(String[] args) {
        ArrayList<String> person = new ArrayList<>();
        Scanner in = new Scanner(System.in);


        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\personData.txt");

        boolean done = false;
        //ID
        String personRec = "";
        String ID = "";
        String firstName = "";
        String lastName = "";
        String title ="";
        int YOB = 0;

        Person sally = new Person("Sally", "Williams", 2005);
        System.out.println(sally);
        person.add(sally);
        sally.setLastName("Smith");
        System.out.println(sally);



        Person fred = new Person("Fred", "Williams", 2010);
        System.out.println(fred);
        person.add(fred);

        Person homer = new Person("Homer", "Simpson", 1975);
        System.out.println(homer);
        person.add(homer);

        do {


            ID = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits] ");
            firstName = SafeInput.getNonZeroLenString(in, "Enter first name ");
            lastName = SafeInput.getNonZeroLenString(in, "Enter last name ");
            title = SafeInput.getNonZeroLenString(in,"Enter title ");
            YOB = SafeInput.getRangedInt(in, "Enter the year of birth: ", 1000, 9999);

            personRec = ID + "," + firstName + "," + lastName + "," + title + "," + YOB;
            person.add(personRec);

            done = SafeInput.getYNConfirm(in, "Are you done? ");

        } while(!done);



        //Create new file

        try {

            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String recs : person) {
                writer.write(recs, 0, recs.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }

}

// public String toCSVRecord() {
//        return  this.IDNum + ", " + this.firstName + "," + this.lastName + "," + YOB;
//    }
//}