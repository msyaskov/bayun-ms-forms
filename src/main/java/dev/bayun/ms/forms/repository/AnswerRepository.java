package dev.bayun.ms.forms.repository;

import dev.bayun.ms.forms.domain.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Максим Яськов
 */

@Repository
public interface AnswerRepository extends MongoRepository<Answer, String> {

    List<Answer> findAllByFormId(String formId);

    Optional<Answer> findByFormIdAndAuthorId(String formId, String authorId);

    boolean existsByFormIdAndAuthorId(String formId, String authorId);

}
