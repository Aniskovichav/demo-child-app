package com.example.demo.service;

import com.example.demo.dto.ChildDTO;
import com.example.demo.entity.Child;
import com.example.demo.repository.ChildRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ChildServiceTest {

//    @Mock
//    private ChildRepository childRepository;
//
//    @InjectMocks
//    private ChildService childService;
//
//    private Child child;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        child = new Child(1L, "John", 10);
//    }
//
//    @Test
//    void testGetChildById() {
//        // Arrange
//        when(childRepository.findById(1L)).thenReturn(Optional.of(child));
//
//        // Act
//        ChildDTO childDTO = childService.getChildById(1L);
//
//        // Assert
//        assertNotNull(childDTO);
//        assertEquals("John", childDTO.getName());
//    }
//
//    @Test
//    void testCreateChild() {
//        // Arrange
//        ChildDTO childDTO = new ChildDTO(1L, "Jane", 5);//TODO
//        when(childRepository.save(any(Child.class))).thenReturn(child);
//
//        // Act
//        ChildDTO createdChildDTO = childService.createChild(childDTO);
//
//        // Assert
//        assertNotNull(createdChildDTO);
//        assertEquals("John", createdChildDTO.getName());
//    }
//
//    @Test
//    void testUpdateChild() {
//        // Arrange
//        ChildDTO childDTO = new ChildDTO(1L, "Updated Name", 12);
//        when(childRepository.findById(1L)).thenReturn(Optional.of(child));
//        when(childRepository.save(any(Child.class))).thenReturn(child);
//
//        // Act
//        ChildDTO updatedChildDTO = childService.updateChild(1L, childDTO);
//
//        // Assert
//        assertNotNull(updatedChildDTO);
//        assertEquals("Updated Name", updatedChildDTO.getName());
//    }
//
//    @Test
//    void testDeleteChild() {
//        // Arrange
//        when(childRepository.existsById(1L)).thenReturn(true);
//
//        // Act
//        childService.deleteChild(1L);
//
//        // Assert
//        verify(childRepository, times(1)).deleteById(1L);
//    }
}
