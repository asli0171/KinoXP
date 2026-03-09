package dk.kinoxp.kinoxp;

import dk.kinoxp.kinoxp.model.Admin;
import dk.kinoxp.kinoxp.model.Film;
import dk.kinoxp.kinoxp.model.Hall;
import dk.kinoxp.kinoxp.model.Screening;
import dk.kinoxp.kinoxp.model.Seat;
import dk.kinoxp.kinoxp.repository.AdminRepository;
import dk.kinoxp.kinoxp.repository.HallRepository;
import dk.kinoxp.kinoxp.service.AdminService;
import dk.kinoxp.kinoxp.service.PricingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class KinoXpApplication {

    public static void main(String[] args) {
        SpringApplication.run(KinoXpApplication.class, args);
    }

    @Bean
    public CommandLineRunner testPricing(PricingService pricingService, AdminService adminService, AdminRepository adminRepository, HallRepository hallRepository) {
        return args -> {
            try {
                Film testFilm = new Film("Test Film", "Danish", "PG", 180);
                Hall testHall = new Hall(1);
                Screening testScreening = new Screening(100, testFilm, testHall, LocalDateTime.now(), true);
                List<Seat> seats = new ArrayList<>();
                seats.add(new Seat("cowboy", 1));

                double result = pricingService.calculatePrice(testScreening, 3, seats);
                System.out.println("Calculated price: " + result + " DKK");
            } catch (Exception e) {
                System.out.println("PricingService error: " + e.getMessage());
            }

            if (adminRepository.findByUsername("admin").isEmpty()) {
                adminService.saveAdmin(new Admin("admin", "admin123"));
            }

            if (hallRepository.count() == 0) {
                for (int i = 1; i <= 5; i++) {
                    hallRepository.save(new Hall(i));
                }
            }
        };
    }
}