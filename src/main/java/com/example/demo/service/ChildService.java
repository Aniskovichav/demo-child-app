package com.example.demo.service;

import com.example.demo.entity.Child;
import com.example.demo.repository.ChildRepository;
import com.example.demo.dto.ChildDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChildService {

    private final ChildRepository childRepository;

    public List<ChildDTO> getAllChildren() {
        return childRepository.findAll()
                .stream()
                .map(this::convertToDTO)//TODO (child -> new ChildDTO(child.getId(), child.getName(), child.getAge()))
                .collect(Collectors.toList());
    }

    public ChildDTO getChildById(long id) {
        return childRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Child not found"));
    }

    public ChildDTO createChild(ChildDTO childDTO) {
        Child child = convertToEntity(childDTO);
        return convertToDTO(childRepository.save(child));
    }

    public ChildDTO updateChild(long id, ChildDTO childDTO) {
        Child child = childRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Child not found"));
        child.setName(childDTO.getName());
        child.setAge(childDTO.getAge());
        return convertToDTO(childRepository.save(child));
    }

    public void deleteChild(long id) {
        childRepository.deleteById(id);
    }

    private Child convertToEntity(ChildDTO childDTO) {
        return new Child(childDTO.getId(), childDTO.getName(), childDTO.getAge());
    }

    private ChildDTO convertToDTO(Child child) {
        return new ChildDTO(child.getId(), child.getName(), child.getAge());
    }
}
