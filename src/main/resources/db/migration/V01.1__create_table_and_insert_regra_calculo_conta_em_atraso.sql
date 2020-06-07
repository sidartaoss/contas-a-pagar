-- create table regra_calc_cta_em_atraso
create table regra_calc_cta_em_atraso (
  cd_regra_calc_cta_em_atraso bigint not null,
  dias_em_atraso varchar(255) not null,
  juros_dia decimal(19,4) not null,
  multa decimal(19,2) not null,
  primary key (cd_regra_calc_cta_em_atraso)
) engine=MyISAM;

-- insert into regra_calc_cta_em_atraso
insert into regra_calc_cta_em_atraso (cd_regra_calc_cta_em_atraso, dias_em_atraso, multa, juros_dia) values (1, 'ATE_3_DIAS', 0.02, 0.001);

insert into regra_calc_cta_em_atraso (cd_regra_calc_cta_em_atraso, dias_em_atraso, multa, juros_dia) values (2, 'SUPERIOR_A_3_DIAS', 0.03, 0.002);

insert into regra_calc_cta_em_atraso (cd_regra_calc_cta_em_atraso, dias_em_atraso, multa, juros_dia) values (3, 'SUPERIOR_A_5_DIAS', 0.05, 0.003);
