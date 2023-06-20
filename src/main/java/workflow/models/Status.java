package workflow.models;

import java.util.List;

public enum Status {

    Scheduled("Заплановано"),
    InProgress("В процесі виконання"),
    Completed("Успішно завершена"),
    Suspended("Призупинена"),
    Postponed("Відкладена"),
    Cancelled("Скасована"),
    Frozen("Заморожена"),
    Overdue("Просрочена");

    private final String ukrainianValue;

    Status(String ukrainianValue) {
        this.ukrainianValue = ukrainianValue;
    }

    public String getValue() {
        return ukrainianValue;
    }

}
