package de.fhws.app.business.config;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import de.fhws.app.business.usermgmt.entity.AppUser;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces("application/fhws")
public class AppUserMessageBodyWriter implements MessageBodyWriter<AppUser> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
                               Annotation[] annotations, MediaType mediaType) {
        return type == AppUser.class;
    }

    @Override
    public long getSize(AppUser user, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType) {
        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
        return 0;
    }

    @Override
    public void writeTo(AppUser user, Class<?> type, Type genericType, Annotation[] annotations,
                        MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
                        OutputStream out) throws IOException, WebApplicationException {

        Writer writer = new PrintWriter(out);
        writer.write(user.getFirstname()+";"+user.getLastname());

        writer.flush();
        writer.close();
    }
}