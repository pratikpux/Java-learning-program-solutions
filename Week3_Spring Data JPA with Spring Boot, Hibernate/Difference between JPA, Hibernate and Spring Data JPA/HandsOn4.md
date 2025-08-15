# Hands-on 4 — Difference between JPA, Hibernate, and Spring Data JPA

## Java Persistence API (JPA)
- JSR 338 Specification for persisting, reading, and managing data from Java objects.
- No concrete implementation — Hibernate is one of its implementations.

## Hibernate
- ORM tool that implements JPA.

## Spring Data JPA
- Abstraction layer over JPA implementation providers like Hibernate.
- Reduces boilerplate code, manages transactions automatically.

---

## Code Comparison

### Hibernate
```java
public Integer addEmployee(Employee employee){
    Session session = factory.openSession();
    Transaction tx = null;
    Integer employeeID = null;

    try {
        tx = session.beginTransaction();
        employeeID = (Integer) session.save(employee); 
        tx.commit();
    } catch (HibernateException e) {
        if (tx != null) tx.rollback();
        e.printStackTrace(); 
    } finally {
        session.close(); 
    }
    return employeeID;
}
