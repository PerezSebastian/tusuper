package com.galape.tusuper.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galape.tusuper.entities.ClientOrder;

@Repository
public interface ClientOrderRepository extends JpaRepository<ClientOrder, String> {
    //public List<ClientOrder> findByDate(); //crear
    //public List<ClientOrder> findByPaymentMethod(); //crear
}
