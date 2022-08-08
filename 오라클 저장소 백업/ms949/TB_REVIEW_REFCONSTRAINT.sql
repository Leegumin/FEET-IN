--------------------------------------------------------
--  Ref Constraints for Table TB_REVIEW
--------------------------------------------------------

  ALTER TABLE "TB_REVIEW" ADD CONSTRAINT "FK_REVIEW_ID" FOREIGN KEY ("ID") REFERENCES "TB_PRODUCT" ("ID") ON DELETE CASCADE ENABLE
