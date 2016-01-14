/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Publisher;

/**
 *
 * @author Administrator
 */
import BUS.DiadiemBUS;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 *
 * @author Administrator
 */
public class Publisher {
    public static void main(String[] args) {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
 
        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);
 
        //Map creditcard path
        ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/diadiem/*");
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", DiadiemBUS.class.getCanonicalName());
        
        //Map withdraw path
        ServletHolder withdrawServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/withdraw/*");
        withdrawServlet.setInitOrder(0);
//        withdrawServlet.setInitParameter("jersey.config.server.provider.classnames", WithdrawHandler.class.getCanonicalName());
        
        //Map transfer path
        ServletHolder transferServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/transfer/*");
        transferServlet.setInitOrder(0);
//        transferServlet.setInitParameter("jersey.config.server.provider.classnames", TransferHandler.class.getCanonicalName());
 
        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            jettyServer.destroy();
        }
    }
}