/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.web.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis
 */
public class AuthorizationListener implements PhaseListener {

    private static final long serialVersionUID = 1535710966062595554L;
    private static final Logger LOG = Logger.getLogger(AuthorizationListener.class.getName());

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        String currentPage = facesContext.getViewRoot().getViewId();

        boolean isLoginPage = (currentPage.lastIndexOf("index.xhtml") > -1);
        boolean isRegisterPage = (currentPage.lastIndexOf("register.xhtml") > -1);
        boolean isRecoveryPage = (currentPage.lastIndexOf("recovery.xhtml") > -1);

        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        if (session == null) {
            NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
            nh.handleNavigation(facesContext, null, "loginPage");
        } else {
            Object username = session.getAttribute("username");
            Object admin = session.getAttribute("admin");

            if (!(isLoginPage || isRegisterPage || isRecoveryPage) && (username == null || username.toString().equals(""))) {

                NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
                nh.handleNavigation(facesContext, null, "loginPage");

            } else if (!isLoginPage && admin != null) {

                Boolean Admin = (Boolean) admin;
                HttpServletRequest origRequest
                        = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

                String uri = origRequest.getRequestURI();
                String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                String urlPath = uri.replace(url, "");
                if (Admin) {
                    List<String> urls = obtenerUrlsAdmin();
                    if (!views(urls, urlPath)) {
                        NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
                        nh.handleNavigation(facesContext, null, "menu");
                    }
                    LOG.log(Level.INFO, "El usuario a iniciado sesion", username);
                } else {
                    List<String> urls = obtenerUrlsUsuario();
                    if (!views(urls, urlPath)) {
                        NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
                        nh.handleNavigation(facesContext, null, "menu");
                    }
                    LOG.log(Level.INFO, "El usuario a iniciado sesion", username);
                }
            }
        }
    }

    public List<String> obtenerUrlsAdmin() {
        List<String> urls = new ArrayList<>();
        urls.add("/faces/views/bi.xhtml");
        urls.add("/faces/views/cancelarorden.xhtml");
        urls.add("/faces/views/movimientointerno.xhtml");
        urls.add("/faces/views/bodega.xhtml");
        urls.add("/faces/views/articulo.xhtml");
        urls.add("/faces/views/entrada.xhtml");
        urls.add("/faces/views/salida.xhtml");
        urls.add("/faces/views/recordorden.xhtml");
        urls.add("/faces/views/updateorden.xhtml");
        urls.add("/faces/views/orden.xhtml");
        urls.add("/faces/views/usuarios.xhtml");
        urls.add("/faces/views/tecnico.xhtml");
        urls.add("/faces/views/servicio.xhtml");
        urls.add("/faces/views/producto.xhtml");
        urls.add("/faces/views/repuesto.xhtml");
        urls.add("/faces/views/cliente.xhtml");
        urls.add("/faces/views/perfil.xhtml");
        urls.add("/faces/views/principal.xhtml");
        urls.add("/faces/index.xhtml");
        urls.add("/faces/loginPage");
        urls.add("/");
        return urls;
    }

    public List<String> obtenerUrlsUsuario() {
        List<String> urls = new ArrayList<>();
        urls.add("/faces/views/cancelarorden.xhtml");
        urls.add("/faces/views/movimientointerno.xhtml");
        urls.add("/faces/views/salida.xhtml");
        urls.add("/faces/views/recordorden.xhtml");
        urls.add("/faces/views/updateorden.xhtml");
        urls.add("/faces/views/orden.xhtml");
        urls.add("/faces/views/tecnico.xhtml");
        urls.add("/faces/views/producto.xhtml");
        urls.add("/faces/views/cliente.xhtml");
        urls.add("/faces/views/perfil.xhtml");
        urls.add("/faces/views/principal.xhtml");
        urls.add("/faces/index.xhtml");
        urls.add("/faces/loginPage");
        urls.add("/");
        return urls;
    }

    public Boolean views(List<String> urls, String view) {
        Boolean exito = false;
        for (String url : urls) {
            if (url.equals(view)) {
                exito = true;
                break;
            }
        }
        return exito;
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
