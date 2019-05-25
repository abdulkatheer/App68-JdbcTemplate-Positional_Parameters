package org.katheer.mapper;

import org.katheer.dto.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();

        student.setName(rs.getString("name"));
        student.setDept(rs.getString("dept"));
        student.setEmail(rs.getString("email"));
        student.setMobile(rs.getString("mobile"));
        student.setId(rs.getInt("id"));
        student.setCgpa(rs.getFloat("cgpa"));

        return student;
    }
}
