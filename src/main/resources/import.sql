insert into Users (id, first_name, last_name, role, email, password) values (1, 'Dmitry', 'Bendik', 'ENGINEER', 'engineer1_mogilev@yopmail.com', '1111');
insert into Users (id, first_name, last_name, role, email, password) values (2, 'Vladislav', 'Palagin', 'EMPLOYEE', 'user1_mogilev@yopmail.com', '$2a$10$vd1QmXh/GKj7j6/sm3dODOoT4FH2vA4DW9wdpRtVofoJlTomG.d5u');
insert into Users (id, first_name, last_name, role, email, password) values (3, 'Alex', 'Smirnov', 'MANAGER', 'manager1_mogilev@yopmail.com', '$2a$10$vd1QmXh/GKj7j6/sm3dODOoT4FH2vA4DW9wdpRtVofoJlTomG.d5u');

insert into Categories(id, name) values (1, 'Application & Services')
insert into Categories(id, name) values (2, 'Benefits & Paper Work')
insert into Categories(id, name) values (3, 'Hardware & Software')
insert into Categories(id, name) values (4, 'Hardware & Software')
insert into Categories(id, name) values (5, 'Security & Access')
insert into Categories(id, name) values (6, 'Application & Services')

insert into Tickets (id, name, description, created_on, desired_resolution_date, assignee_id, owner_id, state, urgency, category_id, approver_id) values (1, 'Project', 'add repo', '2021-10-21', '2021-10-21', 1, 2, 'DRAFT', 'AVERAGE', 1, 1);
insert into Tickets (id, name, description, created_on, desired_resolution_date, assignee_id, owner_id, state, urgency, category_id, approver_id) values (2, 'Homework of String', 'edit repo', '2021-10-21', '2021-12-22', 1, 2, 'DECLINED', 'CRITICAL', 2, 1);
insert into Tickets (id, name, description, created_on, desired_resolution_date, assignee_id, owner_id, state, urgency, category_id, approver_id) values (3, 'Homework of Thread', 'remove repo', '2021-10-21', '2021-11-12', 1, 2, 'NEW', 'LOW', 4, 1);
insert into Tickets (id, name, description, created_on, desired_resolution_date, assignee_id, owner_id, state, urgency, category_id, approver_id) values (4, 'Project of Tickets', 'ready project', '2021-10-21', '2021-08-11', 1, 2, 'DRAFT', 'HIGH', 5, 1);

insert into Histories(id, ticket_id, DATE_ACTION, action, user_id, description) values (1, 1, '2021-10-21', 'CREATE', 1, 'add repo');
insert into Histories(id, ticket_id, DATE_ACTION, action, user_id, description) values (2, 2, '2021-12-22', 'SUBMIT', 1, 'Homework of String');
insert into Histories(id, ticket_id, DATE_ACTION, action, user_id, description) values (3, 3, '2021-11-12', 'CREATE', 1, 'Homework of Thread');
insert into Histories(id, ticket_id, DATE_ACTION, action, user_id, description) values (4, 4, '2021-08-11', 'ASSIGN_TO_ME', 1, 'Project of Tickets');

insert into Comments(id, user_id, text, date, ticket_id) values (1, 1, 'good job', '2022-01-04', 1);
insert into Comments(id, user_id, text, date, ticket_id) values (2, 1, 'not bad', '2022-01-03', 2);
insert into Comments(id, user_id, text, date, ticket_id) values (3, 1, 'change database', '2022-01-05', 3);

--schema.sql  data.sql