package by.hubarevich.servlet;


import by.hubarevich.builder.AbstractVoucherBuilder;
import by.hubarevich.factory.VouchersFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;


/**
 * Created by Anton on 08.01.2016.
 * Main servlet for web-application
 */
@WebServlet("/parseaction")
public class XMLParserServlet extends HttpServlet {

    private final String RADIO_BUTTONS_NAME = "radios";
    private final String RESOURCE = "/WEB-INF/resources/travel-vouchers.xml";
    private final String JSP_PAGE = "/index.jsp";
    private final String VOUCHER = "voucher";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        processRequest(request, response);
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parser = request.getParameter(RADIO_BUTTONS_NAME);
        ServletContext context = getServletContext();
        String resourceUrl = context.getRealPath(RESOURCE);


        VouchersFactory vouchersFactory = new VouchersFactory();
        AbstractVoucherBuilder builder = vouchersFactory.createVouchersBuilder(parser);
        builder.buildSetVouchers(resourceUrl);

        request.setAttribute(VOUCHER, builder);


        RequestDispatcher dispatcher = request
                .getRequestDispatcher(JSP_PAGE);
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }
}
