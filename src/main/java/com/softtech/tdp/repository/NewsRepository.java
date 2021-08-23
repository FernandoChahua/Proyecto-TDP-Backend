package com.softtech.tdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.softtech.tdp.model.News;

public interface NewsRepository extends JpaRepository<News, Integer>{
	List<News> findAllByOrderByPublicationDateDesc();
	
	List<News> findByStateOrderByPublicationDateDesc(boolean state);
	
	@Transactional
    @Modifying
    @Query("UPDATE News n " +
            "SET n.state = FALSE WHERE n.idNews = ?1")
    int disableNewsById(Integer idNews);
}
