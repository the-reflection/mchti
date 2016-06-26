package org.reflection.service;

import org.reflection.model.com.Client;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.ClientNotFoundException;
import org.reflection.repositories.ClientRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("clientService")
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    @Override
    public Client findByCode(String code) {
        return clientRepository.findByCode(code);
    }

    @Transactional
    @Override
    public Client create(Client lookup) {
        return clientRepository.save(lookup);
    }

    @Override
    @Transactional
    public Client findById(BigInteger id) {
        Client client=clientRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return client;
    }

    @Override
    @Transactional(rollbackFor = ClientNotFoundException.class)
    public Client delete(BigInteger id) throws ClientNotFoundException {

        Client client = clientRepository.findOne(id);

        if (client == null) {
            throw new ClientNotFoundException();
        }
        clientRepository.delete(id);
        return client;
    }

    @Override
    @Transactional
    public Iterable<Client> findAll() {
        Iterable<Client> clients=clientRepository.findAll();
        
        for (Client client : clients) {

        //Hibernate.initialize(client.getA());
        //Hibernate.initialize(client.getZs());
        }
        
        return clients;
    }

    @Transactional(rollbackFor = ClientNotFoundException.class)
    @Override
    public Client update(Client updated) throws ClientNotFoundException {

        Client client = clientRepository.findOne(updated.getId());

        if (client == null) {
            throw new ClientNotFoundException();
        }

        BeanUtils.copyProperties(updated, client);
        return clientRepository.save(client);
    }

    @Transactional(rollbackFor = ClientNotFoundException.class)
    @Override
    public Client copy(final Client copied) {

        Client client = new Client();
        BeanUtils.copyProperties(copied, client);
        client.setId(null);

        return clientRepository.save(client);
    }

    @Transactional
    @Override
    public Iterable<Client> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<Client> search(_SearchDTO pageable) {
        return findAll();
    }
}