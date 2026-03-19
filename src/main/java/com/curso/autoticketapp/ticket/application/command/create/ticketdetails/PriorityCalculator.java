package com.curso.autoticketapp.ticket.application.command.create.ticketdetails;

import com.curso.autoticketapp.ticket.domain.entity.enums.TicketPriority;
import com.curso.autoticketapp.ticket.domain.entity.enums.TicketSentiment;
import com.curso.autoticketapp.ticket.domain.entity.enums.TicketUrgency;

public class PriorityCalculator {

    public static TicketPriority calculateTicketPriority(TicketSentiment sentiment, TicketUrgency urgency) {
        return switch (sentiment) {
            case NEGATIVE -> switch (urgency) {
                case HIGH -> TicketPriority.CRITIC;
                case MEDIUM -> TicketPriority.HIGH;
                case LOW -> TicketPriority.MEDIUM;
            };
            case NEUTRAL -> switch (urgency) {
                case HIGH -> TicketPriority.HIGH;
                case MEDIUM -> TicketPriority.MEDIUM;
                case LOW -> TicketPriority.LOW;
            };
            case POSITIVE -> switch (urgency) {
                case HIGH -> TicketPriority.MEDIUM;
                case MEDIUM, LOW -> TicketPriority.LOW;
            };
        };

    }
}
