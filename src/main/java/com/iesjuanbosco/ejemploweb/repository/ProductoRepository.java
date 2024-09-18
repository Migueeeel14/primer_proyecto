package com.iesjuanbosco.ejemploweb.repository;

import com.iesjuanbosco.ejemploweb.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //con esto realizaremos las diversas funciones a la bd
public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
