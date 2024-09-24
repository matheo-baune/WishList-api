DROP TABLE IF EXISTS "gifts";
DROP SEQUENCE IF EXISTS gifts_id_seq;
CREATE SEQUENCE gifts_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."gifts" (
                                  "id" integer DEFAULT nextval('gifts_id_seq') NOT NULL,
                                  "name" character varying(255) NOT NULL,
                                  "description" text,
                                  "link" text,
                                  "image" text,
                                  "price" numeric(10,2),
                                  "created_by" integer,
                                  "group_id" integer,
                                  "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
                                  CONSTRAINT "gifts_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

CREATE INDEX "idx_gifts_group_id" ON "public"."gifts" USING btree ("group_id");


DROP TABLE IF EXISTS "group_members";
DROP SEQUENCE IF EXISTS group_members_id_seq;
CREATE SEQUENCE group_members_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."group_members" (
                                          "id" integer DEFAULT nextval('group_members_id_seq') NOT NULL,
                                          "user_id" integer,
                                          "group_id" integer,
                                          "joined_at" timestamp DEFAULT CURRENT_TIMESTAMP,
                                          CONSTRAINT "group_members_pkey" PRIMARY KEY ("id"),
                                          CONSTRAINT "group_members_user_id_group_id_key" UNIQUE ("user_id", "group_id")
) WITH (oids = false);


DROP TABLE IF EXISTS "groups";
DROP SEQUENCE IF EXISTS groups_id_seq;
CREATE SEQUENCE groups_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."groups" (
                                   "id" integer DEFAULT nextval('groups_id_seq') NOT NULL,
                                   "name" character varying(100) NOT NULL,
                                   "created_by" integer,
                                   "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
                                   CONSTRAINT "groups_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


DROP TABLE IF EXISTS "reservations";
DROP SEQUENCE IF EXISTS reservations_id_seq;
CREATE SEQUENCE reservations_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."reservations" (
                                         "id" integer DEFAULT nextval('reservations_id_seq') NOT NULL,
                                         "gift_id" integer,
                                         "reserved_by" integer,
                                         "reserved_at" timestamp DEFAULT CURRENT_TIMESTAMP,
                                         CONSTRAINT "reservations_gift_id_reserved_by_key" UNIQUE ("gift_id", "reserved_by"),
                                         CONSTRAINT "reservations_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

CREATE INDEX "idx_reservations_gift_id" ON "public"."reservations" USING btree ("gift_id");


DROP TABLE IF EXISTS "users";
DROP SEQUENCE IF EXISTS users_id_seq;
CREATE SEQUENCE users_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."users" (
                                  "id" integer DEFAULT nextval('users_id_seq') NOT NULL,
                                  "username" character varying(50) NOT NULL,
                                  "email" character varying(100) NOT NULL,
                                  "password" character varying(255) NOT NULL,
                                  "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
                                  CONSTRAINT "users_email_key" UNIQUE ("email"),
                                  CONSTRAINT "users_pkey" PRIMARY KEY ("id"),
                                  CONSTRAINT "users_username_key" UNIQUE ("username")
) WITH (oids = false);


ALTER TABLE ONLY "public"."gifts" ADD CONSTRAINT "gifts_created_by_fkey" FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE ONLY "public"."gifts" ADD CONSTRAINT "gifts_group_id_fkey" FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE CASCADE NOT DEFERRABLE;

ALTER TABLE ONLY "public"."group_members" ADD CONSTRAINT "group_members_group_id_fkey" FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE ONLY "public"."group_members" ADD CONSTRAINT "group_members_user_id_fkey" FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE NOT DEFERRABLE;

ALTER TABLE ONLY "public"."groups" ADD CONSTRAINT "groups_created_by_fkey" FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE CASCADE NOT DEFERRABLE;

ALTER TABLE ONLY "public"."reservations" ADD CONSTRAINT "reservations_gift_id_fkey" FOREIGN KEY (gift_id) REFERENCES gifts(id) ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE ONLY "public"."reservations" ADD CONSTRAINT "reservations_reserved_by_fkey" FOREIGN KEY (reserved_by) REFERENCES users(id) ON DELETE CASCADE NOT DEFERRABLE;