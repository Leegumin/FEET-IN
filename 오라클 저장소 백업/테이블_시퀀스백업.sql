--------------------------------------------------------
--  颇老捞 积己凳 - 格夸老-7岿-21-2022   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence SQ_BOARD
--------------------------------------------------------

   CREATE SEQUENCE  "SQ_BOARD"  MINVALUE 1 MAXVALUE 99999 INCREMENT BY 1 START WITH 56 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL
--------------------------------------------------------
--  DDL for Sequence SQ_CART
--------------------------------------------------------

   CREATE SEQUENCE  "SQ_CART"  MINVALUE 1 MAXVALUE 9999 INCREMENT BY 1 START WITH 157 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL
--------------------------------------------------------
--  DDL for Sequence SQ_FAQ
--------------------------------------------------------

   CREATE SEQUENCE  "SQ_FAQ"  MINVALUE 1 MAXVALUE 99999999 INCREMENT BY 1 START WITH 180 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL
--------------------------------------------------------
--  DDL for Sequence SQ_FILES
--------------------------------------------------------

   CREATE SEQUENCE  "SQ_FILES"  MINVALUE 1 MAXVALUE 99999 INCREMENT BY 1 START WITH 326 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL
--------------------------------------------------------
--  DDL for Sequence SQ_LIKE
--------------------------------------------------------

   CREATE SEQUENCE  "SQ_LIKE"  MINVALUE 1 MAXVALUE 9999 INCREMENT BY 1 START WITH 301 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL
--------------------------------------------------------
--  DDL for Sequence SQ_ORDER
--------------------------------------------------------

   CREATE SEQUENCE  "SQ_ORDER"  MINVALUE 1 MAXVALUE 9999 INCREMENT BY 1 START WITH 199 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL
--------------------------------------------------------
--  DDL for Sequence SQ_PRODUCT
--------------------------------------------------------

   CREATE SEQUENCE  "SQ_PRODUCT"  MINVALUE 1 MAXVALUE 9999 INCREMENT BY 1 START WITH 357 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL
--------------------------------------------------------
--  DDL for Sequence SQ_REVIEW
--------------------------------------------------------

   CREATE SEQUENCE  "SQ_REVIEW"  MINVALUE 1 MAXVALUE 9999 INCREMENT BY 1 START WITH 9 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL
--------------------------------------------------------
--  DDL for Sequence SQ_ROLES
--------------------------------------------------------

   CREATE SEQUENCE  "SQ_ROLES"  MINVALUE 1 MAXVALUE 99999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL
--------------------------------------------------------
--  DDL for Sequence SQ_TUTORIAL
--------------------------------------------------------

   CREATE SEQUENCE  "SQ_TUTORIAL"  MINVALUE 1 MAXVALUE 9999 INCREMENT BY 1 START WITH 11 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL
--------------------------------------------------------
--  DDL for Sequence SQ_USER
--------------------------------------------------------

   CREATE SEQUENCE  "SQ_USER"  MINVALUE 1 MAXVALUE 99999999 INCREMENT BY 1 START WITH 201 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL
--------------------------------------------------------
--  DDL for Sequence SQ_USER_CART
--------------------------------------------------------

   CREATE SEQUENCE  "SQ_USER_CART"  MINVALUE 1 MAXVALUE 10000 INCREMENT BY 1 START WITH 2 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL
--------------------------------------------------------
--  DDL for Table TB_CART
--------------------------------------------------------

  CREATE TABLE "TB_CART" ("ID" NUMBER, "USER_ID" NUMBER, "PRODUCT_ID" NUMBER, "QUANTITY" NUMBER, "SHOSE_SIZE" NUMBER, "FINAL_PRICE" NUMBER)  DEFAULT COLLATION "USING_NLS_COMP"
--------------------------------------------------------
--  DDL for Table TB_CATEGORY
--------------------------------------------------------

  CREATE TABLE "TB_CATEGORY" ("CATEGORY" VARCHAR2(30) COLLATE "USING_NLS_COMP", "NO" VARCHAR2(10) COLLATE "USING_NLS_COMP")  DEFAULT COLLATION "USING_NLS_COMP"
--------------------------------------------------------
--  DDL for Table TB_FAQ
--------------------------------------------------------

  CREATE TABLE "TB_FAQ" ("ID" NUMBER, "TITLE" VARCHAR2(500) COLLATE "USING_NLS_COMP", "CONTENT" VARCHAR2(1000) COLLATE "USING_NLS_COMP", "WRITER" VARCHAR2(255) COLLATE "USING_NLS_COMP", "DELETE_YN" CHAR(1) COLLATE "USING_NLS_COMP" DEFAULT 'N', "INSERT_TIME" VARCHAR2(255) COLLATE "USING_NLS_COMP", "UPDATE_TIME" VARCHAR2(255) COLLATE "USING_NLS_COMP", "DELETE_TIME" VARCHAR2(255) COLLATE "USING_NLS_COMP")  DEFAULT COLLATION "USING_NLS_COMP"
--------------------------------------------------------
--  DDL for Table TB_LIKE
--------------------------------------------------------

  CREATE TABLE "TB_LIKE" ("LIKE_ID" NUMBER, "USER_ID" NUMBER, "PRODUCT_ID" NUMBER)  DEFAULT COLLATION "USING_NLS_COMP"
--------------------------------------------------------
--  DDL for Table TB_ORDER
--------------------------------------------------------

  CREATE TABLE "TB_ORDER" ("ID" NUMBER, "PRODUCT_ID" NUMBER, "FINAL_PRICE" NUMBER, "SHOSE_SIZE" NUMBER, "QUANTITY" NUMBER, "ORDER_TIME" VARCHAR2(256) COLLATE "USING_NLS_COMP", "ORDER_NAME" VARCHAR2(256) COLLATE "USING_NLS_COMP", "ADDRESS" VARCHAR2(256) COLLATE "USING_NLS_COMP", "TEL" VARCHAR2(256) COLLATE "USING_NLS_COMP", "USER_ID" NUMBER)  DEFAULT COLLATION "USING_NLS_COMP"
--------------------------------------------------------
--  DDL for Table TB_PRODUCT
--------------------------------------------------------

  CREATE TABLE "TB_PRODUCT" ("ID" NUMBER, "TITLE" VARCHAR2(100) COLLATE "USING_NLS_COMP", "DESCRIPTION" VARCHAR2(500) COLLATE "USING_NLS_COMP", "PRICE" NUMBER, "DISCOUNT" NUMBER, "SALE_YN" CHAR(1) COLLATE "USING_NLS_COMP", "DELETE_YN" CHAR(1) COLLATE "USING_NLS_COMP" DEFAULT 'N', "INSERT_TIME" VARCHAR2(20) COLLATE "USING_NLS_COMP", "UPDATE_TIME" VARCHAR2(20) COLLATE "USING_NLS_COMP", "DELETE_TIME" VARCHAR2(20) COLLATE "USING_NLS_COMP", "PERCENT" NUMBER, "CATEGORY" VARCHAR2(10) COLLATE "USING_NLS_COMP", "NAME" VARCHAR2(1000) COLLATE "USING_NLS_COMP", "TYPE" VARCHAR2(1000) COLLATE "USING_NLS_COMP", "DATA" BLOB, "MODEL" VARCHAR2(100) COLLATE "USING_NLS_COMP")  DEFAULT COLLATION "USING_NLS_COMP"
--------------------------------------------------------
--  DDL for Table TB_REVIEW
--------------------------------------------------------

  CREATE TABLE "TB_REVIEW" ("ID" NUMBER, "REVIEW_ID" NUMBER, "NAME" VARCHAR2(1000) COLLATE "USING_NLS_COMP", "WRITER" VARCHAR2(100) COLLATE "USING_NLS_COMP", "CONTENT" VARCHAR2(1000) COLLATE "USING_NLS_COMP", "TITLE" VARCHAR2(100) COLLATE "USING_NLS_COMP", "INSERT_TIME" VARCHAR2(255) COLLATE "USING_NLS_COMP", "UPDATE_TIME" VARCHAR2(255) COLLATE "USING_NLS_COMP", "DELETE_TIME" VARCHAR2(255) COLLATE "USING_NLS_COMP", "DELETE_YN" CHAR(1) COLLATE "USING_NLS_COMP" DEFAULT 'N', "TYPE" VARCHAR2(1000) COLLATE "USING_NLS_COMP", "DATA" BLOB, "STAR_NUM" NUMBER)  DEFAULT COLLATION "USING_NLS_COMP"
--------------------------------------------------------
--  DDL for Table TB_ROLES
--------------------------------------------------------

  CREATE TABLE "TB_ROLES" ("ID" NUMBER, "NAME" VARCHAR2(1000) COLLATE "USING_NLS_COMP")  DEFAULT COLLATION "USING_NLS_COMP"
--------------------------------------------------------
--  DDL for Table TB_USER
--------------------------------------------------------

  CREATE TABLE "TB_USER" ("ID" NUMBER, "EMAIL" VARCHAR2(1000) COLLATE "USING_NLS_COMP", "PASSWORD" VARCHAR2(1000) COLLATE "USING_NLS_COMP", "USERNAME" VARCHAR2(1000) COLLATE "USING_NLS_COMP", "ROLE" VARCHAR2(1000) COLLATE "USING_NLS_COMP", "NAME" VARCHAR2(1000) COLLATE "USING_NLS_COMP", "BIRTH" VARCHAR2(1000) COLLATE "USING_NLS_COMP", "ADDRESS" VARCHAR2(1000) COLLATE "USING_NLS_COMP", "PHONE" VARCHAR2(1000) COLLATE "USING_NLS_COMP", "DELETE_YN" CHAR(1) COLLATE "USING_NLS_COMP" DEFAULT 'N', "INSERT_TIME" VARCHAR2(1000) COLLATE "USING_NLS_COMP", "UPDATE_TIME" VARCHAR2(1000) COLLATE "USING_NLS_COMP", "DELETE_TIME" VARCHAR2(1000) COLLATE "USING_NLS_COMP", "USER_ID" NUMBER)  DEFAULT COLLATION "USING_NLS_COMP"
--------------------------------------------------------
--  Constraints for Table TB_CART
--------------------------------------------------------

  ALTER TABLE "TB_CART" ADD PRIMARY KEY ("ID") USING INDEX  ENABLE
--------------------------------------------------------
--  Constraints for Table TB_FAQ
--------------------------------------------------------

  ALTER TABLE "TB_FAQ" MODIFY ("ID" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table TB_LIKE
--------------------------------------------------------

  ALTER TABLE "TB_LIKE" ADD CONSTRAINT "PK_FAVORITE_ID" PRIMARY KEY ("LIKE_ID") USING INDEX  ENABLE
--------------------------------------------------------
--  Constraints for Table TB_ORDER
--------------------------------------------------------

  ALTER TABLE "TB_ORDER" ADD PRIMARY KEY ("ID") USING INDEX  ENABLE
--------------------------------------------------------
--  Constraints for Table TB_PRODUCT
--------------------------------------------------------

  ALTER TABLE "TB_PRODUCT" ADD CONSTRAINT "PK_ID" PRIMARY KEY ("ID") USING INDEX  ENABLE
--------------------------------------------------------
--  Constraints for Table TB_REVIEW
--------------------------------------------------------

  ALTER TABLE "TB_REVIEW" ADD CONSTRAINT "PK_REVIEW_ID" PRIMARY KEY ("REVIEW_ID") USING INDEX  ENABLE
  ALTER TABLE "TB_REVIEW" MODIFY ("ID" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table TB_ROLES
--------------------------------------------------------

  ALTER TABLE "TB_ROLES" ADD CONSTRAINT "PK_ROLES_ID" PRIMARY KEY ("ID") USING INDEX  ENABLE
--------------------------------------------------------
--  Constraints for Table TB_USER
--------------------------------------------------------

  ALTER TABLE "TB_USER" ADD CONSTRAINT "PK_USER_ID" PRIMARY KEY ("ID") USING INDEX  ENABLE
  ALTER TABLE "TB_USER" ADD CONSTRAINT "UK_USER_USERID" UNIQUE ("USERNAME") USING INDEX  ENABLE
  ALTER TABLE "TB_USER" ADD CONSTRAINT "UK_USER_EMAIL" UNIQUE ("EMAIL") USING INDEX  ENABLE
--------------------------------------------------------
--  Ref Constraints for Table TB_CART
--------------------------------------------------------

  ALTER TABLE "TB_CART" ADD CONSTRAINT "FK_CART_PRODUCT" FOREIGN KEY ("PRODUCT_ID") REFERENCES "TB_PRODUCT" ("ID") ON DELETE CASCADE ENABLE
  ALTER TABLE "TB_CART" ADD CONSTRAINT "FK_CART_USER" FOREIGN KEY ("USER_ID") REFERENCES "TB_USER" ("ID") ON DELETE CASCADE ENABLE
--------------------------------------------------------
--  Ref Constraints for Table TB_LIKE
--------------------------------------------------------

  ALTER TABLE "TB_LIKE" ADD CONSTRAINT "FK_PRODUCT_ID_FAVORITE" FOREIGN KEY ("PRODUCT_ID") REFERENCES "TB_PRODUCT" ("ID") ON DELETE CASCADE ENABLE
  ALTER TABLE "TB_LIKE" ADD CONSTRAINT "FK_USER_ID_FAVORITE" FOREIGN KEY ("USER_ID") REFERENCES "TB_USER" ("ID") ON DELETE CASCADE ENABLE
--------------------------------------------------------
--  Ref Constraints for Table TB_ORDER
--------------------------------------------------------

  ALTER TABLE "TB_ORDER" ADD CONSTRAINT "FK_ORDER_USER" FOREIGN KEY ("USER_ID") REFERENCES "TB_USER" ("ID") ON DELETE CASCADE ENABLE
  ALTER TABLE "TB_ORDER" ADD CONSTRAINT "FK_PRODUCT_ID_ORDER" FOREIGN KEY ("PRODUCT_ID") REFERENCES "TB_PRODUCT" ("ID") ON DELETE CASCADE ENABLE
--------------------------------------------------------
--  Ref Constraints for Table TB_REVIEW
--------------------------------------------------------

  ALTER TABLE "TB_REVIEW" ADD CONSTRAINT "FK_REVIEW_ID" FOREIGN KEY ("ID") REFERENCES "TB_PRODUCT" ("ID") ON DELETE CASCADE ENABLE
