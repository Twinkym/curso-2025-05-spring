package main.java.com.KirgoDev.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ErrorControllerCustom implements ErrorController {

    private static final String PATH ="/error"; // Ruta por defecto para errores en Spring

    @RequestMapping(PATH)
    public String handleError(httpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == httpStatus.NOT_FOUND.value()) {
                // Si el codigo de estado es 404, muestra la pagina personalizada
                return "404"; // Corresponde al nombre del archivo 404.html
            }
        }
        return "error_generico";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
