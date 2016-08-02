package zhangyi.refactoring.featureenvy;

import java.util.ArrayList;
import java.util.List;

public class Train {
    private final List<Carriage> carriages = new ArrayList<Carriage>();
    private int percentReservedBarrier = 70;

    public void reserveSeats(ReservationRequest request) {
        for (Carriage carriage : carriages) {
            if (carriage.subscribedPercent() < percentReservedBarrier ) {
                request.reserveSeatsIn(carriage);
                return;
            }
        }
        request.cannotFindSeat();
    }
}

