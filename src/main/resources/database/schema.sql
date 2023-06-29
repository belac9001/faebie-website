CREATE EXTENSION IF NOT EXISTS citext;

create table if not exists submissions
(
  id serial not null,
  submissionId varchar(255) not null,
  subreddit varchar(255) not null,
  created bigint not null,
  title varchar(255),
  text varchar(255),
  author varchar(255),
  permalink varchar(255),
  score integer,
  url varchar(255)
);