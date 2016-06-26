package org.reflection.service;

import org.reflection.model.com.ClientCompany;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.ClientCompanyNotFoundException;
import org.reflection.repositories.ClientCompanyRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("clientCompanyService")
@Transactional(readOnly = true)
public class ClientCompanyServiceImpl implements ClientCompanyService {

    @Autowired
    private ClientCompanyRepository clientCompanyRepository;

    @Transactional(readOnly = true)
    @Override
    public ClientCompany findByCode(String code) {
        return clientCompanyRepository.findByCode(code);
    }

    @Transactional
    @Override
    public ClientCompany create(ClientCompany lookup) {
        return clientCompanyRepository.save(lookup);
    }

    @Override
    @Transactional
    public ClientCompany findById(BigInteger id) {
        ClientCompany clientCompany=clientCompanyRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return clientCompany;
    }

    @Override
    @Transactional(rollbackFor = ClientCompanyNotFoundException.class)
    public ClientCompany delete(BigInteger id) throws ClientCompanyNotFoundException {

        ClientCompany clientCompany = clientCompanyRepository.findOne(id);

        if (clientCompany == null) {
            throw new ClientCompanyNotFoundException();
        }
        clientCompanyRepository.delete(id);
        return clientCompany;
    }

    @Override
    @Transactional
    public Iterable<ClientCompany> findAll() {
        Iterable<ClientCompany> clientCompanys=clientCompanyRepository.findAll();
        
        for (ClientCompany clientCompany : clientCompanys) {

        //Hibernate.initialize(clientCompany.getA());
        //Hibernate.initialize(clientCompany.getZs());
        }
        
        return clientCompanys;
    }

    @Transactional(rollbackFor = ClientCompanyNotFoundException.class)
    @Override
    public ClientCompany update(ClientCompany updated) throws ClientCompanyNotFoundException {

        ClientCompany clientCompany = clientCompanyRepository.findOne(updated.getId());

        if (clientCompany == null) {
            throw new ClientCompanyNotFoundException();
        }

        BeanUtils.copyProperties(updated, clientCompany);
        return clientCompanyRepository.save(clientCompany);
    }

    @Transactional(rollbackFor = ClientCompanyNotFoundException.class)
    @Override
    public ClientCompany copy(final ClientCompany copied) {

        ClientCompany clientCompany = new ClientCompany();
        BeanUtils.copyProperties(copied, clientCompany);
        clientCompany.setId(null);

        return clientCompanyRepository.save(clientCompany);
    }

    @Transactional
    @Override
    public Iterable<ClientCompany> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<ClientCompany> search(_SearchDTO pageable) {
        return findAll();
    }
}