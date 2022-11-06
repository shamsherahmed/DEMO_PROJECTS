package com.shamsid.videouploader.repository;

import com.shamsid.videouploader.domain.Media;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends CrudRepository<Media,Long> {
   // public Media saveMediaInformation(Media media);
    public Media findByMid(String mid);
}
