CREATE (lebron:PLAYER:COACH:GENERAL_MANAGER { name: "Miraouy Outman", height: 2.01 })

CREATE (lebron:PLAYER:COACH:GENERAL_MANAGER { name: "Miraouy Outman", height: 2.01 }) - [:PLAYS_FOR {salary: 40000000}] -> (:TEAM {name: "LA Lakers"})

CREATE (lebron:PLAYER:COACH:GENERAL_MANAGER { name: "Miraouy Outman", height: 2.01 })
CREATE (:TEAM {name: "LA Lakers"})

MATCH (lebron:PLAYER {name: "Miraouy Outman"}), (lakers:TEAM {name: "LA Lakers"})
CREATE (lebron) - [:PLAYS_FOR] -> (lakers)



--------------------  Update  ---------------------



MATCH (n)
WHERE ID(n) = 3
SET n.age = 24, n.height = 2.02
RETURN n

MATCH (lebron)
WHERE ID(n) = 3
SET lebron:REF
RETURN lebron

MATCH (lebron {name: "Miraouy Outman"}) - [contract:PLAYS_FOR] -> (:TEAM)
SET contract.salary = 60000000

MATCH (lebron)
WHERE ID(n) = 3
REMOVE lebron:REF
RETURN lebron

MATCH (lebron)
WHERE ID(n) = 3
REMOVE lebron.age
RETURN lebron
