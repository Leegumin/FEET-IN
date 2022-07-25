--------------------------------------------------------
--  颇老捞 积己凳 - 格夸老-7岿-21-2022   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence SQ_BOARD
--------------------------------------------------------

CREATE SEQUENCE "SQ_BOARD" MINVALUE 1 MAXVALUE 99999 INCREMENT BY 1 START WITH 1;
--------------------------------------------------------
--  DDL for Sequence SQ_CART
--------------------------------------------------------

CREATE SEQUENCE "SQ_CART" MINVALUE 1 MAXVALUE 9999 INCREMENT BY 1 START WITH 1;
--------------------------------------------------------
--  DDL for Sequence SQ_FAQ
--------------------------------------------------------

CREATE SEQUENCE "SQ_FAQ" MINVALUE 1 MAXVALUE 99999999 INCREMENT BY 1 START WITH 1;
--------------------------------------------------------
--  DDL for Sequence SQ_FILES
--------------------------------------------------------

CREATE SEQUENCE "SQ_FILES" MINVALUE 1 MAXVALUE 99999 INCREMENT BY 1 START WITH 1;
--------------------------------------------------------
--  DDL for Sequence SQ_LIKE
--------------------------------------------------------

CREATE SEQUENCE "SQ_LIKE" MINVALUE 1 MAXVALUE 9999 INCREMENT BY 1 START WITH 1;
--------------------------------------------------------
--  DDL for Sequence SQ_ORDER
--------------------------------------------------------

CREATE SEQUENCE "SQ_ORDER" MINVALUE 1 MAXVALUE 9999 INCREMENT BY 1 START WITH 1;
--------------------------------------------------------
--  DDL for Sequence SQ_PRODUCT
--------------------------------------------------------

CREATE SEQUENCE "SQ_PRODUCT" MINVALUE 1 MAXVALUE 9999 INCREMENT BY 1 START WITH 1;
--------------------------------------------------------
--  DDL for Sequence SQ_REVIEW
--------------------------------------------------------

CREATE SEQUENCE "SQ_REVIEW" MINVALUE 1 MAXVALUE 9999 INCREMENT BY 1 START WITH 1;
--------------------------------------------------------
--  DDL for Sequence SQ_ROLES
--------------------------------------------------------

CREATE SEQUENCE "SQ_ROLES" MINVALUE 1 MAXVALUE 99999999 INCREMENT BY 1 START WITH 1;
--------------------------------------------------------
--  DDL for Sequence SQ_TUTORIAL
--------------------------------------------------------

CREATE SEQUENCE "SQ_TUTORIAL" MINVALUE 1 MAXVALUE 9999 INCREMENT BY 1 START WITH 1;
--------------------------------------------------------
--  DDL for Sequence SQ_USER
--------------------------------------------------------

CREATE SEQUENCE "SQ_USER" MINVALUE 1 MAXVALUE 99999999 INCREMENT BY 1 START WITH 1;
--------------------------------------------------------
--  DDL for Sequence SQ_USER_CART
--------------------------------------------------------

CREATE SEQUENCE "SQ_USER_CART" MINVALUE 1 MAXVALUE 10000 INCREMENT BY 1 START WITH 1;
--------------------------------------------------------
--  DDL for Table TB_CART
--------------------------------------------------------

CREATE TABLE "TB_CART" (
    "ID"          NUMBER
    ,"USER_ID"     NUMBER
    ,"PRODUCT_ID"  NUMBER
    ,"QUANTITY"    NUMBER
    ,"SHOSE_SIZE"  NUMBER
    ,"FINAL_PRICE" NUMBER
);
--------------------------------------------------------
--  DDL for Table TB_CATEGORY
--------------------------------------------------------

CREATE TABLE "TB_CATEGORY" (
    "CATEGORY" VARCHAR2(30)
    ,"NO"       VARCHAR2(10)
);
--------------------------------------------------------
--  DDL for Table TB_FAQ
--------------------------------------------------------

CREATE TABLE "TB_FAQ" (
    "ID"          NUMBER
    ,"TITLE"       VARCHAR2(500)
    ,"CONTENT"     VARCHAR2(1000)
    ,"WRITER"      VARCHAR2(255)
    ,"DELETE_YN"   CHAR(1) DEFAULT 'N'
    ,"INSERT_TIME" VARCHAR2(255)
    ,"UPDATE_TIME" VARCHAR2(255)
    ,"DELETE_TIME" VARCHAR2(255)
);
--------------------------------------------------------
--  DDL for Table TB_LIKE
--------------------------------------------------------

CREATE TABLE "TB_LIKE" (
    "LIKE_ID"    NUMBER
    ,"USER_ID"    NUMBER
    ,"PRODUCT_ID" NUMBER
);
--------------------------------------------------------
--  DDL for Table TB_ORDER
--------------------------------------------------------

CREATE TABLE "TB_ORDER" (
    "ID"          NUMBER
    ,"PRODUCT_ID"  NUMBER
    ,"FINAL_PRICE" NUMBER
    ,"SHOSE_SIZE"  NUMBER
    ,"QUANTITY"    NUMBER
    ,"ORDER_TIME"  VARCHAR2(256)
    ,"ORDER_NAME"  VARCHAR2(256)
    ,"ADDRESS"     VARCHAR2(256)
    ,"TEL"         VARCHAR2(256)
    ,"USER_ID"     NUMBER
);
--------------------------------------------------------
--  DDL for Table TB_PRODUCT
--------------------------------------------------------

CREATE TABLE "TB_PRODUCT" (
    "ID"          NUMBER
    ,"TITLE"       VARCHAR2(100)
    ,"DESCRIPTION" VARCHAR2(500)
    ,"PRICE"       NUMBER
    ,"DISCOUNT"    NUMBER
    ,"SALE_YN"     CHAR(1)
    ,"DELETE_YN"   CHAR(1) DEFAULT 'N'
    ,"INSERT_TIME" VARCHAR2(20)
    ,"UPDATE_TIME" VARCHAR2(20)
    ,"DELETE_TIME" VARCHAR2(20)
    ,"PERCENT"     NUMBER
    ,"CATEGORY"    VARCHAR2(10)
    ,"NAME"        VARCHAR2(1000)
    ,"TYPE"        VARCHAR2(1000)
    ,"DATA"        BLOB
    ,"MODEL"       VARCHAR2(100)
);
--------------------------------------------------------
--  DDL for Table TB_REVIEW
--------------------------------------------------------

CREATE TABLE "TB_REVIEW" (
    "ID"          NUMBER
    ,"REVIEW_ID"   NUMBER
    ,"NAME"        VARCHAR2(1000)
    ,"WRITER"      VARCHAR2(100)
    ,"CONTENT"     VARCHAR2(1000)
    ,"TITLE"       VARCHAR2(100)
    ,"INSERT_TIME" VARCHAR2(255)
    ,"UPDATE_TIME" VARCHAR2(255)
    ,"DELETE_TIME" VARCHAR2(255)
    ,"DELETE_YN"   CHAR(1) DEFAULT 'N'
    ,"TYPE"        VARCHAR2(1000)
    ,"DATA"        BLOB
    ,"STAR_NUM"    NUMBER
);
--------------------------------------------------------
--  DDL for Table TB_ROLES
--------------------------------------------------------

CREATE TABLE "TB_ROLES" (
    "ID"   NUMBER
    ,"NAME" VARCHAR2(1000)
);
--------------------------------------------------------
--  DDL for Table TB_USER
--------------------------------------------------------

CREATE TABLE "TB_USER" (
    "ID"          NUMBER
    ,"EMAIL"       VARCHAR2(1000)
    ,"PASSWORD"    VARCHAR2(1000)
    ,"USERNAME"    VARCHAR2(1000)
    ,"ROLE"        VARCHAR2(1000)
    ,"NAME"        VARCHAR2(1000)
    ,"ADDRESS"     VARCHAR2(1000)
    ,"PHONE"       VARCHAR2(1000)
    ,"DELETE_YN"   CHAR(1) DEFAULT 'N'
    ,"INSERT_TIME" VARCHAR2(1000)
    ,"UPDATE_TIME" VARCHAR2(1000)
    ,"DELETE_TIME" VARCHAR2(1000)
);
--------------------------------------------------------
--  Constraints for Table TB_CART
--------------------------------------------------------

ALTER TABLE "TB_CART" ADD PRIMARY KEY ( "ID" );
--------------------------------------------------------
--  Constraints for Table TB_FAQ
--------------------------------------------------------

ALTER TABLE "TB_FAQ" MODIFY (
    "ID"
        NOT NULL ENABLE
);
--------------------------------------------------------
--  Constraints for Table TB_LIKE
--------------------------------------------------------

ALTER TABLE "TB_LIKE" ADD CONSTRAINT "PK_FAVORITE_ID" PRIMARY KEY ( "LIKE_ID" );
--------------------------------------------------------
--  Constraints for Table TB_ORDER
--------------------------------------------------------

ALTER TABLE "TB_ORDER" ADD PRIMARY KEY ( "ID" );
--------------------------------------------------------
--  Constraints for Table TB_PRODUCT
--------------------------------------------------------

ALTER TABLE "TB_PRODUCT" ADD CONSTRAINT "PK_ID" PRIMARY KEY ( "ID" );
--------------------------------------------------------
--  Constraints for Table TB_REVIEW
--------------------------------------------------------

ALTER TABLE "TB_REVIEW" ADD CONSTRAINT "PK_REVIEW_ID" PRIMARY KEY ( "REVIEW_ID" );

ALTER TABLE "TB_REVIEW" MODIFY (
    "ID"
        NOT NULL ENABLE
);
--------------------------------------------------------
--  Constraints for Table TB_ROLES
--------------------------------------------------------

ALTER TABLE "TB_ROLES" ADD CONSTRAINT "PK_ROLES_ID" PRIMARY KEY ( "ID" );
--------------------------------------------------------
--  Constraints for Table TB_USER
--------------------------------------------------------

ALTER TABLE "TB_USER" ADD CONSTRAINT "PK_USER_ID" PRIMARY KEY ( "ID" );

ALTER TABLE "TB_USER" ADD CONSTRAINT "UK_USER_USERID" UNIQUE ( "USERNAME" );

ALTER TABLE "TB_USER" ADD CONSTRAINT "UK_USER_EMAIL" UNIQUE ( "EMAIL" );
--------------------------------------------------------
--  Ref Constraints for Table TB_CART
--------------------------------------------------------

ALTER TABLE "TB_CART"
    ADD CONSTRAINT "FK_CART_PRODUCT" FOREIGN KEY ( "PRODUCT_ID" )
        REFERENCES "TB_PRODUCT" ( "ID" )
            ON DELETE CASCADE
    ENABLE;

ALTER TABLE "TB_CART"
    ADD CONSTRAINT "FK_CART_USER" FOREIGN KEY ( "USER_ID" )
        REFERENCES "TB_USER" ( "ID" )
            ON DELETE CASCADE
    ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table TB_LIKE
--------------------------------------------------------

ALTER TABLE "TB_LIKE"
    ADD CONSTRAINT "FK_PRODUCT_ID_FAVORITE" FOREIGN KEY ( "PRODUCT_ID" )
        REFERENCES "TB_PRODUCT" ( "ID" )
            ON DELETE CASCADE
    ENABLE;

ALTER TABLE "TB_LIKE"
    ADD CONSTRAINT "FK_USER_ID_FAVORITE" FOREIGN KEY ( "USER_ID" )
        REFERENCES "TB_USER" ( "ID" )
            ON DELETE CASCADE
    ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table TB_ORDER
--------------------------------------------------------

ALTER TABLE "TB_ORDER"
    ADD CONSTRAINT "FK_ORDER_USER" FOREIGN KEY ( "USER_ID" )
        REFERENCES "TB_USER" ( "ID" )
            ON DELETE CASCADE
    ENABLE;

ALTER TABLE "TB_ORDER"
    ADD CONSTRAINT "FK_PRODUCT_ID_ORDER" FOREIGN KEY ( "PRODUCT_ID" )
        REFERENCES "TB_PRODUCT" ( "ID" )
            ON DELETE CASCADE
    ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table TB_REVIEW
--------------------------------------------------------

ALTER TABLE "TB_REVIEW"
    ADD CONSTRAINT "FK_REVIEW_ID" FOREIGN KEY ( "ID" )
        REFERENCES "TB_PRODUCT" ( "ID" )
            ON DELETE CASCADE
    ENABLE;