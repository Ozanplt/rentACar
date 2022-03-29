package com.etiya.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.etiya.rentACar.entities.Maintenance;

import java.util.List;

@Repository
public interface MaintenanceDao extends JpaRepository <Maintenance,Integer>{

    Boolean existsMaintenanceByCarId(int carId);
    List<Maintenance> getByCarId(int carId);

}