--------------------------------------------------------
--  Ref Constraints for Table TB_CART
--------------------------------------------------------

  ALTER TABLE "TB_CART" ADD CONSTRAINT "FK_CART_PRODUCT" FOREIGN KEY ("PRODUCT_ID") REFERENCES "TB_PRODUCT" ("ID") ON DELETE CASCADE ENABLE
 
  ALTER TABLE "TB_CART" ADD CONSTRAINT "FK_CART_USER" FOREIGN KEY ("USER_ID") REFERENCES "TB_USER" ("ID") ON DELETE CASCADE ENABLE
