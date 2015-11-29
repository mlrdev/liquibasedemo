--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: person; Type: TABLE; Schema: public; Owner: liquibase; Tablespace: 
--

CREATE TABLE person (
    id bigint NOT NULL,
    name character varying(32),
    birthday date
);


ALTER TABLE person OWNER TO liquibase;

--
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: liquibase
--

CREATE SEQUENCE person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE person_id_seq OWNER TO liquibase;

--
-- Name: person_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: liquibase
--

ALTER SEQUENCE person_id_seq OWNED BY person.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: liquibase
--

ALTER TABLE ONLY person ALTER COLUMN id SET DEFAULT nextval('person_id_seq'::regclass);


--
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: liquibase
--

COPY person (id, name, birthday) FROM stdin;
1	Mike	1963-09-01
2	Henry	1982-08-13
3	Pi'ter	1582-03-14
4	Angela	1954-07-17
\.


--
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: liquibase
--

SELECT pg_catalog.setval('person_id_seq', 4, true);


--
-- Name: person_pkey; Type: CONSTRAINT; Schema: public; Owner: liquibase; Tablespace: 
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

