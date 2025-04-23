package com.student.student__;

// Импорты для работы с коллекциями JavaFX
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.collections.transformation.FilteredList;
//import javafx.collections.transformation.SortedList;
// Импорты для работы с JavaFX FXML
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
// Импорты для работы с JSON
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
// Импорты для работы с файлами
import java.io.File;
import java.io.IOException;
import java.util.List;

public class mainWindowController {
    // Таблица студентов
    @FXML private TableView<Student> tableStudent;
    // Поля ввода
    @FXML private TextField ID_, nameField, groupField, ageField, courseField, gradeField, searchField;
    // Столбцы таблицы
    @FXML private TableColumn<Student, String> nameColumn, groupColumn;
    @FXML private TableColumn<Student, Integer> ageColumn, courseColumn, idColumn;
    @FXML private TableColumn<Student, Double> gradeColumn;
    // Кнопки
    @FXML private Button addButton, editButton, deleteButton, saveButton, loadButton;

    private final ObservableList<Student> studentList = FXCollections.observableArrayList();
    private final ObjectMapper objectMapper = new ObjectMapper();

    //Метод инициализации контроллера.
    @FXML
    public void initialize() {
        // Привязка столбцов к полям класса Student
        idColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("averageGrade"));
        // Устанавливаем список студентов как источник данных для таблицы
        tableStudent.setItems(studentList);
    }

    // Обработчик нажатия кнопки "Добавить".
    @FXML
    private void onAddButtonClick() {
        try {
            Student student = new Student( // Создаем нового студента из данных полей ввода
                    nameField.getText().trim(),
                    Integer.parseInt(ageField.getText().trim()),
                    Integer.parseInt(ID_.getText().trim()),
                    Double.parseDouble(gradeField.getText().trim()),
                    groupField.getText().trim(),
                    Integer.parseInt(courseField.getText().trim())
            );
            studentList.add(student);// Добавляем студента в список
            clearInputFields(); // Очищаем поля ввода
        } catch (NumberFormatException e) {
            showErrorAlert(e.getMessage());// Выводим сообщение об ошибке при неверном формате данных
        }
    }

    // Обработчик нажатия кнопки "Редактировать".
    @FXML
    private void onEditButtonClick() {
        Student selected = tableStudent.getSelectionModel().getSelectedItem();// Получаем выбранного студента
        if (selected != null) {
            Student edited = showStudentDialog(selected);// Открываем диалог редактирования
            if (edited != null) {
                studentList.set(studentList.indexOf(selected), edited);// Обновляем данные студента
            }
        } else {
            showErrorAlert("Выберите студента для редактирования");
        }
    }

    // Обработчик нажатия кнопки "Удалить".
    @FXML
    private void onDeleteButtonClick() {
        Student selected = tableStudent.getSelectionModel().getSelectedItem(); // Получаем выбранного студента
        if (selected != null) {
            studentList.remove(selected);// Удаляем студента из списка
        } else {
            showErrorAlert("Выберите студента для удаления");
        }
    }

    /*
    // Поиск студентов
    @FXML
    private void onSearch() {
        FilteredList<Student> filteredList = new FilteredList<>(studentList, p -> true);
        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            filteredList.setPredicate(student -> {
                if (newVal == null || newVal.isEmpty()) return true;
                String lowerCaseFilter = newVal.toLowerCase();
                return student.getName().toLowerCase().contains(lowerCaseFilter) ||
                        student.getGroup().toLowerCase().contains(lowerCaseFilter);

            });
        });
        SortedList<Student> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableStudent.comparatorProperty());
        tableStudent.setItems(sortedList);
    }
*/
    // Сохранение в JSON
    @FXML
    private void onSaveButtonClick() {
        FileChooser fileChooser = new FileChooser();// Создаем диалог выбора файла
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));// Устанавливаем фильтр для JSON файлов
        File file = fileChooser.showSaveDialog(null);// Показываем диалог сохранения
        if (file != null) {
            try {
                objectMapper.writeValue(file, studentList);
            } catch (IOException e) {
                showErrorAlert("Не удалось сохранить файл");
            }
        }
    }

    // Загрузка из JSON
    @FXML
    private void onLoadButtonClick() {
        FileChooser fileChooser = new FileChooser();// Создаем диалог выбора файла
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));// Устанавливаем фильтр для JSON файлов
        File file = fileChooser.showOpenDialog(null);// Показываем диалог открытия
        if (file != null) {
            try {
                List<Student> loaded = objectMapper.readValue(file, new TypeReference<List<Student>>() {});
                studentList.setAll(loaded);// Обновляем список студентов
            } catch (IOException e) {
                showErrorAlert("Не удалось загрузить файл");
            }
        }
    }

    // Очистка полей ввода
    private void clearInputFields() {
        ID_.clear();
        nameField.clear();
        groupField.clear();
        ageField.clear();
        courseField.clear();
        gradeField.clear();
    }

    // Диалог редактирования
    private Student showStudentDialog(Student student) {
        Dialog<Student> dialog = new Dialog<>();// Создаем новое диалоговое окно
        dialog.setTitle("Редактировать книгу");

        // Создаем поля ввода с текущими значениями студента
        TextField idField = new TextField(String.valueOf(student.getStudentId()));  // Исправлено: getStudentId()
        TextField nameField = new TextField(student.getName());     // Теперь корректно
        TextField groupField = new TextField(student.getGroup());
        TextField ageField = new TextField(String.valueOf(student.getAge()));
        TextField courseField = new TextField(String.valueOf(student.getCourse()));
        TextField gradeField = new TextField(String.valueOf(student.getAverageGrade()));

        // Создаем и настраиваем сетку для расположения элементов
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // Добавляем элементы в сетку
        grid.add(new Label("ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("ФИО:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Группа:"), 0, 2);
        grid.add(groupField, 1, 2);
        grid.add(new Label("Возраст:"), 0, 3);
        grid.add(ageField, 1, 3);
        grid.add(new Label("Курс:"), 0, 4);
        grid.add(courseField, 1, 4);
        grid.add(new Label("Ср. Балл:"), 0, 5);
        grid.add(gradeField, 1, 5);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Обработчик результата диалога
        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                try {
                    int ID = Integer.parseInt(idField.getText().trim());
                    int Age = Integer.parseInt(ageField.getText().trim());
                    float Average = Float.parseFloat(gradeField.getText().trim());
                    int Course = Integer.parseInt(courseField.getText().trim());
                    // Создаем нового студента с обновленными данными
                    return new Student(
                            nameField.getText().trim(),
                            Age,
                            ID,
                            Average,
                            groupField.getText().trim(),
                            Course
                    );
                } catch (NumberFormatException e) {
                    showErrorAlert("Введите корректные значения.");
                    showStudentDialog(student);
                }
            }
            return null;
        });
        // Показываем диалог и возвращаем результат
        return dialog.showAndWait().orElse(null);
    }

    // Показывает диалоговое окно с сообщением об ошибке.
    private void showErrorAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Ошибка");
        alert.setContentText(content);
        alert.showAndWait();
    }

}

