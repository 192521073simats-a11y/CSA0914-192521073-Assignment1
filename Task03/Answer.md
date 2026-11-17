# TASK 3 – Design the Control Logic (4 Marks)

## 1. Allocation pseudocode
```text
ALGORITHM AllocateSlot(vehicleNo)
    IF vehicleNo is null OR vehicleNo is blank THEN
        RETURN INVALID_INPUT
    ENDIF

    FOR floor ← 0 TO numberOfFloors - 1
        FOR slot ← 0 TO numberOfSlotsOnFloor(floor) - 1
            IF slots[floor][slot] = null THEN
                slots[floor][slot] ← vehicleNo
                RETURN (floor, slot)
            ENDIF
        ENDFOR
    ENDFOR

    RETURN NO_SPACE
END ALGORITHM
```

The search stops immediately after allocating the first suitable free slot. If both loops finish, the facility is full.

## 2. Fee pseudocode
```text
ALGORITHM CalculateFee(minutes, hourlyRate, dailyMaximum)
    IF minutes < 0 OR hourlyRate < 0 THEN
        RETURN INVALID_INPUT
    ENDIF
    IF minutes = 0 THEN
        RETURN 0
    ENDIF
    billableHours ← (minutes + 59) DIV 60
    fee ← billableHours * hourlyRate
    IF fee > dailyMaximum THEN
        fee ← dailyMaximum
    ENDIF
    RETURN fee
END ALGORITHM
```
`DIV` means integer division. The daily maximum is an example policy and must be configured by the facility.

## 3. Fee slabs using decisions
For a sample policy: first started hour Rs 30; each further started hour Rs 20; daily cap Rs 200:
```java
long hours = (minutes + 59L) / 60L;
double fee = hours == 0 ? 0 : 30 + Math.max(0, hours - 1) * 20;
fee = Math.min(fee, 200);
```
A `switch` is useful for a small fixed set of vehicle categories; `if-else` is suitable for ranges and time-based conditions.

## 4. Completed code
See `ParkingFacility.java` and `ParkingFacilityDemo.java`. The supplied class uses a jagged array, validates indexes, returns the first free location, and returns false for invalid or already-free releases.

## 5. Alternative: free-slot stacks
Maintain one stack of free slot indexes per floor and a free-slot count per floor. Allocation pops a free slot from a non-empty floor; release pushes the slot back. With a suitable floor-selection structure, push/pop are O(1); scanning the F floor stacks to find a non-empty one is O(F), or O(1) with an additional stack/queue of non-empty floors. The stack metadata requires O(F × S) slot references in addition to the occupancy representation, or it can replace scanning as the primary free-slot structure. The simple scan uses O(F × S) worst-case allocation and O(1) release. Both require O(F × S) storage for the parking state.
