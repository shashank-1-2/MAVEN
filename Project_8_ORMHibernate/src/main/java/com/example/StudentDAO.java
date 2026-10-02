package com.example;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class StudentDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Student> findAll() {
        return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    public Optional<Student> findById(int id) {
        return Optional.ofNullable(em.find(Student.class, id));
    }

    public List<Student> findByGradeGreaterThanEqual(double grade) {
        return em.createQuery("SELECT s FROM Student s WHERE s.grade >= :grade", Student.class)
                .setParameter("grade", grade)
                .getResultList();
    }

    public Student save(Student student) {
        if (student.getId() == 0) {
            em.persist(student);          // INSERT, id is generated
            return student;
        }
        return em.merge(student);         // UPDATE
    }

    public boolean existsById(int id) {
        return em.find(Student.class, id) != null;
    }

    public void deleteById(int id) {
        Student student = em.find(Student.class, id);
        if (student != null) {
            em.remove(student);
        }
    }
}