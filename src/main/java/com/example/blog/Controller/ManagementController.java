package com.example.blog.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/management")
@PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")

public class ManagementController {
    @GetMapping
    @PreAuthorize("hasAnyAuthority('MANAGER_READ', 'ADMIN_READ')")
    public String get()
    {
        return "GET:management controller";
    }
    @PostMapping
    @PreAuthorize("hasAnyAuthority('MANAGER_CREATE', 'ADMIN_CREATE')")
    public String post()
    {
        return "POST:management controller";
    }
    @PutMapping
    @PreAuthorize("hasAnyAuthority('MANAGER_UPDATE', 'ADMIN_UPDATE')")
    public String put()
    {
        return "PUT:management controller";
    }
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('MANAGER_DELETE', 'ADMIN_DELETE')")
    public String delete()
    {
        return "DELETE:management controller";
    }

}
