-- Отключение проверок внешних ключей
SET FOREIGN_KEY_CHECKS = 0;

-- Вставка данных в таблицу students
INSERT INTO `students` (`first_name`, `last_name`)
VALUES
    ('John', 'Doe'),
    ('Jane', 'Smith'),
    ('Michael', 'Johnson'),
    ('Emily', 'Brown');

-- Вставка данных в таблицу teachers
INSERT INTO `teachers` (`first_name`, `last_name`)
VALUES
    ('William', 'Jones'),
    ('Sophia', 'Davis');

-- Вставка данных в таблицу subjects
INSERT INTO `subjects` (`name`)
VALUES
    ('Mathematics'),
    ('Science'),
    ('History'),
    ('Literature');

-- Вставка данных в таблицу users
-- Параметр `role` соответствует возможностям авторизации: либо TEACHER, либо STUDENT.
-- Связываем некоторых пользователей с учителями, а некоторых с учениками

INSERT INTO `users` (`username`, `password`, `role`, `teacher_id`, `student_id`)
VALUES
    ('teacher_william', 'password123', 'TEACHER', 1, NULL),
    ('teacher_sophia', 'password456', 'TEACHER', 2, NULL),
    ('student_john', 'password789', 'STUDENT', NULL, 1),
    ('student_jane', 'password012', 'STUDENT', NULL, 2),
    ('student_michael', 'password345', 'STUDENT', NULL, 3),
    ('student_emily', 'password678', 'STUDENT', NULL, 4);

-- Вставка данных в таблицу grades
-- Связываем оценки с определенными студентами, учителями и предметами
INSERT INTO `grades` (`score`, `comment`, `date`, `student_id`, `teacher_id`, `subject_id`)
VALUES
    (85, 'Good job on the test', '2024-11-01', 1, 1, 1),
    (92, 'Excellent understanding', '2024-11-02', 2, 2, 2),
    (78, 'Needs improvement in some areas', '2024-11-03', 3, 1, 3),
    (88, 'Well done on the project', '2024-11-04', 4, 2, 4),
    (90, 'Great performance', '2024-11-05', 1, 1, 2),
    (70, 'Could improve participation', '2024-11-06', 2, 2, 1),
    (95, 'Outstanding work', '2024-11-07', 3, 1, 4),
    (82, 'Solid understanding of concepts', '2024-11-08', 4, 2, 3);

-- Включение проверок внешних ключей
SET FOREIGN_KEY_CHECKS = 1;
