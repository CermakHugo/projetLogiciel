package ProjetGenieLogiciel.isepval.repositories;

import ProjetGenieLogiciel.isepval.models.Connection;
import ProjetGenieLogiciel.isepval.models.User;
import ProjetGenieLogiciel.isepval.models.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
    Connection findByUser1IdAndUser2Id(Long user1Id, Long user2Id);

    boolean existsByUser1IdAndUser2IdAndStatus(Long user1Id, Long user2Id, Status status);

    @Query("SELECT e FROM Connection e WHERE (e.user1 = :user OR e.user2 = :user) AND e.status = 'FRIEND'")
    List<Connection> findByUser1OrUser2AndFriend(User user);

    @Query("SELECT e FROM Connection e WHERE e.user2 = :user AND e.status = 'PENDING'")
    List<Connection> findByUser2AndPendingStatus(User user);

    List<Connection> findByUser1InOrUser2InAndStatus(List<User> user1Set, List<User> user2Set, Status status);
}
