create database db_samu;
use db_samu;
create table tb_usuarios(
id_usuario int primary key auto_increment,
nome_usuario varchar(50) not null,
login_usuario varchar(30) not null unique,
senha_usuario varchar(30) not null,
perfil_usuario varchar(20) not null
);
create table tb_profissionais(
id_prof int primary key auto_increment,
nome_prof varchar(50) not null,
telefone_prof varchar(30),
email_prof varchar(50),
rua_prof varchar(50),
numero_casa_prof varchar(5),
bairro_prof varchar(50),
cidade_prof varchar(30),
cep_prof varchar(10),
cargo_prof varchar(50) not null
);
create table tb_chamados(
id_chamado int primary key auto_increment,
data_abertura varchar(20),
data_saida varchar(20),
nome_paciente varchar(100),
qth varchar(200) not null,
ndo varchar(100),
situacao varchar(100),
observacao varchar(250),
tarm varchar(100) not null,
mr varchar(100) not null,
cond varchar(100) not null,
enf varchar(100) not null,
dr varchar(100) not null
);

insert into tb_usuarios (nome_usuario,login_usuario,senha_usuario,perfil_usuario)
values ('Adiministrador','admin','samuadmin','Admin');
