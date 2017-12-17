insert into node (id,node_type,properties) 
  values ('99df692f9caa43c2b67f06a582077188','Movie','{"title": "Terminator"}');
  
insert into node (id,node_type,properties)
  values ('f4c416a409d84b408c3e650a765cd71c','Director','{"name": "James Cameron"}');
  
insert into edge (id,edge_type,from_node_id,to_node_id,properties)
  values ('50e81e19f0574ea6aca1a4e7defad8b3','directed','f4c416a409d84b408c3e650a765cd71c','99df692f9caa43c2b67f06a582077188','{}');