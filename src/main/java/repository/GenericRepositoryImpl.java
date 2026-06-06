package repository;

import config.ORMConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Attendance;
import model.Employee;

import java.util.ArrayList;
import java.util.List;

public class GenericRepositoryImpl<T> implements Repository<T>{
    private static GenericRepositoryImpl instance;
    private GenericRepositoryImpl() {}
    public static synchronized GenericRepositoryImpl getInstance() {
        if (instance == null)
            instance = new GenericRepositoryImpl();
        return instance;
    }
    @Override
    public void add(T t) {
        EntityManager em = ORMConfig.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(t);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    @Override
    public void update(T t) {
        EntityManager em = ORMConfig.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(t);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    @Override
    public void delete(String id, Class<T> clazz) {
        EntityManager em = ORMConfig.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            T t = em.find(clazz, id);
            if (t != null) em.remove(t);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    @Override
    public T findById(String id, Class<T> clazz) {
        EntityManager em = ORMConfig.emf.createEntityManager();
        try {
            return em.find(clazz, id);
        } finally {
            em.close();
        }
    }
    @Override
    public List<T> findAll(Class<T> clazz) {
        EntityManager em = ORMConfig.emf.createEntityManager();
        try {
            TypedQuery<T> query = em.createQuery("SELECT e FROM " + clazz.getSimpleName() + " e", clazz);
            return query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }
    public List<Employee> findAllEmployees() {
        EntityManager em = ORMConfig.emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT DISTINCT e FROM Employee e LEFT JOIN FETCH e.department LEFT JOIN FETCH e.positions", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    public List<Attendance> findAllAttendance(String empId) {
        EntityManager em = ORMConfig.emf.createEntityManager();
        try {
            TypedQuery<Attendance> query = em.createQuery("SELECT a FROM Attendance a WHERE a.employeeId = :empId", Attendance.class);
            query.setParameter("empId", empId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}