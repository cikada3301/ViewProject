insert into Users (id, first_name, last_name, role_Id, email, password) values (1, 'Dmitry', 'Bendik', 'ENGINEER', 'dima@gmail.com', '1111');
insert into Users (id, first_name, last_name, role_Id, email, password) values (2, 'Vladislav', 'Palagin', 'EMPLOYEE', 'vlad@gmail.com', '2222');

insert into Category(id, name) values (1, 'Application & Services')
insert into Category(id, name) values (2, 'Benefits & Paper Work')
insert into Category(id, name) values (3, 'Hardware & Software')
insert into Category(id, name) values (4, 'Hardware & Software')
insert into Category(id, name) values (5, 'Security & Access')
insert into Category(id, name) values (6, 'Application & Services')

insert into Ticket (id, name, description, created_on, desired_resolution_date, assignee_id, owner_id, state, urgency, category_id, approver_id) values (1, 'Project', 'add repo', 'Java', '2021-10-21', 1, 2, 'DRAFT', 'AVERAGE', 1, 1);
insert into Ticket (id, name, description, created_on, desired_resolution_date, assignee_id, owner_id, state, urgency, category_id, approver_id) values (2, 'Homework of String', 'edit repo', 'Java', '2021-12-22', 1, 2, 'DECLINED', 'CRITICAL', 2, 1);
insert into Ticket (id, name, description, created_on, desired_resolution_date, assignee_id, owner_id, state, urgency, category_id, approver_id) values (3, 'Homework of Thread', 'remove repo', 'Java', '2021-11-12', 1, 2, 'NEW', 'LOW', 4, 1);
insert into Ticket (id, name, description, created_on, desired_resolution_date, assignee_id, owner_id, state, urgency, category_id, approver_id) values (4, 'Project of Tickets', 'ready project', 'Java', '2021-08-11', 1, 2, 'DRAFT', 'HIGH', 5, 1);