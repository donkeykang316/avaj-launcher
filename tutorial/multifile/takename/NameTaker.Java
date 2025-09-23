package takename;

public class NameTaker {
    public String nametaker (String first, String last) {
        String f = first.trim();
        String l = last.trim();
        if (f.isEmpty() || l.isEmpty()) {
            System.out.println("Name cannot be empty!!!");
            return "";
        }
        return f + " " + l;
    }
}