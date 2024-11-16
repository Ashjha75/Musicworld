# Entity

In Spring Boot, an entity class represents a table in a database and is used in conjunction with JPA (Java Persistence
API) to map Java objects to database records

here -

`@Entity` The @Entity annotation marks a class as a JPA entity. \
`@ID` Each entity class typically has an @Id annotation to specify the primary key. \
`@GeneratedValue`  Configures the way of incrementing the primary key. \
`@Table` Provides additional table-specific information like name and schema.\
`@Column`  Customizes the mapping between the entity field and the database column.\
