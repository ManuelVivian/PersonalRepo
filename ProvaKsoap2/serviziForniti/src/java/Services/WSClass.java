/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Manuel
 */
@WebService(serviceName = "WSClass")
public class WSClass {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "sayHi")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hi, " + txt + "! This is a test message.";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getEmail")
    public String getEmail(@WebParam(name = "name") String txt) {
        DBUtil.DButil.getEmail(txt);
        return null;
    }
}
