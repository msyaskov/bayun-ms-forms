package dev.bayun.ms.forms.util;

import com.github.f4b6a3.uuid.UuidCreator;

import java.util.UUID;

/**
 * @author Максим Яськов
 */
public class UUIDGenerator {

    public static UUID generateV7() {
        return UuidCreator.getTimeOrderedEpoch();
    }

}
