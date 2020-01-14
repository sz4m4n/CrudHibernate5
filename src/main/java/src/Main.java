package src;

import src.dao.CarDao;
import src.entity.Car;
import src.entity.CarType;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CarDao carDao = new CarDao();

        Car car = new Car( "Lexus", "IS200", LocalDate.of(2003,3,1 ),  CarType.SEDAN);
        Car car1 = new Car( "Toyota", "Yaris", LocalDate.of(2009,3,1 ),  CarType.HATCHBACK);
        Car car2 = new Car( "Toyota", "Avensis", LocalDate.of(2004,3,1 ),  CarType.COMBI);
        Car car3 = new Car( "Kia", "Rio", LocalDate.of(2013,3,1 ),  CarType.HATCHBACK);
        Car car4 = new Car( "Kia", "Stinger", LocalDate.of(2020,3,1 ),  CarType.SPORT);
        carDao.addCar(car);
        carDao.addCar(car1);
        carDao.addCar(car2);
        carDao.addCar(car3);
        carDao.addCar(car4);

        List<Car> carList = carDao.getCarByType(CarType.HATCHBACK);
        carList.forEach(System.out::println);

        carDao.updateCar(2L, new Car(CarType.SPORT));
        Car carShow = carDao.getCar(2L);
        System.out.println("Car type changed " + carShow);
    }
}
