package org.katheer.dao;

import org.katheer.dto.Student;
import org.katheer.mapper.StudentRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String add(Student student) {
        String status = "";
        try {
            List<Student> studentList = jdbcTemplate.query("SELECT * FROM student " +
                    "WHERE id=?", new Object[] {student.getId()},
                    new StudentRowMapper());

            if (studentList.isEmpty()) {
                int rowCount = jdbcTemplate.update("INSERT INTO student VALUES(?,?,?,?,?,?)",
                        new Object[] {student.getId(), student.getName(),
                                student.getDept(), student.getCgpa(),
                                student.getEmail(), student.getMobile()});

                if (rowCount == 1) {
                    status = "Student Inserted Successfully!";
                } else {
                    status = "Student Insert failed !";
                }
            } else {
                status = "Student already exists!";
            }
        } catch (Exception e) {
            status = "Student Insert failed !";
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public String update(Student student) {
        String status = "";

        try {
            int rowCount = jdbcTemplate.update("UPDATE student SET name=?," +
                    "dept=?,cgpa=?,email=?,mobile=? WHERE id=?",
                    new Object[] {student.getName(), student.getDept(),
                            student.getCgpa(), student.getEmail(),
                            student.getMobile(), student.getId()});

            if (rowCount == 1) {
                status = "Student Updated successfully!";
            } else {
                status = "Student not found!";
            }
        } catch (Exception e) {
            status = "Student update failed!";
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Student search(int id) {
        Student student = null;

        List<Student> studentList = jdbcTemplate.query("SELECT * FROM student" +
                " WHERE id=?", new Object[] {id}, (rs, index) -> {
            Student student1 = new Student();
            student1.setId(rs.getInt("id"));
            student1.setName(rs.getString("name"));
            student1.setDept(rs.getString("dept"));
            student1.setCgpa(rs.getFloat("cgpa"));
            student1.setMobile(rs.getString("mobile"));
            student1.setEmail(rs.getString("email"));
            return student1;
        });

        if (!studentList.isEmpty()) {
            student = studentList.get(0);
        }

        return student;
    }

    @Override
    public String delete(int id) {
        String status = "";

        try {
            int rowCount =
                    jdbcTemplate.update("DELETE FROM student WHERE id=?",
                            new Object[] {id});

            if (rowCount == 1) {
                status = "Student deleted successfully!";
            } else {
                status = "Student not found!";
            }
        } catch (Exception e) {
            status = "Student delete failed!";
            e.printStackTrace();
        }

        return status;
    }
}
