package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List < User > getListUsers() {
      TypedQuery < User > query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getCarUser(String model, int series) {
      Query query = sessionFactory.getCurrentSession().createQuery("select user from Car car " +
                      "where car.model = : model  and car.series = : series")
              .setParameter("model", model)
              .setParameter("series", series);

      return (User) query.getSingleResult();
   }
}