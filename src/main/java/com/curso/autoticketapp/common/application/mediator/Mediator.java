package com.curso.autoticketapp.common.application.mediator;

import com.curso.autoticketapp.common.domain.exception.HandlerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class Mediator {

    private final Map<? extends Class<?>, RequestHandler<?, ?>> requestHandlerMap;

    public Mediator(List<RequestHandler<?, ?>> handlers) {
        this.requestHandlerMap = handlers
                .stream()
                .collect(Collectors.toMap(RequestHandler::getRequestType, Function.identity()));
    }

    public <R, T extends Request<R>> R dispatch(T request) {
        RequestHandler<T, R> handler = (RequestHandler<T, R>) requestHandlerMap.get(request.getClass());

        if (handler == null) {
            log.error("No handler found for request {}", request.getClass());
            throw new HandlerNotFoundException(request.getClass());
        }

        return handler.handle(request);
    }
}
