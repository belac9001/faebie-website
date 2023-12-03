CREATE EXTENSION IF NOT EXISTS citext;

create table if not exists submissions
(
  id serial not null,
  submissionId varchar(255) not null,
  subreddit varchar(255) not null,
  title varchar(255),
  text varchar(255),
  author varchar(255),
  url varchar(255),
  permalink varchar(255),
  score integer,
  created bigint not null
);

create table if not exists comments
(
  id serial not null,
  submissionId varchar(255) not null,
  parentId varchar(255),
  subreddit varchar(255) not null,
  body varchar(255),
  author varchar(255),
  score integer,
  ups integer,
  controversiality integer,
  created bigint not null
);