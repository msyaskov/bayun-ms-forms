package dev.bayun.ms.forms.repository;

import dev.bayun.ms.forms.domain.core.Form;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Максим Яськов
 */

@Repository
public interface FormRepository extends MongoRepository<Form, String> {

    List<Form> findAllByAuthorId(String authorId);

}
