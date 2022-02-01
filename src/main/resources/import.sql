insert into Users (id, first_name, last_name, role, email, password) values (1, 'Engineer1', 'Engineer1', 'ENGINEER', 'engineer1_mogilev@yopmail.com', '$2a$10$MSl/FEoQbOvE/o6gKLsbQOqAuOCxFh6V6emOkSG/FYuiMqvBl.hDe');
insert into Users (id, first_name, last_name, role, email, password) values (2, 'Engineer2', 'Engineer2', 'ENGINEER', 'engineer2_mogilev@yopmail.com', '$2a$10$MSl/FEoQbOvE/o6gKLsbQOqAuOCxFh6V6emOkSG/FYuiMqvBl.hDe');
insert into Users (id, first_name, last_name, role, email, password) values (3, 'User1', 'User1', 'EMPLOYEE', 'user1_mogilev@yopmail.com', '$2a$10$MSl/FEoQbOvE/o6gKLsbQOqAuOCxFh6V6emOkSG/FYuiMqvBl.hDe');
insert into Users (id, first_name, last_name, role, email, password) values (4, 'User2', 'User2', 'EMPLOYEE', 'user2_mogilev@yopmail.com', '$2a$10$MSl/FEoQbOvE/o6gKLsbQOqAuOCxFh6V6emOkSG/FYuiMqvBl.hDe');
insert into Users (id, first_name, last_name, role, email, password) values (5, 'Manager1', 'Manager1', 'MANAGER', 'manager1_mogilev@yopmail.com', '$2a$10$MSl/FEoQbOvE/o6gKLsbQOqAuOCxFh6V6emOkSG/FYuiMqvBl.hDe');
insert into Users (id, first_name, last_name, role, email, password) values (6, 'Manager2', 'Manager2', 'MANAGER', 'manager2_mogilev@yopmail.com', '$2a$10$MSl/FEoQbOvE/o6gKLsbQOqAuOCxFh6V6emOkSG/FYuiMqvBl.hDe');

insert into Categories(id, name) values (1, 'Application & Services')
insert into Categories(id, name) values (2, 'Benefits & Paper Work')
insert into Categories(id, name) values (3, 'Hardware & Software')
insert into Categories(id, name) values (4, 'People & Management')
insert into Categories(id, name) values (5, 'Security & Access')
insert into Categories(id, name) values (6, 'Application & Services')

insert into Tickets (id, name, description, created_on, desired_resolution_date, assignee_id, owner_id, state, urgency, category_id, approver_id) values (1, 'Project', 'add repo', '2021-10-21', '2021-10-21', 1, 3, 'NEW', 'AVERAGE', 1, 1);
insert into Tickets (id, name, description, created_on, desired_resolution_date, assignee_id, owner_id, state, urgency, category_id, approver_id) values (2, 'Homework of String', 'edit repo', '2021-10-21', '2021-12-22', 2, 4, 'DECLINED', 'CRITICAL', 2, 1);
insert into Tickets (id, name, description, created_on, desired_resolution_date, assignee_id, owner_id, state, urgency, category_id, approver_id) values (3, 'Homework of Thread', 'remove repo', '2021-10-21', '2021-11-12', 1, 5, 'NEW', 'LOW', 4, 1);
insert into Tickets (id, name, description, created_on, desired_resolution_date, assignee_id, owner_id, state, urgency, category_id, approver_id) values (4, 'Homework of Collections', 'ready homework', '2021-10-21', '2021-08-11', 1, 6, 'DRAFT', 'HIGH', 5, 1);
insert into Tickets (id, name, description, created_on, desired_resolution_date, assignee_id, owner_id, state, urgency, category_id, approver_id) values (5, 'Homework of Wrappers', 'ready homework', '2022-01-13', '2022-01-22', 2, 3, 'DRAFT', 'LOW', 1, 2);
insert into Tickets (id, name, description, created_on, desired_resolution_date, assignee_id, owner_id, state, urgency, category_id, approver_id) values (6, 'Homework of Spring', 'fixed homework', '2022-01-13', '2022-01-22', 2, 4, 'NEW', 'LOW', 1, 1);
insert into Tickets (id, name, description, created_on, desired_resolution_date, assignee_id, owner_id, state, urgency, category_id, approver_id) values (7, 'Homework of Hibernate', 'added homework', '2022-01-13', '2022-01-22', 1, 5, 'DRAFT', 'HIGH', 1, 1);
insert into Tickets (id, name, description, created_on, desired_resolution_date, assignee_id, owner_id, state, urgency, category_id, approver_id) values (8, 'Homework of Docker', 'ready homework', '2022-01-15', '2022-01-22', 1, 6, 'NEW', 'LOW', 1, 2);
insert into Tickets (id, name, description, created_on, desired_resolution_date, assignee_id, owner_id, state, urgency, category_id, approver_id) values (9, 'Homework of Cloud', 'ready homework', '2022-01-16', '2022-01-22', 2, 3, 'DONE', 'HIGH', 1, 1);
insert into Tickets (id, name, description, created_on, desired_resolution_date, assignee_id, owner_id, state, urgency, category_id, approver_id) values (10, 'Homework of Servlet', 'ready homework', '2022-01-18', '2022-01-22', 1, 4, 'NEW', 'LOW', 1, 2);

insert into Histories(id, ticket_id, date, action, user_id, description) values (1, 1, '2021-10-21', 'SUBMIT', 1, 'add repo');
insert into Histories(id, ticket_id, date, action, user_id, description) values (2, 2, '2021-12-22', 'DECLINE', 1, 'Homework of String');
insert into Histories(id, ticket_id, date, action, user_id, description) values (3, 3, '2021-11-12', 'SUBMIT', 1, 'Homework of Thread');
insert into Histories(id, ticket_id, date, action, user_id, description) values (4, 4, '2021-08-11', 'CREATE', 1, 'Project of Tickets');
insert into Histories(id, ticket_id, date, action, user_id, description) values (5, 5, '2022-01-13', 'SUBMIT', 1, 'add repo');
insert into Histories(id, ticket_id, date, action, user_id, description) values (6, 6, '2022-01-13', 'CREATE', 1, 'Homework of String');
insert into Histories(id, ticket_id, date, action, user_id, description) values (7, 7, '2022-01-13', 'SUBMIT', 1, 'Homework of Thread');
insert into Histories(id, ticket_id, date, action, user_id, description) values (8, 8, '2022-01-15', 'CREATE', 1, 'Project of Tickets');
insert into Histories(id, ticket_id, date, action, user_id, description) values (9, 9, '2022-01-16', 'DONE', 1, 'Homework of Thread');
insert into Histories(id, ticket_id, date, action, user_id, description) values (10, 10, '2022-01-18', 'SUBMIT', 1, 'Project of Tickets');

insert into Comments(id, user_id, text, date, ticket_id) values (1, 1, 'good job', '2022-01-04', 1);
insert into Comments(id, user_id, text, date, ticket_id) values (2, 1, 'not bad', '2022-01-03', 2);
insert into Comments(id, user_id, text, date, ticket_id) values (3, 1, 'change database', '2022-01-05', 3);

--schema.sql  data.sql