package com.etiya.rentACar.dataAccess.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.etiya.rentACar.entities.Car;

import java.util.List;


@Repository
public interface CarDao extends JpaRepository<Car,Integer>{
    List<Car> getByModelYear(double modelYear);
    List<Car> getByCityId(int cityId);
}