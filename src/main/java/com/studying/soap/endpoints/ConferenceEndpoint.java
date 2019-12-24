package com.studying.soap.endpoints;

import com.studying.soap.entity.Conference;
import com.studying.soap.repository.ConferenceRepo;
import io.spring.guides.gs_producing_web_service.GetConferenceRequest;
import io.spring.guides.gs_producing_web_service.GetConferenceResponse;
import io.spring.guides.gs_producing_web_service.ObjectFactory;
import java.util.Optional;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ConferenceEndpoint {
  private Logger logger = LoggerFactory.getLogger(ConferenceEndpoint.class);

  private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

  @Resource
  private ConferenceRepo conferenceRepo;

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getConferenceRequest")
  @ResponsePayload
  public GetConferenceResponse getConference(@RequestPayload GetConferenceRequest request) {
    ObjectFactory objectFactory = new ObjectFactory();
    GetConferenceResponse response = objectFactory.createGetConferenceResponse();
    logger.info("Endpoint working!");

    Optional<Conference> conferenceById = conferenceRepo.findConferenceById((long) request.getId());
    if (conferenceById.isPresent()) {
      Conference conferenceFromRepo = conferenceById.get();
      logger.info("Find conference " + conferenceFromRepo);
      io.spring.guides.gs_producing_web_service.Conference conference = objectFactory
          .createConference();
      conference.setId(conferenceFromRepo.getId().intValue());
      conference.setSubject(conferenceFromRepo.getSubject());
      conference.setReaderName(conferenceFromRepo.getReaderName());
      response.setConference(conference);
      return response;

    }
    logger.error("Something go wrong, entity not present");

    return null;
  }

}
