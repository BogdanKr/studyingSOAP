package com.studying.soap.repository;

import com.studying.soap.entity.Conference;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class ConferenceRepo {

  private static List<Conference> conferences = new ArrayList<>();

  @PostConstruct
  public void initData() {
    conferences.add(new Conference(1L, "It Conference", "Adam Smith"));
    conferences.add(new Conference(2L, "Science today", "Adam Smith"));
    conferences.add(new Conference(3L, "Ecology", "Adam Smith"));
    conferences.add(new Conference(4L, "Your wonderful morning", "Adam Smith"));
    conferences.add(new Conference(5L, "Rest&SOAP", "Valeria Fatova"));
    conferences.add(new Conference(6L, "Spring", "Oleksii Retiznyk"));

  }

  public Optional<Conference> findConferenceById(Long id) {
    return conferences.stream()
        .filter(conference -> conference.getId().equals(id))
        .findFirst();
  }

  public List<Conference> getAllConferences() {
    return conferences;
  }

  public void addConference(Conference conference) {
    conferences.add(conference);
  }

  public void updateConference(Conference updatedConference) {
    Conference conferenceFromDB = conferences.get( updatedConference.getId().intValue() - 1);
    conferenceFromDB.setSubject(updatedConference.getSubject());
    conferenceFromDB.setReaderName(updatedConference.getReaderName());
  }

  public void deleteConference(Conference conference) {
    conferences.remove(conference);
  }

}
