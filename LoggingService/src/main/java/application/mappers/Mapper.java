package application.mappers;

import apis.resources.LogMessage;
import application.models.Logs;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {
    Logs toLogging(LogMessage logMessage);
}
