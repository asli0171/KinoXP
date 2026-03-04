package dk.kinoxp.kinoxp.service;

import dk.kinoxp.kinoxp.repository.ScreeningRepository;
import org.springframework.stereotype.Service;
import dk.kinoxp.kinoxp.model.Screening;
import java.util.List;
import java.util.Optional;

@Service
public class ScreeningService {
    private final ScreeningRepository screeningRepository;

    public ScreeningService(ScreeningRepository screeningRepository){
        this.screeningRepository = screeningRepository;
    }

    public Screening savescreening (Screening screening) {
        return screeningRepository.save(screening);
    }

    public List<Screening> getAllScreenings (){
        return screeningRepository.findAll();
    }

    public Optional<Screening> getHallById (Long id){
        return screeningRepository.findById(id);
    }

    public void deleteScreeningById (Long id){
        screeningRepository.deleteById(id);
    }
}
