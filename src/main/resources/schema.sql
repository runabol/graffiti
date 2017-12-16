drop table if exists node cascade; 

create table node (
  id          varchar(64) primary key,
  node_type   varchar(64) not null,
  created_at  timestamp not null,
  modified_at timestamp not null,
  properties  jsonb not null,
  deleted     boolean not null default false
);

create table edge (
  id           varchar(64) primary key,
  edge_type    varchar(64) not null,
  created_at   timestamp not null,
  modified_at  timestamp not null,
  from_node_id varchar(64) not null references node (id),
  to_node_id   varchar(64) not null references node (id),
  properties   jsonb not null,
  deleted      boolean not null default false
);