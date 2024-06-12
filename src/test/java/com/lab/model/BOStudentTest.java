package com.lab.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BOStudentTest {
    BOStudent boStudent;

    @BeforeEach
    void setUp() {
        boStudent = new BOStudent();
        boStudent.addStudent(new Student("HE170815", "Vu Gia Khanh", "3", "Java"));
        boStudent.addStudent(new Student("HE170945", "Do Thuy Duong", "3", ".Net"));
        boStudent.addStudent(new Student("HE172592", "Duong Tuan Du", "3", "C/C++"));
        boStudent.addStudent(new Student("HE173204", "Nguyen Thanh Tung", "3", "Java"));
        boStudent.addStudent(new Student("HE173204", "Nguyen Thanh Tung", "3", ".Net"));
        boStudent.addStudent(new Student("HE173204", "Nguyen Thanh Tung", "4", ".Net"));
        boStudent.addStudent(new Student("HE173260", "Nguyen Tien Dat", "3", ".Net"));
        boStudent.addStudent(new Student("HE173571", "Bui Viet Khang", "3", "Java"));
        boStudent.addStudent(new Student("HE180018", "Phan Duc Huy", "3", "C/C++"));
        boStudent.addStudent(new Student("HE180047", "Vu Viet Anh", "3", "C/C++"));
        boStudent.addStudent(new Student("HE180047", "Vu Viet Anh", "4", "C/C++"));
        boStudent.addStudent(new Student("HE180047", "Vu Viet Anh", "5", "C/C++"));
    }

//    addStudent
    @Test
    void testAddStudent() {
        Student student = new Student("HE123456", "Nguyen Van A", "3", "Java");
        assertTrue(boStudent.addStudent(student));
    }

    @Test
    void testAddStudent_DuplicateStudent() {
        Student student = new Student("HE170815", "Vu Gia Khanh", "3", "Java");
        assertFalse(boStudent.addStudent(student));
    }

//    getNumberStudent
    @Test
    void testGetNumberStudent_WithInitialSetup() {
        int numberOfStudents = boStudent.getNumberStudent();
        assertEquals(12, numberOfStudents);
    }

    @Test
    void testGetNumberStudent_NotZero_WithInitialSetup() {
        int numberOfStudents = boStudent.getNumberStudent();
        assertNotEquals(0, numberOfStudents);
    }

//    isExistedId
    @Test
    void testIsExistedId_ExistingId() {
        boolean exists = boStudent.isExistedId("HE170815");
        assertTrue(exists, "ID HE170815 should exist in the list");
    }

    @Test
    void testIsExistedId_NonExistingId() {
        boolean exists = boStudent.isExistedId("HE999999");
        assertFalse(exists, "ID HE999999 should not exist in the list");
    }

    @Test
    void testIsExistedId_CaseInsensitive() {
        boolean exists = boStudent.isExistedId("he170815");
        assertTrue(exists, "ID he170815 should exist in the list (case insensitive)");
    }


//    getStudentById
    @Test
    void testGetStudentById_ExistingId() {
        Student student = boStudent.getStudentById("HE170815");
        assertNotNull(student);
        assertEquals("HE170815", student.getId());
        assertEquals("Vu Gia Khanh", student.getStudentName());
    }

    @Test
    void testGetStudentById_NonExistingId() {
        Student student = boStudent.getStudentById("HE999999");
        assertNull(student);
    }

    @Test
    void testGetStudentById_CaseInsensitive() {
        Student student = boStudent.getStudentById("he170815");
        assertNotNull(student);
        assertEquals("HE170815", student.getId());
        assertEquals("Vu Gia Khanh", student.getStudentName());
    }

    // deleteStudent
    @Test
    void testDeleteStudent_ExistingStudent() {
        Student student = new Student("HE171034", "Nguyen Khanh Toan", "3", "Java");
        boStudent.addStudent(student);
        boStudent.deleteStudent(student);
        assertNull(boStudent.getStudentById("HE171034"));
    }

    @Test
    void testDeleteStudent_NonExistingStudent() {
        Student student = new Student("HE999999", "Non Existing Student", "3", "Java");
        boStudent.deleteStudent(student);
        assertFalse(boStudent.isExistedId("HE999999"));
    }


//    isExistedId
    @ParameterizedTest
    @ValueSource(strings = {"HE170815", "HE170945", "HE172592", "HE173204", "HE173260", "HE173571", "HE180018", "HE180047"})
    void testIsExistedId_ExistingId_Parameterized(String id) {
        boolean exists = boStudent.isExistedId(id);
        assertTrue(exists);
    }

//    isDuplicateStudent
    @Test
    void testIsDuplicateStudent_DuplicateStudent() {
        Student student = new Student("HE170815", "Vu Gia Khanh", "3", "Java");
        assertTrue(boStudent.isDuplicateStudent(student));
    }

    @Test
    void testIsDuplicateStudent_NonDuplicateStudent() {
        Student student = new Student("HE999999", "Non Existing Student", "3", "Java");
        assertFalse(boStudent.isDuplicateStudent(student));
    }

    @Test
    void testIsDuplicateStudent_CaseInsensitive() {
        Student student = new Student("he170815", "Vu Gia Khanh", "3", "Java");
        assertTrue(boStudent.isDuplicateStudent(student));
    }

//    sortStudentByNameAsc

}