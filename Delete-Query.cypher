// Deleting a Node (Not going to work)
MATCH(j {name: "Miraouy"})
DELETE j

// Delete node and relationships
MATCH(j {name: "Miraouy"})
DETACH DELETE j

// Delete relationship
MATCH(joel {name: "Miraouy"}) - [rel:PLAYS_FOR] -> (:TEAM)
DELETE rel
