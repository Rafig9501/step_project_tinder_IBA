package servlets;

import service.RegistrationService;
import utilities.engine.TemplateEngine;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static utilities.constants.LocalFiles.REG_FTL;


public class RegistrationServlet extends HttpServlet {

    TemplateEngine engine;
    RegistrationService reqService;

    public RegistrationServlet(TemplateEngine engine, RegistrationService reqService) {
        this.engine = engine;
        this.reqService = reqService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        reqService.settingEngine(REG_FTL, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        reqService.getParameters(req, resp);
    }
}
