package wishlist.service;

import org.springframework.stereotype.Service;
import wishlist.repository.ReservationsRepository;

@Service
public class ReservationsService {
    private final ReservationsRepository reservationsRepository;

    public ReservationsService(ReservationsRepository reservationsRepository) {
        this.reservationsRepository = reservationsRepository;
    }
}
