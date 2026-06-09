package Arrays;

public class Demo1 {
    public static void main(String[] args) {
        int[] arr = new int[5];
        arr[0] = 10;
        arr[1] = 20;
        arr[2] = 30;
        arr[3] = 40;
        arr[4] = 50;
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Length of array is: " + arr.length);
            for (int j = 0; j < arr.length; j++) {
                System.out.println(arr[j]);
            }
        }
    }
}