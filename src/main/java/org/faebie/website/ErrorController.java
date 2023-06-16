package org.faebie.website;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorController {
    private static final Logger log = LogManager.getLogger(ErrorController.class);

    @ExceptionHandler(value = Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exception(final Model model, final Throwable throwable) {
        log.warn("Catching exception");

        log.error(throwable);

        model.addAttribute("err", throwable.getMessage());
        model.addAttribute("exception", throwable);

        return "error";
    }
}
