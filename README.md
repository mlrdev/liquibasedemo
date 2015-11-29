# Liquibase Playground

This is a simple show-case to database-versioning with the help of Liquibase (<http://www.liquibase.org/>). In this demo a database with some pre-filled data got altered via java-based Liquibase.*

## Setup

- Import as gradle project
- Database: 
    - install postgresql / or the db of your choice
    - create a db and a user * (use: liquibase/liquibase or whatever you like)
    - (change application.yml, if you won't stick to the configured defaults above)

Example to create the pre-configured database and user:
<pre>
sudo -u postgres createuser liquibase
sudo -u postgres createdb --owner=liquibase liquibase
sudo -u postgres psql -c "ALTER USER liquibase WITH PASSWORD 'liquibase'"
</pre>

Connect as user liquibase to the database via: ` psql -U liquibase -h localhost `

If you use PostgreSQL and simply want to start this, then you could run `scripts/createDB`. The result would be a database "liquibase" with a table "person" that looks like so:
 
| id |  name  |  birthday |  
|-|-|-|-|
| 1 | Mike   | 1963-09-01 |
| 2 | Henry  | 1982-08-13 |
| 3 | Pi'ter | 1582-03-14 |
| 4 | Angela | 1954-07-17 |

To reset the database to it's original state you could use `/scripts/resetDB`.

## Run
`./gradlew bootRun `

If the changeset was processes sucessfull, you should get some output like this:
` "[...] ChangeSet <path/name.xml>::<changeSetId>::<Author> ran successfully in n ms" `

After this an entry in your (automatically created table) `databasechangelog` should contain the md5sum for your specific changeset(s).

The second time you run this code, only newer changesets will be processed. 

Enjoy!



-------
*)
I haven't worked with Liquibase (<http://www.liquibase.org/>) for some months and never used the "java-way" to do it.
So on a cold lone evening I began (and hopefully I continued over time) this little approach -- where the Spring Boot Setup took me the longest time :-/ -- to refresh the basics.