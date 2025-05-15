package eu.fayder.restcountries.servlet;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<javax.ws.rs.NotFoundException> {
	
	private static final Logger LOG = LoggerFactory.getLogger(NotFoundExceptionMapper.class);

	@Override
	public Response toResponse(javax.ws.rs.NotFoundException exception) {
		LOG.error(exception.getMessage());
		return Response.status(Status.NOT_FOUND).build();
	}

}
