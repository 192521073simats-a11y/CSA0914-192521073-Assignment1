import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/** Runnable console demonstration of the core campus parking workflow. */
public class SmartParkingSystem {
    enum VehicleType { TWO_WHEELER, CAR, ELECTRIC }

    static final class Ticket {
        final long id;
        final String registration;
        final VehicleType type;
        final int floor;
        final int slot;
        final Instant enteredAt;
        final boolean monthlyPass;

        Ticket(long id, String registration, VehicleType type, int floor, int slot,
               Instant enteredAt, boolean monthlyPass) {
            this.id = id; this.registration = registration; this.type = type;
            this.floor = floor; this.slot = slot; this.enteredAt = enteredAt;
            this.monthlyPass = monthlyPass;
        }
    }

    static final class Facility {
        private final String[][] slots;
        private final Map<String, Ticket> active = new HashMap<>();
        private long nextTicketId = 1;

        Facility(int[] capacities) {
            slots = new String[capacities.length][];
            for (int f = 0; f < capacities.length; f++) {
                if (capacities[f] < 0) throw new IllegalArgumentException("Invalid capacity");
                slots[f] = new String[capacities[f]];
            }
        }

        Ticket enter(String reg, VehicleType type, boolean validMonthlyPass, Instant time) {
            String key = normalize(reg);
            if (active.containsKey(key)) throw new IllegalArgumentException("Duplicate active registration");
            for (int f = 0; f < slots.length; f++) {
                for (int s = 0; s < slots[f].length; s++) {
                    if (slots[f][s] == null) {
                        slots[f][s] = key;
                        Ticket ticket = new Ticket(nextTicketId++, key, type, f, s, time,
                                validMonthlyPass && type != null);
                        active.put(key, ticket);
                        return ticket;
                    }
                }
            }
            throw new IllegalStateException("Parking facility is full");
        }

        double exit(String reg, Instant time) {
            String key = normalize(reg);
            Ticket t = active.get(key);
            if (t == null) throw new IllegalArgumentException("No active ticket");
            long minutes = Math.max(0, Duration.between(t.enteredAt, time).toMinutes());
            long hours = (minutes + 59L) / 60L;
            double rate = switch (t.type) {
                case TWO_WHEELER -> 10.0;
                case CAR -> 30.0;
                case ELECTRIC -> 60.0;
            };
            double fee = t.monthlyPass ? 0.0 : hours * rate;
            slots[t.floor][t.slot] = null;
            active.remove(key);
            return fee;
        }

        boolean release(int floor, int slot) {
            if (floor < 0 || floor >= slots.length || slot < 0 || slot >= slots[floor].length)
                return false;
            if (slots[floor][slot] == null) return false;
            slots[floor][slot] = null;
            return true;
        }

        private static String normalize(String reg) {
            if (reg == null || reg.isBlank()) throw new IllegalArgumentException("Registration is required");
            return reg.trim().toUpperCase();
        }
    }

    public static void main(String[] args) {
        Facility facility = new Facility(new int[] {2, 1});
        Instant start = Instant.parse("2026-11-17T08:00:00Z");
        Ticket car = facility.enter("TN01AB1234", VehicleType.CAR, false, start);
        System.out.println("Ticket " + car.id + " allocated at [" + car.floor + ", " + car.slot + "]");
        System.out.println("Fee for 90 minutes: Rs " +
                facility.exit("TN01AB1234", start.plus(Duration.ofMinutes(90))));
        Ticket student = facility.enter("TN02CD5678", VehicleType.TWO_WHEELER, true, start);
        System.out.println("Monthly-pass ticket " + student.id + " allocated at ["
                + student.floor + ", " + student.slot + "]");
        System.out.println("Pass-holder fee: Rs " +
                facility.exit("TN02CD5678", start.plus(Duration.ofMinutes(30))));
        System.out.println("Invalid release result: " + facility.release(9, 0));
    }
}
