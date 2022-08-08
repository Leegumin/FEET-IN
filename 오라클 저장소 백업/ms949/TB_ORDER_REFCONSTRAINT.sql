--------------------------------------------------------
--  Ref Constraints for Table TB_ORDER
--------------------------------------------------------

  ALTER TABLE "TB_ORDER" ADD CONSTRAINT "FK_ORDER_USER" FOREIGN KEY ("USER_ID") REFERENCES "TB_USER" ("ID") ON DELETE CASCADE ENABLE
 
  ALTER TABLE "TB_ORDER" ADD CONSTRAINT "FK_PRODUCT_ID_ORDER" FOREIGN KEY ("PRODUCT_ID") REFERENCES "TB_PRODUCT" ("ID") ON DELETE CASCADE ENABLE
