/*<Бородиенко Денис> ИВТ-23*/

package com.student.student__; //пакет Студент__

import javafx.application.Application;
import javafx.fxml.FXMLLoader;         // Для загрузки FXML-файлов
import javafx.scene.Scene;             // Контейнер для содержимого окна
import javafx.stage.Stage;             // Основное окно приложения

import java.io.IOException;            // Для обработки ошибок ввода-вывода


//Главный класс приложения, наследуется от Application
public class Main_Student extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Создаем загрузчик FXML и указываем путь к файлу интерфейса
        FXMLLoader fxmlLoader = new FXMLLoader(
                Main_Student.class.getResource("hello-view.fxml")
        );
        //Создание сцены (окна приложения)
        Scene scene = new Scene(fxmlLoader.load(), 746, 400);
        // Настраиваем главное окно:
        stage.setTitle("Student Table");
        stage.setScene(scene);            // Устанавливаем сцену для отображения
        stage.show();                    // Показываем окно пользователю
    }

    //метод для запуска приложения
    public static void main(String[] args) {
        launch();  // Стандартный метод Application для запуска JavaFX-приложения
    }
}