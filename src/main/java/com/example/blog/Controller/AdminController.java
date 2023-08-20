package com.example.blog.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN_READ')")
    public String get()
    {
        return "GET:admin controller";
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN_CREATE')")
    public String post()
    {
        return "POST:admin controller";
    }
    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN_UPDATE')")
    public String put()
    {
        return "PUT:admin controller";
    }
    @DeleteMapping
    @PreAuthorize("hasAuthority('ADMIN_DELETE')")
    public String delete()
    {
        return "DELETE:admin controller";
    }

}
