CREATE VIEW breed_count (id, breed, itemCount)
    AS SELECT c.id, c.breed, COUNT(*)
    FROM (SELECT breedId FROM master.dbo.dog) as a
    JOIN master.dbo.breedLookup as c ON c.id = a.breedId
    GROUP BY a.breedId
