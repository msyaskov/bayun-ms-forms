package dev.bayun.ms.forms.http;

import lombok.Data;

import java.util.Collection;

/**
 * @author Максим Яськов
 */

@Data
public class RestPage<E> {

    private int total;

    private int page;

    private int count;

    private Collection<E> elements;

    public static <E> RestPage<E> of(int total, int page, Collection<E> elements) {
        RestPage<E> restPage = new RestPage<>();

        restPage.setTotal(total);
        restPage.setPage(page);
        restPage.setCount(elements.size());
        restPage.setElements(elements);

        return restPage;
    }

}
