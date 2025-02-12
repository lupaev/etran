-- liquibase formatted sql

-- changeset sergej:1
create table SKPP
(
    ID           NUMBER                    not null
        constraint SKPP_PK
            primary key,
    CL_ORG_ID    NUMBER                    not null,
    REPORT_DATE  DATE                      not null,
    ST_OTPR_CODE VARCHAR2(6)               not null,
    DATE_SENT    DATE,
    DATE_ACCEPT  DATE,
    CREATE_DATE  DATE      default SYSDATE not null,
    UPDATE_DATE  DATE      default SYSDATE not null,
    EXT_ID       NUMBER                    not null,
    IS_RUN       NUMBER(1) default 0       not null,
    RESP_TEXT    VARCHAR2(4000)
)
    /

comment on table SKPP is 'Очередь: Суточный клиентский план погрузки'
/

comment on column SKPP.ID is 'Первичный ключ'
/

comment on column SKPP.CL_ORG_ID is 'Идентификатор организации в ЭТРАН. Всегда 43403.'
/

comment on column SKPP.REPORT_DATE is 'Дата СКПП'
/

comment on column SKPP.ST_OTPR_CODE is 'Код станции отправления'
/

comment on column SKPP.DATE_SENT is 'Дата и время отправки Запроса данных по СКПП в ЭТРАН'
/

comment on column SKPP.DATE_ACCEPT is 'Дата и время получения ответа по выбранному СКПП из ЭТРАН'
/

comment on column SKPP.CREATE_DATE is 'Дата создания записи'
/

comment on column SKPP.UPDATE_DATE is 'Дата последнего изменения записи'
/

comment on column SKPP.EXT_ID is 'Внешний id из test_zsmk.evz001_skpp.id'
/

comment on column SKPP.IS_RUN is '(0) - запись готова к обработке, (1) - запись обработана, (2) - получен ответ из ЭТРАН, (-1) - ошибка передачи, (-2) - ошибка получения ответа'
/

comment on column SKPP.RESP_TEXT is 'Текст ответа ЭТРАН'
/

create index SKPP_REPORT_DATE_I
    on SKPP (REPORT_DATE)
    /

create index SKPP_IS_RUN_I
    on SKPP (IS_RUN)
    /

create index SKPP_EXT_ID_I
    on SKPP (EXT_ID)
    /

-- changeset sergej:2
create table SKPP_STRS
(
    ID                   NUMBER               not null
        constraint SKPP_STRS_PK
            primary key,
    SKPP_ID              NUMBER               not null
        constraint SKPP_STRS_SKPP_FK
            references SKPP
            on delete cascade,
    NUMPP                NUMBER,
    CLP_STRING_ID        NUMBER,
    CLAIM_ID             NUMBER,
    RECIP_ORG_ID         NUMBER,
    RECIP_NAME           VARCHAR2(250),
    CLP_TYPE             NUMBER,
    FR_GG_NUMBER         NUMBER,
    FR_GROUP_NAME        VARCHAR2(150),
    RW_NAZN_CODE         VARCHAR2(4),
    RW_NAZN_NAME         VARCHAR2(200),
    ST_NAZN_CODE         VARCHAR2(6),
    ST_NAZN_NAME         VARCHAR2(200),
    PLAN_TONN            NUMBER               not null,
    PLAN_VAG             NUMBER               not null,
    CLAIM_TONN           NUMBER,
    CLAIM_VAG            NUMBER,
    POGR_TONN            NUMBER,
    POGR_VAG             NUMBER,
    RPS_NAME             VARCHAR2(200)        not null,
    RPS_ID               NUMBER               not null,
    SOGL_VAG             NUMBER,
    SOGL_TONN            NUMBER,
    SOGL_VAG_RECIPIENT   NUMBER,
    REASON_DMZI          VARCHAR2(3000),
    CREATE_DATE          DATE default SYSDATE not null,
    UPDATE_DATE          DATE default SYSDATE not null,
    EXT_ID               NUMBER               not null,
    TS_ID                NUMBER,
    CLAIM_NUM            NUMBER,
    STATE                VARCHAR2(20),
    STATE_NAME           VARCHAR2(200),
    CLAIM_OST            NUMBER,
    REASONS              VARCHAR2(300),
    ST_NAZN_SNG_CODE     NUMBER,
    ST_NAZN_SNG_NAME     VARCHAR2(200),
    FR_CODE              NUMBER,
    FR_NAME              VARCHAR2(200),
    TS_NAME              VARCHAR2(200),
    RPS_CODE             NUMBER,
    WAG_OWNER_ID         NUMBER,
    WAG_OWNER_NAME       VARCHAR2(200),
    AUX_SERV             NUMBER,
    TRANS_CNTR_NUM       NUMBER,
    RECIP_OKPO           NUMBER,
    EXTRA_NEXT_DAY       NUMBER,
    PLAN_WAG1824_INTERV  NUMBER,
    PAYER_ORG_ID         NUMBER,
    PAYER_OKPO           NUMBER,
    PAYER_NAME           VARCHAR2(200),
    SOGL_VAG_PORT        NUMBER,
    DELTA_VAG            NUMBER,
    POGR_WAG1824_INTERV  NUMBER,
    POGR_TONN1824_INTERV NUMBER,
    TYPE_DELIV           NUMBER,
    RW_OTP_CODE          VARCHAR2(4),
    RW_OTP_NAME          VARCHAR2(200),
    ST_OTP_CODE          VARCHAR2(6),
    ST_OTP_NAME          VARCHAR2(200),
    LIM_SIGN             NUMBER,
    HIST_SIGN            NUMBER
)
    /

comment on table SKPP_STRS is 'Строки суточного клиентского плана погрузки'
/

comment on column SKPP_STRS.ID is 'Первичный ключ'
/

comment on column SKPP_STRS.SKPP_ID is 'Ссылка на skpp.id'
/

comment on column SKPP_STRS.NUMPP is 'Порядковый номер строки – присваивается при переносе плана в схему XI каждой строке по порядку'
/

comment on column SKPP_STRS.CLP_STRING_ID is 'Идентификатор строки плана в ЭТРАН (получаем в ответе GetSKPP)'
/

comment on column SKPP_STRS.CLAIM_ID is 'Идентификатор заявки ГУ-12'
/

comment on column SKPP_STRS.RECIP_ORG_ID is 'Идентификатор грузополучателя в ЭТРАН'
/

comment on column SKPP_STRS.RECIP_NAME is 'Наименование грузополучателя'
/

comment on column SKPP_STRS.CLP_TYPE is 'Тип сообщения'
/

comment on column SKPP_STRS.FR_GG_NUMBER is 'Код группы груза'
/

comment on column SKPP_STRS.FR_GROUP_NAME is 'Наименование группы груза'
/

comment on column SKPP_STRS.RW_NAZN_CODE is 'Код дороги назначения'
/

comment on column SKPP_STRS.RW_NAZN_NAME is 'Наименование дороги назначения'
/

comment on column SKPP_STRS.ST_NAZN_CODE is 'Код станции назначения'
/

comment on column SKPP_STRS.ST_NAZN_NAME is 'Наименование станции назначения'
/

comment on column SKPP_STRS.PLAN_TONN is 'Плановое количество тонн'
/

comment on column SKPP_STRS.PLAN_VAG is 'Плановое количество вагонов'
/

comment on column SKPP_STRS.CLAIM_TONN is 'Тонны по ГУ-12'
/

comment on column SKPP_STRS.CLAIM_VAG is 'Вагоны по ГУ-12'
/

comment on column SKPP_STRS.POGR_TONN is 'Погружено фактически в отчетные сутки, тонн'
/

comment on column SKPP_STRS.POGR_VAG is 'Погружено фактически в отчетные сутки, вагонов'
/

comment on column SKPP_STRS.RPS_NAME is 'Наименование рода подвижного состава'
/

comment on column SKPP_STRS.RPS_ID is 'Идентификатор рода ПС – укрупненный код'
/

comment on column SKPP_STRS.SOGL_VAG is 'Согласовано к отправке в отчетные сутки, вагонов'
/

comment on column SKPP_STRS.SOGL_TONN is 'Согласовано к отправке в отчетные сутки, тонн'
/

comment on column SKPP_STRS.SOGL_VAG_RECIPIENT is 'Согласовано грузополучателем, вагонов'
/

comment on column SKPP_STRS.REASON_DMZI is 'Причина несогласования'
/

comment on column SKPP_STRS.CREATE_DATE is 'Дата создания записи'
/

comment on column SKPP_STRS.UPDATE_DATE is 'Дата последнего изменения записи'
/

comment on column SKPP_STRS.EXT_ID is 'Внешний id из test_zsmk.evz002_skpp_strs.id'
/

comment on column SKPP_STRS.TS_ID is 'Порт (пункт перевалки) (ссылка на NSI007_PORT.ETRAN_ID)'
/

comment on column SKPP_STRS.CLAIM_NUM is 'Номер заявки'
/

comment on column SKPP_STRS.STATE is 'ID состояния заявки'
/

comment on column SKPP_STRS.STATE_NAME is 'Наименование состояния заявки.'
/

comment on column SKPP_STRS.CLAIM_OST is 'Остаток вагонов по заявке.'
/

comment on column SKPP_STRS.REASONS is 'Причина полного/ частичного отказа от строки плана (после согласования).'
/

comment on column SKPP_STRS.ST_NAZN_SNG_CODE is 'Код станции назначения СНГ'
/

comment on column SKPP_STRS.ST_NAZN_SNG_NAME is 'Наименование станции назначения СНГ'
/

comment on column SKPP_STRS.FR_CODE is 'Код груза'
/

comment on column SKPP_STRS.FR_NAME is 'Наименование груза'
/

comment on column SKPP_STRS.TS_NAME is 'Наименование пункта перевалки'
/

comment on column SKPP_STRS.RPS_CODE is 'Код вида ПС'
/

comment on column SKPP_STRS.WAG_OWNER_ID is 'ИД паспорта (orgPassport) собственника вагона. Заполняется OP011.CNT001_CAROWNER_ID'
/

comment on column SKPP_STRS.WAG_OWNER_NAME is 'Наименование собственника вагона'
/

comment on column SKPP_STRS.AUX_SERV is 'Дополнительная услуга. ИД услуги по НСИ AUX_SERVICES - маршрутизация (один состав, согласование полностью)'
/

comment on column SKPP_STRS.TRANS_CNTR_NUM is 'Не используется на ЗСМК. Номер договора на организацию перевозок грузов по графику. Передается в случае указания по строке СКПП вида дополнительной услуги «КП» (контейнерный поезд) или «Д» (договорной поезд).  Заполнять из ответа GetSKPPReply. НЕ переносить в TEST_ZSMK'
/

comment on column SKPP_STRS.RECIP_OKPO is 'ОКПО грузополучателя.'
/

comment on column SKPP_STRS.EXTRA_NEXT_DAY is 'Флаг согласия на автоматический перенос несогласованных ОАО «РЖД» объемов на следующие сутки(1 - выполнить перенос, 0 - не выполнять перенос ( по умолчанию))'
/

comment on column SKPP_STRS.PLAN_WAG1824_INTERV is 'Не используется на ЗСМК. Количество вагонов, планируемое к погрузке с 18:00 до 24:00 мск времени, ваг'
/

comment on column SKPP_STRS.PAYER_ORG_ID is 'Идентификатор паспорта плательщика (orgPassport)'
/

comment on column SKPP_STRS.PAYER_OKPO is 'ОКПО плательщика.'
/

comment on column SKPP_STRS.PAYER_NAME is 'Наименование плательщика.'
/

comment on column SKPP_STRS.SOGL_VAG_PORT is 'Количество вагонов, согласованное портом'
/

comment on column SKPP_STRS.DELTA_VAG is 'Заявлено минус согласовано. '
/

comment on column SKPP_STRS.POGR_WAG1824_INTERV is 'Количество вагонов, погруженных в интервал с 18 до 24 часов'
/

comment on column SKPP_STRS.POGR_TONN1824_INTERV is 'Вес вагонов,  погруженных в интервал с 18 до 24 часов'
/

comment on column SKPP_STRS.TYPE_DELIV is 'Признак назначения из заявки ГУ-12. SIGN_ID по НСИ DESTINATION_SIGN'
/

comment on column SKPP_STRS.RW_OTP_CODE is 'Код дороги отправления'
/

comment on column SKPP_STRS.RW_OTP_NAME is 'Наименование дороги отправления'
/

comment on column SKPP_STRS.ST_OTP_CODE is 'Код станции отправления'
/

comment on column SKPP_STRS.ST_OTP_NAME is 'Наименование станции отправления'
/

comment on column SKPP_STRS.LIM_SIGN is 'Признак попадания под лимитирующее направление. 1 - Да, 0 - Нет'
/

comment on column SKPP_STRS.HIST_SIGN is 'Признак наличия истории у записи. 1 - Да, 0 - Нет'
/

create index SKPP_STRS_SKPP_FK
    on SKPP_STRS (SKPP_ID)
    /

create index SKPP_STRS_EXT_ID_I
    on SKPP_STRS (EXT_ID)
    /

create index SKPP_STRS_CLP_STRING_ID_I
    on SKPP_STRS (CLP_STRING_ID)
    /

-- changeset sergej:3
create table XML_QUERY_LOG
(
    ID           NUMBER               not null
        constraint XML_QUERY_LOG_PK
            primary key,
    OBJECT_TYPE  VARCHAR2(100)        not null,
    OBJECT_ID    NUMBER               not null,
    CREATE_DATE  DATE default SYSDATE not null,
    UPDATE_DATE  DATE default SYSDATE not null,
    XML_REQUEST  CLOB,
    XML_RESPONSE CLOB
)
    /

comment on table XML_QUERY_LOG is 'Журнал запросов и ответов системы ЭТРАН'
/

comment on column XML_QUERY_LOG.ID is 'Первичный ключ'
/

comment on column XML_QUERY_LOG.OBJECT_TYPE is 'Тип объекта, который ведет журнал запросов и ответов'
/

comment on column XML_QUERY_LOG.OBJECT_ID is 'Внешний ключ объекта, который ведет журнал запросов и ответов'
/

comment on column XML_QUERY_LOG.CREATE_DATE is 'Дата создания записи'
/

comment on column XML_QUERY_LOG.UPDATE_DATE is 'Дата последнего изменения записи'
/

comment on column XML_QUERY_LOG.XML_REQUEST is 'XML запрос в ЭТРАН'
/

comment on column XML_QUERY_LOG.XML_RESPONSE is 'XML ответ из ЭТРАН'
/

create index NI_XML_QUERY_LOG_OBJECT_TYPE_OBJECT_ID
    on XML_QUERY_LOG (OBJECT_TYPE, OBJECT_ID)
    /


