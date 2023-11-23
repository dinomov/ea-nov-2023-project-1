INSERT INTO `users` (`id`, `email`, `first_name`, `last_name`, `password`,
                     `active`, `deleted`, `city`, `street`, `zip`, `state`,
                     `locked`, `fail_attempts`, `last_fail_attempt_time`)
VALUES (1,'uinan@miu.edu','Umur','Inan',
        '$2a$10$oZpW1LsE9i.HoyWvUhjy/.xrNx/oPJmc8PefV9UGMjzrPVsN7JKdu', /* password */
        1, 0, 'Fairfield','1000 north 4th street','52557', 'Iowa', 0, 0, 0),
       (2,'dinomov@miu.edu','Dilshod','Inomov',
        '$2a$10$oZpW1LsE9i.HoyWvUhjy/.xrNx/oPJmc8PefV9UGMjzrPVsN7JKdu', 1, 0, 'Fairfield','1000 north 4th street','52557', 'Iowa', 0, 0, 0),
       (3,'admin@miu.edu','Admin','Admin',
        '$2a$10$oZpW1LsE9i.HoyWvUhjy/.xrNx/oPJmc8PefV9UGMjzrPVsN7JKdu', 1, 0, 'Fairfield','1000 north 4th street','52557', 'Iowa', 0, 0, 0),
       (4,'mikromov@miu.edu','Mirzokhidjon','Ikromov',
        '$2a$10$oZpW1LsE9i.HoyWvUhjy/.xrNx/oPJmc8PefV9UGMjzrPVsN7JKdu', 1, 0, 'Mountain View','1234 Levin Ave','94040', 'California', 0, 0, 0),
       (5,'john@miu.edu','John','Smith',
    '$2a$10$oZpW1LsE9i.HoyWvUhjy/.xrNx/oPJmc8PefV9UGMjzrPVsN7JKdu', 1, 0, 'Mountain View','1234 Levin Ave','94040', 'California', 0, 0, 0);

INSERT INTO `faculties` (`id`, `title`, `salary`)
VALUES (1,'Assistant Professor of Computer Science',227000),
       (5,'Professor',1200000);

INSERT INTO `students` (`id`, `major`, `gpa`)
VALUES (2,'ComPro',4.0);

INSERT INTO `students` (`id`, `major`, `gpa`)
VALUES (4,'ComPro',4.0);

INSERT INTO `staffs` (`id`, `title`)
VALUES (3,'System administrator');

INSERT INTO `role` (`id`, `role`)
VALUES (1,'ADMIN'), (2,'FACULTY'), (3,'STUDENT');

INSERT INTO user_roles (user_id, roles_id)
VALUES (1, 2);
INSERT INTO user_roles (user_id, roles_id)
VALUES (2, 3);
INSERT INTO user_roles (user_id, roles_id)
VALUES (3, 1);
INSERT INTO user_roles (user_id, roles_id)
VALUES (4, 3);
INSERT INTO user_roles (user_id, roles_id)
VALUES (5, 2);

