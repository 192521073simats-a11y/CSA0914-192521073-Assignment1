public final class ParkingTicket {
    private final long ticketId;
    private final String registrationNo;
    private final int floor;
    private final int slot;
    private final long entryTimeMillis;

    public ParkingTicket(long ticketId, String registrationNo, int floor, int slot) {
        this(ticketId, registrationNo, floor, slot, System.currentTimeMillis());
    }

    public ParkingTicket(long ticketId, String registrationNo, int floor, int slot,
                         long entryTimeMillis) {
        if (ticketId <= 0 || registrationNo == null || registrationNo.isBlank()
                || floor < 0 || slot < 0 || entryTimeMillis < 0) {
            throw new IllegalArgumentException("Invalid parking ticket details");
        }
        this.ticketId = ticketId;
        this.registrationNo = registrationNo.trim().toUpperCase();
        this.floor = floor;
        this.slot = slot;
        this.entryTimeMillis = entryTimeMillis;
    }

    public long getTicketId() { return ticketId; }
    public String getRegistrationNo() { return registrationNo; }
    public int getFloor() { return floor; }
    public int getSlot() { return slot; }
    public long getEntryTimeMillis() { return entryTimeMillis; }

    @Override
    public String toString() {
        return "ParkingTicket{id=" + ticketId + ", registrationNo='" + registrationNo
                + "', floor=" + floor + ", slot=" + slot + "}";
    }
}
