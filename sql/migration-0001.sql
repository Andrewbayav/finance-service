-- This database was automatically created by docker-compose up command
-- create database yahoofinance;

CREATE TABLE public.stocks
(
    id SERIAL PRIMARY KEY NOT NULL,
    ticker TEXT,
    name TEXT,
    price FLOAT
);

CREATE UNIQUE INDEX stocks_id_uindex ON public.stocks (id);
