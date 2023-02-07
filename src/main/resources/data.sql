DELETE FROM `category`;
DELETE FROM `role`;
INSERT INTO `category` (`category_id`, `category_name`) VALUES (1,'BreakFast'), (2,'Lunch'),(3,'Dinner');
INSERT INTO `role`(`role_id`, `role`) VALUES (1,'USER'),(2,'ADMIN');