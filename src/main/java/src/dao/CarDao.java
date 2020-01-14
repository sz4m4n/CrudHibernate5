package src.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import src.config.HibernateConfig;
import src.entity.Car;
import src.entity.CarType;

import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CarDao {

    public Car getCar(Long id){
        Session session = HibernateConfig.INSTANCE.getSessionFactory().openSession();
        Car car = session.get(Car.class, id);
        session.close();
        return car;
    }

    public void addCar (Car car){
        Session session = HibernateConfig.INSTANCE.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.save(car);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateCar(Long id, Car newCar){
        Session session = HibernateConfig.INSTANCE.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Car car = session.find(Car.class, id);
            car.setCarType(newCar.getCarType());
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteCar (Long id){
        Session session = HibernateConfig.INSTANCE.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Car car = session.find(Car.class, id);
            session.delete(car);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Car> getCarByType(CarType carType){
        Session session = HibernateConfig.INSTANCE.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        List<BigInteger> resultList= null;
        try {
            transaction.begin();
            String sql = "SELECT id FROM cars WHERE cars.car_type = ?";
            Query query = session.createNativeQuery(sql);
            query.setParameter(1, carType.ordinal());
            resultList = query.getResultList();
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        List<Car> carList = new ArrayList<>();
        resultList.forEach(id -> carList.add(getCar(id.longValue())));
        return carList;
    }

}
