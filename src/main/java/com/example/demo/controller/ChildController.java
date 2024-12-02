package com.example.demo.controller;

import com.example.demo.entity.Child;
import com.example.demo.service.ChildService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/children")
public class ChildController {

    private final ChildService service;

    public ChildController(ChildService service) {
        this.service = service;
    }

    @GetMapping
    public List<Child> getAllChildren() {
        return service.findAll();
    }

    @PostMapping
    public Child createChild(@RequestBody Child child) {
        return service.save(child);
    }

    @DeleteMapping("/{id}")
    public void deleteChild(@PathVariable Long id) {
        service.delete(id);
    }
}

