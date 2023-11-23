package com.tevology.logistica.models.dao.maestros;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tevology.logistica.models.entity.maestros.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Integer> {
    @Query("select c from Cliente c")
    public Page<Cliente> findAllPageAndSort(Pageable pageable);
}
