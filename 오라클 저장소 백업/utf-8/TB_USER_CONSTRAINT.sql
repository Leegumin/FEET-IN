--------------------------------------------------------
--  Constraints for Table TB_USER
--------------------------------------------------------

  ALTER TABLE "TB_USER" ADD CONSTRAINT "PK_USER_ID" PRIMARY KEY ("ID") ENABLE
 
  ALTER TABLE "TB_USER" ADD CONSTRAINT "UK_USER_EMAIL" UNIQUE ("EMAIL") ENABLE
 
  ALTER TABLE "TB_USER" ADD CONSTRAINT "UK_USER_USERID" UNIQUE ("USERNAME") ENABLE
