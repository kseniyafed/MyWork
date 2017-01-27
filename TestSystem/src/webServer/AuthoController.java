package webServer;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author intel
 */
class AuthoController extends AbstractDbController {

    public AuthoController() throws IOException {
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        String requestBody=IOUtils.toString(he.getRequestBody(), "UTF-8");
        HashMap<String,String> formValues=parseFromValues(requestBody);
        if(formValues.get("login")!=null && formValues.get("login")!=null)
            System.out.print("login:"+formValues.get("login")+"\n"+"password:"+formValues.get("password"));
    }

    protected String getTemplateFilename() {
        return "Authorization.ftl";
    }

    private HashMap<String, String> parseFromValues(String formValuesEncoded) throws UnsupportedEncodingException {
       String formValuesDecoded= URLDecoder.decode(formValuesEncoded, "UTF-8");
       String[] formValues=formValuesDecoded.split("&");
       HashMap<String,String> result= new HashMap<>();
       for(String formValue: formValues){
           String[] valueParts=formValue.split("=");
           if(valueParts.length==1){
               result.put(valueParts[0],null);
           }else{
                   result.put(valueParts[0],  valueParts[1]);
           }
       }
       return result;
    }
}
