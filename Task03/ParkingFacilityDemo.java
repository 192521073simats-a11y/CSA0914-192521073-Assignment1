import java.util.Arrays;

public class ParkingFacilityDemo {
    public static void main(String[] args) {
        ParkingFacility p = new ParkingFacility(new int[] {2, 1});
        System.out.println(Arrays.toString(p.allocate("TN01AB1111")));
        System.out.println(Arrays.toString(p.allocate("TN02CD2222")));
        System.out.println(Arrays.toString(p.allocate("TN03EF3333")));
        System.out.println(Arrays.toString(p.allocate("TN04GH4444")));
        System.out.println(p.release(0, 1) + " " + p.release(0, 1) + " "
                + p.release(5, 0));
        System.out.println(Arrays.toString(p.allocate("TN04GH4444")));
    }
}
