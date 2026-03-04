package dk.kinoxp.kinoxp.service;

import dk.kinoxp.kinoxp.repository.HallRepository;
import org.springframework.stereotype.Service;
import dk.kinoxp.kinoxp.model.Hall;
import java.util.List;
import java.util.Optional;

@Service
public class HallService {
    private final HallRepository hallRepository;

    public HallService(HallRepository hallRepository){
        this.hallRepository = hallRepository;
    }

    public Hall savehall (Hall hall) {
        return hallRepository.save(hall);
    }

    public List<Hall> getAllHalls (){
        return hallRepository.findAll();
    }

    public Optional<Hall> getHallById (Long id){
        return hallRepository.findById(id);
    }

    public void deleteHallById (Long id){
        hallRepository.deleteById(id);
    }
}
