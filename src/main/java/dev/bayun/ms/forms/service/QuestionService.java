package dev.bayun.ms.forms.service;

import dev.bayun.ms.forms.domain.core.Form;
import dev.bayun.ms.forms.domain.core.Question;
import dev.bayun.ms.forms.domain.question.AbstractRequireableQuestion;
import dev.bayun.ms.forms.domain.question.PatchQuestion;
import dev.bayun.ms.forms.repository.FormRepository;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Максим Яськов
 */

@Service
@Transactional
@AllArgsConstructor
public class QuestionService {

    private FormRepository formRepository;

    @NonNull
    public Question create(String formId, Question candidate) {
        Assert.notNull(formId, "A formId must not be null");
        Assert.notNull(candidate, "A candidate must not be null");

        Form form = findFormById(formId);

        candidate.setId(generateQuestionId(form));

        form.getQuestions().add(candidate);
        formRepository.save(form);

        return candidate;
    }

    @NonNull
    public Question findBy(String formId, String questionId) {
        Assert.notNull(questionId, "A questionId must not be null");

        Form form = findFormById(formId);
        return findByIdFromForm(questionId, form);
    }

    @NonNull
    protected Question findByIdFromForm(String questionId, Form form) {
        Assert.notNull(questionId, "A questionId must not be null");
        Assert.notNull(form, "A form must not be null");
        return form.getQuestions().stream()
                .filter(question -> question.getId().equals(questionId))
                .findFirst().orElseThrow(() -> new QuestionNotFoundException(String.format("with id %s and formId %s", questionId, form.getId())));
    }

    public void updateById(String formId, String questionId, Question patch) {
        if (patch == null) {
            return;
        }

        Form form = findFormById(formId);
        Question question = findByIdFromForm(questionId, form);
        if (!question.getClass().isInstance(patch)) {
            throw new UnsupportedOperationException("Can't change question: types don't match");
        }

        patch.setId(question.getId());
        setQuestionByIdToForm(patch, form);

        formRepository.save(form);
    }

    public void patchById(String formId, String questionId, PatchQuestion patch) {
        if (patch == null) {
            return;
        }

        Form form = findFormById(formId);
        for (Question question : form.getQuestions()) {
            if (!question.getId().equals(questionId)) {
                continue;
            }

            if (patch.getLabel() != null) {
                question.setLabel(patch.getLabel());
            }
            if (patch.getComment() != null) {
                if (patch.getComment().trim().length() == 0) {
                    question.setComment(null);
                } else {
                    question.setComment(patch.getComment());
                }
            }

            if (question instanceof AbstractRequireableQuestion requireableQuestion && patch.getRequired() != null) {
                requireableQuestion.setRequired(patch.getRequired());
            }
        }
        formRepository.save(form);
    }

    public void deleteById(String formId, String questionId) {
        try {
            Form form = findFormById(formId);
            List<Question> questions = form.getQuestions().stream()
                    .filter(question -> !question.getId().equals(questionId))
                    .collect(Collectors.toCollection(LinkedList::new));
            form.setQuestions(questions);

            formRepository.save(form);
        } catch (IllegalArgumentException ignored) {
            // ignored
        }
    }

    protected Form findFormById(String formId) {
        return formRepository.findById(formId)
                .orElseThrow(() -> new FormNotFoundException("with id " + formId));
    }

    protected void setQuestionByIdToForm(Question question, Form form) {
        List<Question> questions = form.getQuestions().stream()
                .filter(q -> !q.getId().equals(question.getId()))
                .collect(Collectors.toCollection(LinkedList::new));
        questions.add(question);
        form.setQuestions(questions);
    }

    protected String generateQuestionId(Form form) {
        return String.valueOf(form.getQuestions().stream()
                .map(Question::getId)
                .mapToInt(Integer::valueOf)
                .max().orElse(0) + 1);
    }
}
