package com.studying.soap.endpoints;

import com.studying.soap.entity.Conference;
import com.studying.soap.repository.ConferenceRepo;
import io.spring.guides.gs_producing_web_service.GetConferenceRequest;
import io.spring.guides.gs_producing_web_service.GetConferenceResponse;
import io.spring.guides.gs_producing_web_service.ObjectFactory;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ConferenceEndpoint {

  private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

  @Resource
  private ConferenceRepo conferenceRepo;

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getConferenceRequest")
  @ResponsePayload
  public GetConferenceResponse getConference(@RequestPayload GetConferenceRequest request) {
    ObjectFactory objectFactory = new ObjectFactory();
    GetConferenceResponse response = objectFactory.createGetConferenceResponse();

    Optional<Conference> conferenceById = conferenceRepo.findConferenceById(request.getId());
    if (conferenceById.isPresent()) {
      Conference conferenceFromRepo = conferenceById.get();
      io.spring.guides.gs_producing_web_service.Conference conference = objectFactory
          .createConference();
      conference.setId(conferenceFromRepo.getId().intValue());
      conference.setSubject(conferenceFromRepo.getSubject());
      conference.setReaderName(conferenceFromRepo.getReaderName());
      response.setConference(conference);
    }

    return null;
  }

}
