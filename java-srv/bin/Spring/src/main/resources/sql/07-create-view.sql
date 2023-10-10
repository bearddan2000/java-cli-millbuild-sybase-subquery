CREATE VIEW dog_expanded
    AS SELECT d.id, b.breed, c.color
    FROM master.dbo.dog as d
    JOIN master.dbo.breedLookup as b ON b.id = d.breedId
    JOIN master.dbo.colorLookup as c ON c.id = d.colorId
