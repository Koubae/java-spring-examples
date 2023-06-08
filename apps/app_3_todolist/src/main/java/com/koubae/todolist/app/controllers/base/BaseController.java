package com.koubae.todolist.app.controllers.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;


@Controller
abstract public class BaseController {
    @Value("${app.name}")
    protected String APP_NAME;

    static final public String API_V1 = "/api/v1/";

}
