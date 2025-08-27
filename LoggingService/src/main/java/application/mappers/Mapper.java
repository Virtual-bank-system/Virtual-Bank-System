package application.mappers;

import apis.resources.LogMessage;
import application.models.Logging;
import application.repos.LoggingRepo;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {
    Logging toLogging(LogMessage logMessage);
}
