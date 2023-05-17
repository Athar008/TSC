package com.nagraaa.tv.interfaces;

import com.nagraaa.tv.entity.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TvShowRepository extends JpaRepository<TvShow, Long> {
}