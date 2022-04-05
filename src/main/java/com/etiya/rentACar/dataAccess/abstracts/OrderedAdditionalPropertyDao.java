package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.OrderedAdditionalProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderedAdditionalPropertyDao extends JpaRepository<OrderedAdditionalProperty,Integer> {
    List<OrderedAdditionalProperty> getAllIdById(int id);
}
