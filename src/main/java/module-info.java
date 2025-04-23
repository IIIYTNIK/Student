module com.student.student__ {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens com.student.student__ to javafx.fxml;
    exports com.student.student__;
}