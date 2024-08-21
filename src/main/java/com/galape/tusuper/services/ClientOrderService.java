package com.galape.tusuper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.galape.tusuper.repositories.ClientOrderRepository;

@Service
public class ClientOrderService {
    @Autowired
    private ClientOrderRepository clientOrderRepository;
}
