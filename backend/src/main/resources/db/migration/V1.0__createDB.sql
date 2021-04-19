create sequence hibernate_sequence start with 1 increment by 1;

create table category (id bigint not null, name varchar(128), primary key (id));
create table match_stats (id bigint not null, defeats integer not null check (defeats>=0), victories integer not null check (victories>=0), user_username varchar(255) not null, primary key (id));
create table quiz (id bigint not null, answer1 varchar(128), answer2 varchar(128), answer3 varchar(128), answer4 varchar(128), correct_answer_index integer not null check (correct_answer_index>=0 AND correct_answer_index<=3), question varchar(128), sub_category_id bigint not null, primary key (id));
create table sub_category (id bigint not null, name varchar(128), category_id bigint not null, primary key (id));
create table user_roles (user_username varchar(255) not null, roles varchar(255));
create table users (username varchar(255) not null, enabled boolean not null, password varchar(255), primary key (username));

alter table category add constraint UK_46ccwnsi9409t36lurvtyljak unique (name);
alter table quiz add constraint UK_rnmswl6u0o5dwjq14nm5248je unique (question);
alter table match_stats add constraint FK4t4a8c1ploau9vfknu3t6tl7d foreign key (user_username) references users;
alter table quiz add constraint FKwqtq0m711iiym2m781dqr6n4 foreign key (sub_category_id) references sub_category;
alter table sub_category add constraint FKl65dyy5me2ypoyj8ou1hnt64e foreign key (category_id) references category;
alter table user_roles add constraint FKs9rxtuttxq2ln7mtp37s4clce foreign key (user_username) references users;