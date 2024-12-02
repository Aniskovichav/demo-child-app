package com.example.demo.service;

import com.example.demo.entity.Child;
import com.example.demo.repository.ChildRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildService {

    private final ChildRepository repository;

    public ChildService(ChildRepository repository) {
        this.repository = repository;
    }

    public List<Child> findAll() {
        return repository.findAll();
    }

    public Child save(Child child) {
        return repository.save(child);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

