package ru.study.dao.implementations;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.study.dao.interfaces.UserDAO;
import ru.study.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class JpaUserDAO implements UserDAO {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User findUserByEmail(String email) {
        return entityManager.createQuery("select u from User u where email = :email", User.class)
                .setParameter("email", email)
                .getResultList().stream().findAny().orElse(null);
    }

    @Transactional
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }
}
