package com.example.application.data.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.application.data.entity.Earthquake;


//this code is for filtering the grid with the filtertext
public interface services extends JpaRepository<Earthquake, Integer>{


    @Query("select c from Earthquake c " +
    "where lower(c.Country) like lower(concat('%', :searchTerm, '%')) " +
    "or lower(c.Place) like lower(concat('%', :searchTerm, '%'))")
    static
    List<Earthquake> search(@Param("searchTerm") String searchTerm) {
        // TODO Auto-generated method stub
        return null;
    }
    }

