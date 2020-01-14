package src.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mark;
    private String model;

    @Column(name = "production_year")
    private LocalDate productionYear;

    @Transient
    private long age;

    @Column(name = "car_type")
    @Enumerated(EnumType.ORDINAL)
    private CarType carType;

    public Car() {}

    public Car( String mark, String model, LocalDate productionYear, CarType carType) {
        this.mark = mark;
        this.model = model;
        this.productionYear = productionYear;
        this.carType = carType;
    }

    public Car(CarType carType) {
        this.carType = carType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(LocalDate productionYear) {
        this.productionYear = productionYear;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    @PostLoad
    private void calculateAge() {
        age = ChronoUnit.YEARS.between(productionYear, LocalDate.now());
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", productionYear=" + productionYear +
                ", age=" + age +
                ", carType=" + carType +
                '}';
    }
}