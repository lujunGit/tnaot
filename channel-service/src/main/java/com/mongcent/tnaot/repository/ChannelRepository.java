package com.mongcent.tnaot.repository;

import com.mongcent.tnaot.model.Channel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

public interface ChannelRepository extends JpaRepository<Channel, Integer> {
    //自定义方法

    /*@Query(value = "SELECT c.*\n" +
            "FROM (\n" +
            "       SELECT *\n" +
            "       FROM cart_event\n" +
            "       WHERE user_id = ?1 AND (cart_event_type = 3 OR cart_event_type = 2)\n" +
            "       ORDER BY cart_event.created_at DESC\n" +
            "       LIMIT 1\n" +
            "     ) t\n" +
            "  RIGHT JOIN cart_event c ON c.user_id = t.user_id\n" +
            "WHERE c.created_at BETWEEN coalesce(t.created_at, 0) AND 9223372036854775807 AND coalesce(t.id, -1) != c.id\n" +
            "ORDER BY c.created_at ASC", nativeQuery = true)
    Channel getCartEventStreamByUser(Long userId);*/

    @Query("from Channel c where c.name=:name")
    List<Channel> findChannel(@Param("name") String name);

    @Modifying
    @Query("update Channel c set c.create_time=?1, c.update_time=?2 where c.id=?3")
    Integer updateChannel(Date create_time, Date update_time, Integer id);
}
