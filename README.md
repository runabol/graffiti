# Graffiti

Graffiti is a headless (no UI) Java CMS which is designed to be dead simple. 

# Concepts

Graffiti relies on a handful of core data structures: 

## Graph

A Graph is actually an extermely simple yet powerful data structure:

![alt text](graph.png "Graph")

It is composed of only two basic units:

### Node

A Node (the round elements in the Graph above) is the basic unit of data. Another word for it could be Object or an Entity but we like Node better because this is how they like to call it in the Graph world.

Nodes are grouped by a type which is just a simple String.

Almost anything can be modelled as a Node: people, cars, invoices, transactions, movies, etc.  

### Edge

An edge represents a link between two nodes. Again, this could be called a Link or a Connection but we like to stick to the fancy mathematical jargon.

### That's it!

And between these basic primitive data structures you can represent just about anything.
