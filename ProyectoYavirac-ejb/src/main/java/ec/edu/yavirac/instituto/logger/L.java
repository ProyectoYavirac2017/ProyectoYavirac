/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.yavirac.instituto.logger;
import java.io.File;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;



public class L {

    public Log level;

    public L() {
        level = LogFactory.getLog(L.class);
    }
    
    public L(String name){
        level = LogFactory.getLog(name);
    }
    
    public L(Class clazz){
        level = LogFactory.getLog(clazz);
    }
    
    public L(Class clazz,String logConfiguration){
        
        level = LogFactory.getLog(clazz);
    }
    
    public void configureClass(Class clazz) {
        level = LogFactory.getLog(clazz);
    }

    public void configureClass(String logName) {
        level = LogFactory.getLog(logName);
    }
    
    public void setXMLConfiguration(String xmlConfiguration) {
        
        File f = new File(xmlConfiguration);
        if (f.exists()) {
            LogManager.resetConfiguration();
            DOMConfigurator.configure(xmlConfiguration);
            
            try {
                level.info("Config Log Path: "+f.getAbsolutePath());
            } catch (Exception e) {
            }
        } else {
            try {
                level = LogFactory.getLog(L.class);
                level.info(
                        "No se encontró el archivo '{}'. Se ha cargado la configuración por defecto."+xmlConfiguration);
            } catch (Exception e) {
            }
        }

    }


    public void reloadDefaultConfiguration() {
        String defaultConfiguration = this.getClass().getResource(
                "/log4j.xml").getPath();
        LogManager.resetConfiguration();
        DOMConfigurator.configure(defaultConfiguration);
    }


}
