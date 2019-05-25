package org.katheer.dao;

import org.katheer.dto.Student;

public interface StudentDao {
    String add(Student student);
    String update(Student student);
    Student search(int id);
    String delete(int id);
}
