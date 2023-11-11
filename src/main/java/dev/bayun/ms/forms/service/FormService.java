package dev.bayun.ms.forms.service;

import dev.bayun.ms.forms.domain.core.Form;
import dev.bayun.ms.forms.repository.FormRepository;
import dev.bayun.ms.forms.security.AuthenticatedPrincipal;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Максим Яськов
 */

@Service
@Transactional
@AllArgsConstructor
public class FormService {

    @Setter
    private FormRepository formRepository;

    public Form create(@NonNull Form candidate) {
        Assert.notNull(candidate, "A candidate must not be null");

        Form form = new Form();

        String principalId = SecurityContextHolder.getContext().getAuthentication().getName();
        form.setAuthorId(principalId);

        Assert.hasText(candidate.getName(), "A candidate.name must have text");
        form.setName(candidate.getName().trim());

        Assert.notNull(candidate.getScope(), "A candidate.scope must not be null");
        form.setScope(candidate.getScope());

        form.setQuestions(new LinkedList<>());
        return formRepository.save(form);
    }

    @NonNull
    public Form findById(String id) {
        Assert.notNull(id, "A id must not be null");
        return formRepository.findById(id).orElseThrow(() -> new FormNotFoundException("with id = " + id));
    }

    public void updateById(String id, Form patch) {
        if (patch == null) {
            return;
        }

        Form form = findById(id);

        boolean changed = false;
        if (patch.getName() != null) {
            form.setName(patch.getName());
            changed = true;
        }

        if (patch.getScope() != null) {
            form.setScope(patch.getScope());
            changed = true;
        }

        if (changed) {
            formRepository.save(form);
        }
    }

    public void deleteById(@NonNull String id) {
        Assert.notNull(id, "A id must not be null");
        formRepository.deleteById(id);
    }

    public List<Form> findAllByAuthorId(String authorId) {
        return formRepository.findAllByAuthorId(authorId);
    }
}
