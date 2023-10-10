# java-cli-millbuild-sybase-subquery

## Description
Creates a small database table
called `dog`. This table, `dog`, has been normalized to 3NF.
Two new tables have been added, `breedLookup` and `colorLookup`.
Creates a new table `dog_expanded` that joins
`dog`, `breedLookup` and `colorLookup`. Added clustered indexes on
`dog`.breedId and `dog`.colorId and a non-clustered index for
`dog_expanded`.id. Turned `dog_expanded` into a view with an
implicit index on `dog_expanded`.id. Using a subquery with the aggregate function
COUNT, create a new view `breed_count`. 

## Tech stack
- java
- millbuild
  - log4j
  - sybase driver

## Docker stack
- nightscape/scala-mill
- mariadb:latest

## To run
`sudo ./install.sh -u`

## To stop
`sudo ./install.sh -d`

## For help
`sudo ./install.sh -h`

## Credit
https://github.com/htorun/dbtableprinter