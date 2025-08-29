package cz.personal.vinegar.dataObjects;

import cz.personal.vinegar.enums.Action;
import cz.personal.vinegar.enums.Sender;
import lombok.Data;

@Data
public class RequestDataItem {
    Sender sender;
    Action action;
    String payload;
}
