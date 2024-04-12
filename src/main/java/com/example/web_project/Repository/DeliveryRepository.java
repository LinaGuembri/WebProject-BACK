package com.example.web_project.Repository;

import com.example.web_project.Entity.Delivery;
import com.example.web_project.Entity.OrderDetail;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends CrudRepository<Delivery, Long > {

}
