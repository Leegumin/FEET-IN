--------------------------------------------------------
--  Ref Constraints for Table TB_LIKE
--------------------------------------------------------

  ALTER TABLE "TB_LIKE" ADD CONSTRAINT "FK_PRODUCT_ID_FAVORITE" FOREIGN KEY ("PRODUCT_ID") REFERENCES "TB_PRODUCT" ("ID") ON DELETE CASCADE ENABLE
 
  ALTER TABLE "TB_LIKE" ADD CONSTRAINT "FK_USER_ID_FAVORITE" FOREIGN KEY ("USER_ID") REFERENCES "TB_USER" ("ID") ON DELETE CASCADE ENABLE
