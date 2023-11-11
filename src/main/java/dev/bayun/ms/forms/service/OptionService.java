package dev.bayun.ms.forms.service;

import dev.bayun.ms.forms.domain.core.Form;
import dev.bayun.ms.forms.domain.core.Option;
import dev.bayun.ms.forms.domain.core.PatchOption;
import dev.bayun.ms.forms.domain.core.Question;
import dev.bayun.ms.forms.domain.question.AbstractOptionQuestion;
import dev.bayun.ms.forms.repository.FormRepository;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author Максим Яськов
 */

@Service
@Transactional
@AllArgsConstructor
public class OptionService {

    private FormRepository formRepository;

    @NonNull
    public Option create(String formId, String questionId, Option candidate) {
        Assert.notNull(formId, "A formId must not be null");
        Assert.notNull(questionId, "A questionId must not be null");
        Assert.notNull(candidate, "A candidate must not be null");

        Form form = findFormById(formId);
        for (Question question : form.getQuestions()) {
            if (!question.getId().equals(questionId)) {
                continue;
            }

            if (!(question instanceof AbstractOptionQuestion optionQuestion)) {
                throw new UnsupportedOperationException("question isn't a AbstractOptionQuestion");
            }

            Option option = new Option();
            option.setId(generateOptionId(optionQuestion));
            option.setLabel(candidate.getLabel());

            optionQuestion.getOptions().add(option);
            formRepository.save(form);
            return option;
        }

        throw new QuestionNotFoundException("with formId=%s and questionId=%s".formatted(formId, questionId));
    }

    @NonNull
    public Option findBy(String formId, String questionId, String optionId) {
        Assert.notNull(questionId, "A questionId must not be null");
        Assert.notNull(optionId, "A optionId must not be null");

        Form form = findFormById(formId);
        for (Question question : form.getQuestions()) {
            if (!question.getId().equals(questionId)) {
                continue;
            }

            if (!(question instanceof AbstractOptionQuestion optionQuestion)) {
                throw new UnsupportedOperationException("question isn't a AbstractOptionQuestion");
            }

            for (Option option : optionQuestion.getOptions()) {
                if (option.getId().equals(optionId)) {
                    return option;
                }
            }
        }

        throw new OptionNotFoundException("with formId=%s and questionId=%s and optionId=%s".formatted(formId, questionId, optionId));
    }

    public void patchBy(String formId, String questionId, String optionId, PatchOption patch) {
        if (patch == null || patch.getLabel() == null) {
            return;
        }

        Form form = findFormById(formId);
        for (Question question : form.getQuestions()) {
            if (!question.getId().equals(questionId)) {
                continue;
            }

            if (!(question instanceof AbstractOptionQuestion optionQuestion)) {
                throw new UnsupportedOperationException("question isn't a AbstractOptionQuestion");
            }

            for (Option option : optionQuestion.getOptions()) {
                if (option.getId().equals(optionId)) {
                    option.setLabel(patch.getLabel().trim().length() == 0 ? null : patch.getLabel().trim());
                }
            }
        }
    }

    protected Form findFormById(String formId) {
        return formRepository.findById(formId)
                .orElseThrow(() -> new FormNotFoundException("with id " + formId));
    }

    protected String generateOptionId(AbstractOptionQuestion question) {
        return String.valueOf(question.getOptions().stream()
                .map(Option::getId)
                .mapToInt(Integer::valueOf)
                .max().orElse(0) + 1);
    }

    public void deleteById(String formId, String questionId, String optionId) {
        Assert.notNull(questionId, "A questionId must not be null");
        Assert.notNull(optionId, "A optionId must not be null");

        Form form = findFormById(formId);
        for (Question question : form.getQuestions()) {
            if (!question.getId().equals(questionId)) {
                continue;
            }

            if (!(question instanceof AbstractOptionQuestion optionQuestion)) {
                throw new UnsupportedOperationException("question isn't a AbstractOptionQuestion");
            }

            optionQuestion.getOptions().removeIf(option -> option.getId().equals(optionId));
            formRepository.save(form);
        }
    }
}
