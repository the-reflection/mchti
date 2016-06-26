package org.reflection.service;

import org.reflection.model.com.Client;
import org.reflection.exception.ClientNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface ClientService {

    public Client findByCode(String code);

    public Client findById(BigInteger id);
    
    public Client create(Client client);
    
    public Client update(Client client) throws ClientNotFoundException;
    
    public Client copy(Client client);
    
    public Client delete(BigInteger id) throws ClientNotFoundException;
   
    public Iterable<Client> search(_SearchDTO pageable);
    
    public Iterable<Client> findAll(_SearchDTO pageable);
    
    public Iterable<Client> findAll();
}