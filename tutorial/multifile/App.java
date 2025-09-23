import takename.NameTaker;
import randomint.GetRandomInt;

public class App {
    public static void main (String args[]) {
        if (args.length != 2) {
            System.out.println("Provide your first name and last name!!!");
            return;
        }
        NameTaker nk = new NameTaker();
        GetRandomInt gri = new GetRandomInt();

        String fullname = nk.nametaker(args[0], args[1]);
        if (fullname == null) {
            System.out.println("You didn't provide your full name!");
            return;
        }

        int num = gri.randomint();

        System.out.println("Hello there! " + fullname + ", welcome! Your got the score " + num);
    }
}