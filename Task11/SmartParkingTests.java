import java.time.Instant;

/** Repeatable boundary and invalid-input checks for SmartParkingSystem. */
public class SmartParkingTests {
    private static int passed = 0;
    private static void check(String name, boolean condition) {
        System.out.println(name + ": " + (condition ? "PASS" : "FAIL"));
        if (!condition) throw new AssertionError(name);
        passed++;
    }
    public static void main(String[] args) {
        Instant start = Instant.parse("2026-11-17T08:00:00Z");

        SmartParkingSystem.Facility normal = new SmartParkingSystem.Facility(new int[]{1});
        SmartParkingSystem.Ticket t = normal.enter("tn01aa0001",
                SmartParkingSystem.VehicleType.CAR, false, start);
        check("Normal entry allocates last free slot", t.floor == 0 && t.slot == 0);
        check("Zero-minute parking fee", normal.exit("TN01AA0001", start) == 0.0);

        SmartParkingSystem.Facility boundary = new SmartParkingSystem.Facility(new int[]{1});
        boundary.enter("TN01AA0002", SmartParkingSystem.VehicleType.CAR, false, start);
        check("Exactly 60 minutes costs one hour", boundary.exit("TN01AA0002",
                start.plusSeconds(3600)) == 30.0);

        SmartParkingSystem.Facility overBoundary = new SmartParkingSystem.Facility(new int[]{1});
        overBoundary.enter("TN01AA0003", SmartParkingSystem.VehicleType.CAR, false, start);
        check("61 minutes rounds up to two hours", overBoundary.exit("TN01AA0003",
                start.plusSeconds(3660)) == 60.0);

        SmartParkingSystem.Facility duplicate = new SmartParkingSystem.Facility(new int[]{2});
        duplicate.enter("TN01AA0004", SmartParkingSystem.VehicleType.CAR, false, start);
        boolean duplicateRejected = false;
        try { duplicate.enter("tn01aa0004", SmartParkingSystem.VehicleType.CAR, false, start); }
        catch (IllegalArgumentException expected) { duplicateRejected = true; }
        check("Duplicate active registration rejected", duplicateRejected);

        boolean unknownRejected = false;
        try { SmartParkingSystem.VehicleType.valueOf("BUS"); }
        catch (IllegalArgumentException expected) { unknownRejected = true; }
        check("Unknown vehicle type rejected", unknownRejected);

        SmartParkingSystem.Facility full = new SmartParkingSystem.Facility(new int[]{1});
        full.enter("TN01AA0005", SmartParkingSystem.VehicleType.TWO_WHEELER, false, start);
        boolean fullRejected = false;
        try { full.enter("TN01AA0006", SmartParkingSystem.VehicleType.CAR, false, start); }
        catch (IllegalStateException expected) { fullRejected = true; }
        check("Full facility rejects additional vehicle", fullRejected);

        check("Invalid slot release rejected", !full.release(4, 0) && !full.release(0, 2));
        SmartParkingSystem.Facility pass = new SmartParkingSystem.Facility(new int[]{1});
        pass.enter("TN01AA0007", SmartParkingSystem.VehicleType.TWO_WHEELER, true, start);
        check("Valid monthly-pass parking fee is zero", pass.exit("TN01AA0007",
                start.plusSeconds(1800)) == 0.0);

        System.out.println("Passed " + passed + " tests.");
    }
}
