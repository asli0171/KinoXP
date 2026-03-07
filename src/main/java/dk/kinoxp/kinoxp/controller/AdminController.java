package dk.kinoxp.kinoxp.controller;

import dk.kinoxp.kinoxp.model.Admin;
import dk.kinoxp.kinoxp.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")

public class AdminController {
    private final AdminService adminService;

    public AdminController (AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        return ResponseEntity.status(201).body(adminService.saveAdmin(admin));
    }

    @GetMapping
    public List<Admin> getAllAdmins(){
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id){
        return adminService.getAdminById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdminById(@PathVariable Long id) {
        adminService.deleteAdminById(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        return adminService.getAdminById(id)
                .map(existing -> {
                    admin.setId(id);
                    return ResponseEntity.ok(adminService.saveAdmin(admin));
                })
                .orElse(ResponseEntity.notFound().build());

    }

}
