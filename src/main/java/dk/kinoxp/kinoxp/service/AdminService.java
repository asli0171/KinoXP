package dk.kinoxp.kinoxp.service;

import dk.kinoxp.kinoxp.repository.AdminRepository;
import org.springframework.stereotype.Service;
import dk.kinoxp.kinoxp.model.Admin;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    public Admin saveadmin (Admin admin) {
        return adminRepository.save(admin);
    }

    public List<Admin> getAllAdmins (){
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById (Long id){
        return adminRepository.findById(id);
    }

    public void deleteAdminById (Long id){
        adminRepository.deleteById(id);
    }
}
