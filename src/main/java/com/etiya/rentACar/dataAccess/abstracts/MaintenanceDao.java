package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceDao extends JpaRepository <Maintenance,Integer>{

    Boolean existsMaintenanceByCarId(int carId);

}