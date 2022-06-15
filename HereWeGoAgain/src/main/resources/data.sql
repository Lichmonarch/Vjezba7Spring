delete from hardware;
delete from review;

insert into hardware (name, price, type, in_stock)
values ('NVIDIA RTX 3090', 1099, 'GPU', 3);
insert into hardware (name, price, type, in_stock)
values ('MSI B550 TOMAHAWK', 199, 'MBO', 4);
insert into hardware ( name, price, type, in_stock)
values ('AMD RYZEN 9 5950X', 699, 'CPU', 5);
insert into hardware (name, price, type, in_stock)
values ('KINGSTON FURY BEAST 16GB', 99, 'RAM', 10);

insert into review (index, title, body, rating, id_hardware)
values ('001', 'Najbolja grafa na tržištu', 'tekst recenzije je ovdje', 5, '001');
insert into review (index, title, body, rating, id_hardware)
values ('002', 'Dobra ali skupa', 'tekst recenzije je ovdje', 4, '001');
insert into review (index, title, body, rating, id_hardware)
values ('003', 'Pristojan board', 'tekst recenzije je ovdje', 4, '002');
insert into review (index, title, body, rating, id_hardware)
values ('004', 'Moćan ali se ruši...', 'tekst recenzije je ovdje', 3, '003');
insert into review (index, title, body, rating, id_hardware)
values ('005', 'Samo 15.6GB je iskoritivo!!!', 'tekst recenzije je ovdje', 2, '004');

insert into user(id, username, password)
values
    (1, 'user', '$2a$12$h0HcS2QDb/7zPASbLa2GoOTSRP6CWK0oX7pCK.dPjkM6L5N4pNovi'), -- password = user
    (2, 'admin', '$2a$12$INo0nbj40sQrTB7b28KJput/bNltGmFyCfRsUhvy73qcXo5/XdsTG'); -- password = admin
insert into authority (id, authority_name)
values
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_USER');
insert into user_authority (user_id, authority_id)
values
    (1, 2),
    (2, 1);

