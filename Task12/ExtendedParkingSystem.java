import java.time.LocalTime;

/** Day-4 extension: e-bike flat rate, delivery-van daytime multiplier and five unequal floors. */
public class ExtendedParkingSystem {
    interface ParkingPrice {
        double fee(long startedHours, LocalTime parkedAt);
    }

    static class HourlyPrice implements ParkingPrice {
        private final double rate;
        HourlyPrice(double rate) { this.rate = rate; }
        public double fee(long hours, LocalTime time) { return hours * rate; }
    }

    static class EBikePrice implements ParkingPrice {
        private final double flatFee;
        EBikePrice(double flatFee) { this.flatFee = flatFee; }
        public double fee(long hours, LocalTime time) { return hours == 0 ? 0 : flatFee; }
    }

    static class DeliveryVanPrice implements ParkingPrice {
        private final double carRate;
        DeliveryVanPrice(double carRate) { this.carRate = carRate; }
        public double fee(long hours, LocalTime time) {
            boolean daytime = !time.isBefore(LocalTime.of(9, 0))
                    && time.isBefore(LocalTime.of(17, 0));
            return hours * carRate * (daytime ? 2.0 : 1.0);
        }
    }

    static class OneDayPass {
        private final LocalTime validFrom;
        private final LocalTime validUntil;
        OneDayPass(LocalTime validFrom, LocalTime validUntil) {
            if (validUntil.isBefore(validFrom)) throw new IllegalArgumentException("Invalid pass interval");
            this.validFrom = validFrom;
            this.validUntil = validUntil;
        }
        boolean isValidAt(LocalTime time) {
            return !time.isBefore(validFrom) && !time.isAfter(validUntil);
        }
    }

    public static void main(String[] args) {
        int[] capacity = {20, 35, 40, 50, 60};
        String[][] slots = new String[capacity.length][];
        for (int f = 0; f < capacity.length; f++) slots[f] = new String[capacity[f]];
        System.out.println("Floor capacities: 20, 35, 40, 50, 60");
        System.out.println("E-bike flat fee: Rs " +
                new EBikePrice(25).fee(4, LocalTime.of(10, 0)));
        System.out.println("Delivery van at 10:00 for 2 hours: Rs " +
                new DeliveryVanPrice(30).fee(2, LocalTime.of(10, 0)));
        System.out.println("Delivery van at 18:00 for 2 hours: Rs " +
                new DeliveryVanPrice(30).fee(2, LocalTime.of(18, 0)));
        OneDayPass pass = new OneDayPass(LocalTime.of(0, 0), LocalTime.of(23, 59));
        System.out.println("Visitor one-day pass valid at 14:00: " +
                pass.isValidAt(LocalTime.of(14, 0)));
    }
}
