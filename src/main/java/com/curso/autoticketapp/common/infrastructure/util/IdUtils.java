package com.curso.autoticketapp.common.infrastructure.util;

import com.curso.autoticketapp.common.domain.constants.TicketConstants;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class IdUtils {

    public static UUID getUUID() {
        return UUID.randomUUID();
    }

    public static String getAlphanumericPublicId() {
        return RandomStringUtils.secure().nextAlphanumeric(TicketConstants.PUBLIC_ID_LENGTH).toUpperCase();
    }

}
