package backend.spectrum.dguonoff.DAO;

import backend.spectrum.dguonoff.DAO.idClass.ParticipationReservationId;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Participation_Reservation")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Participation_Reservation {

    @EmbeddedId
    private ParticipationReservationId bookmarkId;


    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User guestId;

    @ManyToOne
    @MapsId("reservationId")
    private Reservation reservationId;

}
