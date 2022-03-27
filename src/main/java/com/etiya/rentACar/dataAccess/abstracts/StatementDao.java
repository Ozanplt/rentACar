package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementDao extends JpaRepository<Statement,Integer> {
}
