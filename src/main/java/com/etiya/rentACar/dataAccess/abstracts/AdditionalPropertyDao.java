package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.AdditionalProperty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalPropertyDao extends JpaRepository<AdditionalProperty,Integer> {
}
