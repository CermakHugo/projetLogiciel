package ProjetGenieLogiciel.isepval.repositories;

import ProjetGenieLogiciel.isepval.models.Message;
import ProjetGenieLogiciel.isepval.models.enums.MessageCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByCategoryOrderByCreatedAt(MessageCategory category);

    List<Message> findByRecipientIdAndCategoryOrderByCreatedAt(long id, MessageCategory category);

    List<Message> findById(long id);
}
