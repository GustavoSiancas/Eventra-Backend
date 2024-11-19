package com.example.demo.service;

import com.example.demo.controller.client.request.ClientRequest;
import com.example.demo.controller.client.request.ClientUpdateRequest;
import com.example.demo.controller.client.response.ClientResponse;
import com.example.demo.entity.ClientEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.ClientMapper;
import com.example.demo.repository.ClientRepository; // Asegúrate de tener el repositorio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    /*
    public ClientEntity createClient(ClientRequest clientRequest) {
        ClientEntity client = new ClientEntity();
        client.setRealName(clientRequest.realName());
        client.setBirthDate(clientRequest.birthDate());
        client.setDni(clientRequest.dni());
        client.setCountry(clientRequest.country());
        client.setGender(clientRequest.gender());
        client.setDirection(clientRequest.direction());
        client.setPhone(clientRequest.phone());
        client.setUser(userService.getUserById(clientRequest.userId()).get());
        return clientRepository.save(client);
    }*/

    public ClientResponse createClientwUser(UserEntity user) {
        ClientEntity client = new ClientEntity();
        client.setUser(user);
        client=clientRepository.save(client);
        return clientMapper.toClientResponse(client);
    }

    public List<ClientResponse> getAllClients() {
        List<ClientEntity> clients = clientRepository.findAll();
        return clients.stream()
                .map(clientMapper::toClientResponse)  // Mapea cada ClientEntity a ClientResponse
                .collect(Collectors.toList());
    }

    public Optional<ClientEntity> getClientById(Long id) {
        return clientRepository.findById(id);
    }


    public Optional<ClientEntity> getClientByUser(Long id) {
        return clientRepository.findByUser_Id(id);
    }

    public ClientResponse getClientByUserResponse(Long id) {
        ClientEntity client=getClientByUser(id).get();
        return clientMapper.toClientResponse(client);
    }

    /*
    public ClientEntity loginClient(LoginRequest loginRequest) {
        return getClientById(userService.loginUser(loginRequest).getId())
                .orElseThrow(() -> new IllegalArgumentException("Este usuario no es cliente"));
    }*/

    public ClientResponse updateClient(ClientRequest clientRequest) {
        Optional<ClientEntity> actualClient= clientRepository.findById(clientRequest.clientId());
        System.out.println("Cliente buscado");
        if (actualClient.isPresent()){
            ClientEntity client=actualClient.get();
            //update
            client.setRealName(clientRequest.realName());
            client.setBirthDate(clientRequest.birthDate());
            client.setDni(clientRequest.dni());
            client.setCountry(clientRequest.country());
            client.setGender(clientRequest.gender());
            client.setDirection(clientRequest.direction());
            client.setPhone(clientRequest.phone());
            client = clientRepository.save(client);
            System.out.println("Cliente actualisado");
            return clientMapper.toClientResponse(client);
        } else {
            throw new IllegalArgumentException("Este cliente no existe");
        }

    }
}
