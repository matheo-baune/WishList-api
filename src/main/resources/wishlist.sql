DROP TABLE IF EXISTS "gifts" CASCADE;
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
                                  "groups_id" integer,
                                  "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
                                  CONSTRAINT "gifts_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

CREATE INDEX "idx_gifts_groups_id" ON "public"."gifts" USING btree ("groups_id");


DROP TABLE IF EXISTS "group_members" CASCADE;
DROP SEQUENCE IF EXISTS group_members_id_seq;
CREATE SEQUENCE group_members_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."group_members" (
                                          "id" integer DEFAULT nextval('group_members_id_seq') NOT NULL,
                                          "users_id" integer,
                                          "groups_id" integer,
                                          "joined_at" timestamp DEFAULT CURRENT_TIMESTAMP,
                                          CONSTRAINT "group_members_pkey" PRIMARY KEY ("id"),
                                          CONSTRAINT "group_members_users_id_groups_id_key" UNIQUE ("users_id", "groups_id")
) WITH (oids = false);


DROP TABLE IF EXISTS "groups" CASCADE;
DROP SEQUENCE IF EXISTS groups_id_seq;
CREATE SEQUENCE groups_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."groups" (
                        id BIGINT DEFAULT nextval('groups_id_seq') PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        created_by INT NOT NULL,
                        code VARCHAR(10) NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


DROP TABLE IF EXISTS "reservations" CASCADE;
DROP SEQUENCE IF EXISTS reservations_id_seq;
CREATE SEQUENCE reservations_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."reservations" (
                                         "id" integer DEFAULT nextval('reservations_id_seq') NOT NULL,
                                         "gifts_id" integer,
                                         "reserved_by" integer,
                                         "reserved_at" timestamp DEFAULT CURRENT_TIMESTAMP,
                                         CONSTRAINT "reservations_gifts_id_reserved_by_key" UNIQUE ("gifts_id", "reserved_by"),
                                         CONSTRAINT "reservations_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

CREATE INDEX "idx_reservations_gifts_id" ON "public"."reservations" USING btree ("gifts_id");


DROP TABLE IF EXISTS "users" CASCADE;
DROP SEQUENCE IF EXISTS users_id_seq;
CREATE SEQUENCE users_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."users" (
                                  "id" integer DEFAULT nextval('users_id_seq') NOT NULL,
                                  "username" character varying(50) NOT NULL,
                                  "email" character varying(100) NOT NULL,
                                  "password" character varying(255) NOT NULL,
                                  "role" character varying(20) DEFAULT 'users' NOT NULL,
                                  "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
                                  CONSTRAINT "users_email_key" UNIQUE ("email"),
                                  CONSTRAINT "users_pkey" PRIMARY KEY ("id"),
                                  CONSTRAINT "users_username_key" UNIQUE ("username")
) WITH (oids = false);


ALTER TABLE ONLY "public"."gifts" ADD CONSTRAINT "gifts_created_by_fkey" FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE ONLY "public"."gifts" ADD CONSTRAINT "gifts_groups_id_fkey" FOREIGN KEY (groups_id) REFERENCES groups(id) ON DELETE CASCADE NOT DEFERRABLE;

ALTER TABLE ONLY "public"."group_members" ADD CONSTRAINT "group_members_groups_id_fkey" FOREIGN KEY (groups_id) REFERENCES groups(id) ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE ONLY "public"."group_members" ADD CONSTRAINT "group_members_users_id_fkey" FOREIGN KEY (users_id) REFERENCES users(id) ON DELETE CASCADE NOT DEFERRABLE;

ALTER TABLE ONLY "public"."groups" ADD CONSTRAINT "groups_created_by_fkey" FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE CASCADE NOT DEFERRABLE;

ALTER TABLE ONLY "public"."reservations" ADD CONSTRAINT "reservations_gifts_id_fkey" FOREIGN KEY (gifts_id) REFERENCES gifts(id) ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE ONLY "public"."reservations" ADD CONSTRAINT "reservations_reserved_by_fkey" FOREIGN KEY (reserved_by) REFERENCES users(id) ON DELETE CASCADE NOT DEFERRABLE;