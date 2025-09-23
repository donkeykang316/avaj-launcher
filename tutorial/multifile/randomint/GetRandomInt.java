package randomint;

public class GetRandomInt {
    public int randomint () {
        int min = 0;
        int max = 10;
        int num = (int)(Math.random() * (max - min + 1)) + min;
        return num;
    }
}