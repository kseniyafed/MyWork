package webServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractTemplateController implements HttpHandler {

    protected final Configuration freeMarkerCfg;

    public AbstractTemplateController() throws IOException {
        freeMarkerCfg = new Configuration(Configuration.VERSION_2_3_25);
        freeMarkerCfg.setDirectoryForTemplateLoading(new File("C:\\Users\\anzem\\Projects\\TestSystem\\TestSystem\\tmpl"));
    }

    @Override
    public abstract void handle(HttpExchange he) throws IOException;

    protected void respond(HashMap<String,Object> model, HttpExchange exchange) throws IOException {

        ByteArrayOutputStream response = new ByteArrayOutputStream();
        try (final OutputStreamWriter out = new OutputStreamWriter(response)) {
            Template tmpl = freeMarkerCfg.getTemplate(getTemplateFilename());
            tmpl.process(model, out);
        } catch (Exception ex) {
            Logger.getLogger(GroupController.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] bytes = response.toByteArray();
        exchange.sendResponseHeaders(200, bytes.length);
        try (final OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
    public HashMap<String, String> parseHtmlQuery(String formValuesEncoded)
            throws UnsupportedEncodingException {
        String formValuesDecoded = URLDecoder.decode(formValuesEncoded, "UTF-8");
        String[] formValues = formValuesDecoded.split("&");
        HashMap<String, String> result = new HashMap<>();
        for (String formValue : formValues) {
            String[] valueParts = formValue.split("=");
            if (valueParts.length == 1) {
                result.put(valueParts[0], null);
            } else {
                result.put(valueParts[0], valueParts[1]);
            }
        }
        return result;
    }

    protected abstract String getTemplateFilename();
}
