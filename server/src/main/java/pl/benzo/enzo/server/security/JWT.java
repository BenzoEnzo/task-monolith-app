package pl.benzo.enzo.server.security;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.benzo.enzo.server.util.JsonReader;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class JWT {
    private final static Logger LOGGER = LoggerFactory.getLogger(JWT.class);
    private final static String FILEPATH = "/home/devk/Pulpit/IdeaProjects/task-monolith-app/server/src/main/resources/security/secureApp.json";
    private String SECRET;
    public void getValuesFromJson() throws IOException {
       final JsonReader jsonReader = new JsonReader();
       jsonReader.readJsonToMap(FILEPATH);
       final Map map = jsonReader.getJsonMap();
       this.SECRET = (String) map.get("SECRET");
       LOGGER.info(SECRET);
    }
}
