package webServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;

/**
 *
 * @author intel
 */
public abstract class  AbstractDbController implements HttpHandler {

    protected final Configuration cfg;

    public AbstractDbController() throws IOException {
        cfg = new Configuration(Configuration.VERSION_2_3_25);
        cfg.setDirectoryForTemplateLoading(new File("./tmpl"));
    }

    @Override
    public abstract void handle(HttpExchange he) throws IOException;

    protected void respond(HashMap model, HttpExchange exchange) throws IOException {
        /* String hello="Hello world";
         byte[] bytes=hello.getBytes();
        
         exchange.sendResponseHeaders(200, bytes.length);
         try(final OutputStream os=exchange.getResponseBody()){
         os.write(bytes);
         }*/
        Template tmpl = cfg.getTemplate(getTemplateFilename());
        ByteArrayOutputStream response = new ByteArrayOutputStream();
        try (final OutputStreamWriter out = new OutputStreamWriter(response)) {
            tmpl.process(model, out);
        } catch (TemplateException ex) {
            throw new IOException(ex);
        }
        byte[] bytes = response.toByteArray();
        exchange.sendResponseHeaders(200, bytes.length);
        try (final OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    protected abstract String getTemplateFilename();
    }
