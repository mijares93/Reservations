package com.alejandro.reservations.data.entity.repository;

import com.alejandro.reservations.data.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mijares on 17/07/2017.
 */
@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

}
